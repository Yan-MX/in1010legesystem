package obli2;


/*
 * sorted list is a sub class of linked list
 * it sorts elements in the list from small to big
 * it disables 
	public void leggTil(int pos, T x) 
	public void sett(int pos, T x) 
 * leggTil method has the biggest change
 * fjern() delete the last item in the list, biggest out
 * */



//only objects that inherit from the comparable class can be used in this method
public class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {
	public SortertLenkeliste() {
	}
	
	
	/*first check the size of the sorted list
	 *  size 0
	 *  size 1
	 *  size>= 2   if so, compare new item to the first and last in the existing list:
							 *  new item less than the first(least)
							 *  new item bigger than the last(biggest)
							 *  new item in the middle
	 *  make sure index valid :)
	*/
	public void leggTil(T x) {
		
		if (this.stoerrelse() == 0) {
			// System.out.println("0 case");
			/*String a ="elementAAA";
			String b = "elementDD";
			System.out.println("this is the resultof :" +a.compareTo(b));
			//the output of it is -3!!!!*/
			super.leggTil(x);
			/* in this part, it does not need to size++, cuz inside super.leggtil(x), the size 
			 * already changes(+1) therefore just return to stop  */
			return;
			
			
			
			
		} else if (this.stoerrelse() == 1) {
			//System.out.println("compare output: "+
			//getStartNode().getdata().compareTo(x));
			if (getStartNode().getdata().compareTo(x) <= 0) {
				this.getStartNode().setnextnode(new Node<T>(x));

			} else {
				T a = this.getStartNode().getdata();
				this.setStartNode(new Node<T>(x));
				this.getStartNode().setnextnode(new Node<T>(a));
			}
		//if there are already at least two elements
		} else {
			//System.out.println("2 case");
			int index = 0;
			//compare to (if bigger, >=1, if smaller <0
			if (getStartNode().getdata().compareTo(x) < 0
					&& findnode(this.stoerrelse() - 1).getdata().compareTo(x) > 0) {
				while (findnode(index).getdata().compareTo(x) <=0) {
					index++;
				}
				//System.out.println(x+ " "+ "the index is :" +index);
				Node<T> aftertheNode = findnode(index);
				findnode(index - 1).setnextnode(new Node<T>(x));
				findnode(index).setnextnode(aftertheNode);
			} else if (getStartNode().getdata().compareTo(x) >= 0) {
				Node<T> newFirstNode = new Node<T>(x);
				Node<T> a = this.getStartNode();
				this.setStartNode(newFirstNode);
				newFirstNode.setnextnode(a);
				
			} else if (findnode(this.stoerrelse() - 1).getdata().compareTo(x) <= 0){
				findnode(stoerrelse() - 1).setnextnode(new Node<T>(x));
			}

		}
		size++;
		//System.out.println("size up");

	}
	


	
	//last out, if there is at least one
	public T fjern() {
		if (size == 0) {
			throw new UgyldigListeIndeks(0);
		}
		switch (size) {
		case 1:
			getStartNode().setnextnode(null);
			size--;
			return getStartNode().getdata();
		default:
			T a = findnode(size - 1).getdata();
			findnode(size - 2).setnextnode(null);
			size--;
			return a;
		}

	}

	public void leggTil(int pos, T x) {
		throw new UnsupportedOperationException();
	}

	public void sett(int pos, T x) {
		throw new UnsupportedOperationException();
	}

}
