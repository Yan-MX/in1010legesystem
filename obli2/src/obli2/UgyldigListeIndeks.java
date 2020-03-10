package obli2;

@SuppressWarnings("serial")
public class UgyldigListeIndeks extends RuntimeException{
	UgyldigListeIndeks( int index){
		super("Ugyldig indeks: " +index);
	}

}
