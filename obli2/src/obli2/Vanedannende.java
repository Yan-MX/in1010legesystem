package obli2;

public class Vanedannende  extends Legemiddel{
	private int styrke;
	
	public Vanedannende (String s, double a, double b, Integer e) {
		super(s,a,b);
		styrke = e;
	}
	public int hentVanedannendeStyrke() {
		return styrke;
	}
	@Override
	public String toString() {
		return navn+ " [Vanedannende] [virkestoff=" + virkestoff + ", pris=" + pris + ", Id=" + Id
				+ ", styrke=" + styrke + "]";
	}
	
}
