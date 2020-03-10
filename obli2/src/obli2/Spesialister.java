package obli2;

public class Spesialister extends Lege implements Godkjenningsfritak{
	private int kontrollID;
	public Spesialister(String a, int b) {
		super(a);
		kontrollID = b;
	}
	@Override
	public String toString() {
		return navn +"    Specialist: YES " +"[kontrollID = " +kontrollID+ "]";
	}
	
	@Override
	public int hentKontrollID() {
		return kontrollID;
	}
	
}
