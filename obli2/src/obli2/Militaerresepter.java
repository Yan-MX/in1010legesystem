package obli2;

public class Militaerresepter extends HvitResept{
	public Militaerresepter(Legemiddel a, Lege b, Pasient c, int d) {
		super(a,b,c,d);
	}
	public double prisAaBetale() {
		return 0;
	}

	@Override
	public String toString() {
		return this.hentId() +"Militaerresepter [farge = "+ farge() +",\n       legemiddel=" + legemiddel.hentNavn() + ",\n       reit=" + reit + ",\n       pasient=" + pasient.henteNavn() + ",\n       Id=" + Id
				+ ",\n       lege=" + lege.hentLegeNavn() + "]\n";
	}
	
}