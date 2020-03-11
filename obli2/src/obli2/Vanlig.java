package obli2;

public class  Vanlig extends Legemiddel{
	
	public  Vanlig(String s, double a, double b) {
		super(s,a,b);
	}

	@Override
	public String toString() {
		return navn+ " [Vanlig] [virkestoff=" + virkestoff + ", pris=" + pris + ", Id=" + Id + "]";
	}
	
	
}
