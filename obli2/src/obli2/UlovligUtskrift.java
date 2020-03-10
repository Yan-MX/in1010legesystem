package obli2;
//??????
@SuppressWarnings("serial")
public class UlovligUtskrift extends Exception{
UlovligUtskrift(Lege l, Legemiddel lm){
super("Legen "+l.hentLegeNavn()+ "har ikke lov til å skrive ut "+ lm.hentNavn());
}
}