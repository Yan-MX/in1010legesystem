package obli2;

public class Presept extends HvitResept {
	
	public Presept(Legemiddel a, Lege b, Pasient c, int d) {
		super(a,b,c,3);
	}
	public double prisAaBetale() {
		double pris =legemiddel.hentPris()-108;
		if (pris>0) {
			return pris;
		}
		else {
			return 0;
		}
	}
	
	
	@Override
	public String toString() {
		return this.hentId() +"Presept [farge = "+ farge() +",\n       legemiddel=" + legemiddel.hentNavn() + ",\n       reit=3 ,\n       pasient=" + pasient.henteNavn() + ",\n       Id=" + Id
				+ ",\n       lege=" + lege.hentLegeNavn() + "]\n";
	}
}
