package obli2;

public class Narkotisk extends Legemiddel{
	private int styrke;
	
	public Narkotisk(String s, double a, double b, Integer e) {
		super(s,a,b);
		styrke = e;
		
	}
	public int hentNarkotiskStyrke() {
		return styrke;
	}
	@Override
	public String toString() {
		return navn+ " [Narkotisk] [styrke=" + styrke + ",  virkestoff=" + virkestoff + ", pris=" + pris
				+ ", Id=" + Id + "]";
	}
	
	
}
