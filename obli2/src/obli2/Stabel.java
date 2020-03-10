package obli2;

public class Stabel<T> extends Lenkeliste<T>{
	public Stabel() {
	}
	public void leggPaa (T x) {
		leggTil(x);
	}
	//first in last out, ta av is to delete the last item in the list
	public T taAv() {
		return fjern(stoerrelse()-1);
	}
}
