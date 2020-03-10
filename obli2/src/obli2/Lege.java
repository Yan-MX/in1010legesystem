package obli2;

public class Lege implements Comparable<Lege> {
	protected String navn;
	private Lenkeliste<Resept> utskrevedeResepter = new Lenkeliste<Resept>();
	public Lege(String a) {
		navn = a;
	}
	public String hentLegeNavn() {
		return navn;
	}
	@Override
	public String toString() {
		return navn ;
	}
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Lege)) {
			return false;
		}
		
		Lege s = (Lege) o;
		return s.navn == this.navn;
	}
	@Override
	public int compareTo(Lege o) {
		return this.hentLegeNavn().compareTo(o.hentLegeNavn());
		
	}
	
	public HvitResept skrivHvitResept(Legemiddel g, Pasient a, int reit) throws UlovligUtskrift {
		if (g instanceof Narkotisk && this instanceof Spesialister == false) {
			/*System.out.println("invalid legemeddel index :"+g.hentId());
			System.out.println("invalid legemiddel name:" +g.hentNavn());
			System.out.println("invalid legemiddel sort:" +g.getClass().getSimpleName() );*/
			throw new UlovligUtskrift(this, g);
		} else {
			HvitResept n = new HvitResept(g, this, a, reit);
			this.utskrevedeResepter.leggTil(n);
			a.leggTilResepter(n);
			return n;
		}
	}

	public Militaerresepter skrivMillitaerResept(Legemiddel g, Pasient a, int reit) throws UlovligUtskrift {
		if (g instanceof Narkotisk && this instanceof Spesialister == false) {
			/*System.out.println("invalid legemeddel index :"+g.hentId());
			System.out.println("invalid legemiddel name:" +g.hentNavn());
			System.out.println("invalid legemiddel sort:" +g.getClass().getSimpleName() );*/
			throw new UlovligUtskrift(this, g);
		} else {
			Militaerresepter n = new Militaerresepter(g, this, a, reit);
			this.utskrevedeResepter.leggTil(n);
			a.leggTilResepter(n);
			return n;
		}
	}
	public Presept skrivPresept(Legemiddel g, Pasient a)throws UlovligUtskrift {
		if (g instanceof Narkotisk && this instanceof Spesialister == false) {
			/*System.out.println("invalid legemeddel index :"+g.hentId());
			System.out.println("invalid legemiddel name:" +g.hentNavn());
			System.out.println("invalid legemiddel sort:" +g.getClass().getSimpleName() );*/
			throw new UlovligUtskrift(this, g);
		} else {
			Presept n = new Presept(g, this, a, 0);
			this.utskrevedeResepter.leggTil(n);
			a.leggTilResepter(n);
			return n;
		}
	}
	public Blaaresepter skrivBlaaResept(Legemiddel g, Pasient a, int reit) throws UlovligUtskrift {
		if (g instanceof Narkotisk && this instanceof Spesialister == false) {
			/*System.out.println("invalid legemeddel index :"+g.hentId());
			System.out.println("invalid legemiddel name:" +g.hentNavn());
			System.out.println("invalid legemiddel sort:" +g.getClass().getSimpleName() );*/
			throw new UlovligUtskrift(this, g);
		} else {
			Blaaresepter n = new Blaaresepter(g, this, a, reit);
			this.utskrevedeResepter.leggTil(n);
			a.leggTilResepter(n);
			return n;
		}
	}
	public Lenkeliste<Resept> henteLegeUtskrevendeResept(){
		return this.utskrevedeResepter;
	}
	
	
}
