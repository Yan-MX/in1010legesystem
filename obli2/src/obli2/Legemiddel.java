package obli2;

//this is the class Legemiddel
public abstract class Legemiddel {
	protected String navn;
	protected double virkestoff, pris;
	protected int Id;
	protected static int Idcounter = 0;
	//constructor
	public Legemiddel(String s, double a, double b) {
		navn = s;
		pris = a;
		virkestoff = b;
		Id = Idcounter;
		Idcounter++;
	}
	//return ID
	protected int hentId() {
		return Id;
	}
	//return name
	public String hentNavn() {
		return navn;
	}
	
	public double hentPris() {
		return pris;
	}
	
	public double hentVirkestoff() {
		return virkestoff;
	}
	
	public void settNyPris(double nypris) {
		pris = nypris;
	}
	//override of to String
	@Override
	public String toString() {
		return "Legemiddel [navn=" + navn + ", virkestoff=" + virkestoff + ", pris=" + pris + ", Id=" + Id + "]";
	}

	
}
