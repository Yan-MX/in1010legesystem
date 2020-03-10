package obli2;

public class HvitResept extends Resept {
	public HvitResept(Legemiddel a, Lege b, Pasient c, int d) {
		super(a,b,c,d);
	}

	@Override
	public String farge() {
		return "hvit";
	}
	//lack of explanation of how to calculate the cost of hviterespter
	//that is neither military or p.
	public double prisAaBetale() {
		return legemiddel.hentPris();
	}
	@Override
	public String toString() {
		return this.hentId() +"HvitResept [farge = "+ farge() +",\n       legemiddel=" + legemiddel.hentNavn() + ",\n       reit=" + reit + ",\n       pasient=" + pasient.henteNavn() + ",\n       Id=" + Id
				+ ",\n       lege=" + lege.hentLegeNavn() + "]\n";
	}
}
