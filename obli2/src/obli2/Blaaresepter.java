package obli2;


//subclass of legemiddel
public class Blaaresepter extends Resept {
	public Blaaresepter(Legemiddel a, Lege b, Pasient c, int d) {
		super(a,b,c,d);
	}

	
	public String farge() {
		return "blaa";
	}
	public double prisAaBetale() {
		return legemiddel.hentPris() *.25;
	}
	@Override
	public String toString() {
		return this.hentId() +"Blaaresept [farge = "+ farge() +",\n       legemiddel=" + legemiddel.hentNavn() + ",\n       reit=" + reit + ",\n       pasient=" + pasient.henteNavn() + ",\n       Id=" + Id
				+ ",\n       lege=" + lege.hentLegeNavn() + "]\n";
	}

}
