package obli2;

public abstract class Resept {
	// Konstruktøren i Resept skal ta inn et Legemiddel
	//legemiddel, en Lege utskrivendeLege, en int pasientId og 
	//int reit (i den rekkefølgen).
	protected Legemiddel legemiddel;
	protected static int idcounter = 0;
	protected int reit,Id;
	protected Pasient pasient;
	protected Lege lege;
	
	public Resept(Legemiddel a, Lege b, Pasient c, int d) {
		legemiddel = a;
		lege = b;
		pasient = c;
		reit = d;
		Id = idcounter;
		idcounter++;
	}
	
	public int hentId() {
		return Id;
	}
	
	public Legemiddel hentLegemiddel() {
		return legemiddel;
	}
	
	public Lege hentLege() {
		return lege;
	}
	public Pasient hentPasient() {
		return pasient;
	}
	
	public int hentReit() {
		return reit;
	}
	public boolean bruk() {
		if(reit >0) {
			reit--;
			return true;
		}
		else {
			return false;
		}
	}
	abstract public String farge();
	abstract public double prisAaBetale();

	@Override
	public String toString() {
		return "Resept [farge = "+ farge() +",\n       legemiddel=" + legemiddel.hentNavn() + ",\n       reit=" + reit + ",\n       pasient=" + pasient.henteNavn() + ",\n       Id=" + Id
				+ ",\n       lege=" + lege.hentLegeNavn() + "]\n";
	}
	
}
