package obli2;

import java.util.Iterator;

/*This is the lenkeliste(linked list) class and it uses the Liste<T> interface
 * it has several methods and follows first in first out
 * Node<T> class is also written beneath linked list. I choose to only use single pointer
 * for noders to point to the next one only. 
 * I create several other methods :
 * 1.findnode(int pos), this method return the node 
 * at given postion if not out of index or so.
 * 2.getlastNode(), it simply return the last node
 * 3. getter and setter for startNode
 * 
 * for the method leggTil, if it is empty in the linked list and if give a null data 
 * to create a start node, it will ignore. This is to make sure the linked list is not full
 * of nulls.
 * */
public class Lenkeliste<T> implements Liste<T> {
	protected int size;
	//have created getter and setter for this
	private Node<T> startNode;

	public Lenkeliste() {
	}

	public int stoerrelse() {
		return size;
	}

	// set node in a specific location and the nodes after index+1
	public void leggTil(int pos, T x) {
		Node<T> theNode = new Node<T>(x);
		/* discuss pos and size, there are several conditions:
		 * first is pos out of range, out of index
		 * second pos = size, although out of range, but can add as the new last item
		 * thrid pos == o ,set it in the first then
		 * fourth pos in within the index and pos is neither the first or the last.
		*/
		//first
		if (pos < 0 || pos > size) {
			throw new UgyldigListeIndeks(pos);
			//second
		} else if (pos == size) {
			leggTil(x);
			//third
		} else if (pos == 0) {
			if (getStartNode() == null) {
				leggTil(x);
			} else {
				theNode.setnextnode(getStartNode());
				setStartNode(theNode);
				size++;
			}
			
			//fourth
		} else {
			Node<T> afternewNode = findnode(pos);
			Node<T> beforenewNode = findnode(pos - 1);
			beforenewNode.setnextnode(theNode);
			theNode.setnextnode(afternewNode);
			size++;
		}

	}

	// add one node to the last place
	public void leggTil(T x) {
		if (x == null) {
			return;
		}
		if (getStartNode() == null) {
			setStartNode(new Node<T>(x));
		} else {
			Node<T> m = new Node<T>(x);
			Node<T> lastnode = getlastNode(getStartNode());
			lastnode.setnextnode(m);
		}
		size++;
	}

	// return the last node in the linked list
	private Node<T> getlastNode(Node<T> c) {
		Node<T> lastNode = c;
		if (lastNode.getnextnode() != null) {
			return getlastNode(lastNode.getnextnode());
		} else {
			return lastNode;
		}

	}

	// set the value of a node at a specific position if not out of index range
	// the new value will replace the old value
	public void sett(int pos, T x) {
		if (0 <= pos && pos < stoerrelse()) {
			Node<T> theNode = findnode(pos);
			theNode.setdata(x);
		} else {
			throw new UgyldigListeIndeks(pos);
		}
	}

	// find certain based on index and return the data of the found node
	public Node<T> findnode(int pos) {
		//first check valid index
		if (pos < 0 || pos >= size) {
			throw new UgyldigListeIndeks(pos);
		}
		Node<T> search = getStartNode();
		int index = 0;
		//while loop to find the given index
		while (index != pos) {
			search = search.getnextnode();
			index++;
		}
		return search;
	}

	public T hent(int pos) {
		return findnode(pos).getdata();
	}

	// delete one at specific location
	public T fjern(int pos) {
		//first check index valid
		if (pos < 0 || pos >= size) {
			throw new UgyldigListeIndeks(pos);
		}
		//size >= 2
		if (size >= 2) {
			// then discussing pos, if it is the first, last or in the middle
			if (pos == 0) {
				T a = getStartNode().getdata();

				setStartNode(findnode(1));
				size--;
				return a;
			} else if (pos == size - 1) {
				T a = findnode(size - 1).getdata();
				findnode(size - 2).setnextnode(null);
				size--;
				return a;
			} else {
				T a1 = findnode(pos).getdata();
				findnode(pos - 1).setnextnode(findnode(pos + 1));
				size--;
				return a1;
			}
		//size = 0
		} else if (size == 0) {
			return null;
		}
		// size == 1
		else {
			T a = getStartNode().getdata();
			this.setStartNode(null);
			size--;
			return a;
		}

	}

	//if there is at least one item in it, always delete the first one in the list
	public T fjern() {
		if (size == 0) {
			throw new UgyldigListeIndeks(0);
		}
		switch (size) {
		case 1:
			T a2 = getStartNode().getdata();
			size--;
			this.setStartNode(null);
			return a2;
			//size >1
		default:
			T a = getStartNode().getdata();
			setStartNode(findnode(1));
			size--;
			return a;

		}

	}

	public Node<T> getStartNode() {
		return startNode;
	}

	public void setStartNode(Node<T> startNode) {
		this.startNode = startNode;
	}
	public Iterator<T> iterator() {
		return new LenkelisteIterator<T>(this);
	}



}

 class LenkelisteIterator<T> implements Iterator<T>{
	private int index = 0;
	private Lenkeliste<T> liste;
	public LenkelisteIterator(Lenkeliste<T> a) {
		liste = a;
	}
	@Override
	public boolean hasNext() {
		if(liste.stoerrelse()>=index+1){
			return true;
		}
		return false;
	}

	@Override
	public T next() {
		if (this.hasNext()) {
			return liste.findnode(index++).getdata();
		}
		return null;
	}
	
}

 class Node<T> {
	private Node<T> next;
	private T data;

	// overloading constructor
	public Node(T a) {
		data = a;
	}

	public Node(T a, Node<T> b) {
		next = b;
		data = a;
	}

	public T getdata() {
		return data;
	}

	public void setdata(T r) {
		data = r;
	}

	public Node<T> getnextnode() {
		return next;
	}

	public void setnextnode(Node<T> q) {
		next = q;
	}
	
}

