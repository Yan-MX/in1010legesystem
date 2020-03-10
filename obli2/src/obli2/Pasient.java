package obli2;

public class Pasient {
	private String navn;
	private String fodselnummer;
	public int PasientID;
	public Stabel<Resept> sinReseptListe;
	static int counter = 0;
    
	public Pasient(String a, String b) {
		navn = a;
		fodselnummer = b;
		PasientID = counter;
		sinReseptListe = new Stabel<Resept>();
		counter++;
	}
	
	public void leggTilResepter(Resept a) {
		sinReseptListe.leggPaa(a);
	}
	public Resept henteSiste() {
		if(this.sinReseptListe.stoerrelse()>0) {
			return this.sinReseptListe.hent(this.sinReseptListe.stoerrelse()-1);
		}
		//if the stabel is empty:
		else {	
			return null;
		}
	}
	public String henteNavn() {
		return navn;
	}
	public String hentefodselnummer() {
		return fodselnummer;
	}
	@Override
	public String toString() {
		return navn+ "[ ID=" + PasientID + ", fodselnummer=" + fodselnummer +  "]";
	}
	
}
