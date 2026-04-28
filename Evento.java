
import java.util.*;

public class Evento {
	
	protected String nome;
	protected int idOrganizzatore;
	protected int idEvento;
	protected double pFExecutive;
	protected double pFPremium;
	protected double pFStandard;
	protected Posto[] vP;
	protected Vector<Biglietto> vB;
	
	public Evento(String nome, int idOrganizzatore, int idEvento, double pFExecutive, double pFPremium, double pFStandard,int nExecutive, int nPremium, int nStandard) {
		this.nome=nome;
		this.idOrganizzatore=idOrganizzatore;
		this.idEvento=idEvento;
		if (pFExecutive>0) {
			this.pFExecutive=pFExecutive;
		}else {
			this.pFExecutive=50;
		}
		if (pFPremium>0) {
			this.pFPremium=pFPremium;
		}else {
			this.pFPremium=30;
		}
		if (pFStandard>0) {
			this.pFStandard=pFStandard;
		}else {
			this.pFStandard=10;
		}
		if (nExecutive<=0) {
			nExecutive=10;
		}
		if (nPremium<=0) {
			nPremium=10;
		}
		if (nStandard<=0) {
			nStandard=10;
		}
		vB=new Vector();
		creaPosti(nExecutive,nPremium,nStandard);
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getIdOrganizzatore() {
		return idOrganizzatore;
	}
	
	public int getIdEvento() {
		return idEvento;
	}
	
	public double getpFExecutive() {
		return pFExecutive;
	}

	public double getpFPremium() {
		return pFPremium;
	}

	public double getpFStandard() {
		return pFStandard;
	}
	
	public double getPFascia(String fascia) {
		if (fascia.toLowerCase().equals("executive")) {
			return pFExecutive;
		}else if(fascia.toLowerCase().equals("premium")) {
			return pFPremium;
		}else if (fascia.toLowerCase().equals("standard")) {
			return pFStandard;
		}else {
			return -1;
		}
	}

	public void creaPosti(int nExecutive, int nPremium, int nStandard) {
	    // 1. Inizializzazione dell'array con la somma totale dei posti
	    vP = new Posto[nExecutive + nPremium + nStandard];
	    
	    int k = 0; // Indice progressivo per il vettore vP

	    // Ciclo per i posti Executive
	    for (int i = 0; i < nExecutive; i++) {
	        vP[k] = new Posto("executive", k);
	        k++;
	    }

	    // Ciclo per i posti Premium
	    for (int i = 0; i < nPremium; i++) {
	        vP[k] = new Posto("premium", k);
	        k++;
	    }

	    // Ciclo per i posti Standard
	    for (int i = 0; i < nStandard; i++) {
	        vP[k] = new Posto("standard", k);
	        k++;
	    }
	}
	
	public String toString() {
		String s="Evento: " + idEvento + " | " + nome +
				"\n idOrganizzatore: " + idOrganizzatore +
				",  sono disponibili i seguenti biglietti: \n"+
				" Executive: " + pFExecutive + "\n" +
				" Premium: " + pFPremium + "\n" +
				" Standard: " + pFStandard + "\n" ;
		return s;
	}
		
	public Biglietto acquistaBiglietto(String fascia, int idCliente) {
		for (int i=0;i<vP.length;i++) {
			if (vP[i].getFascia().equals(fascia) && vP[i].isOccupato==false) {
				if (getPFascia(vP[i].getFascia())==-1) {
					System.out.println("ERRORE! Fascia inserita non valida");
					return null;
				}
				String s=this.nome+";"+vB.size();
				vB.add(new Biglietto(vB.size(), idCliente, i, s, getPFascia(vP[i].getFascia())));
				vP[i].setOccupato(true);
				return vB.get(vB.size()-1);
			}
		}
		return null;
	}
	
	
	//0 convalidato
	//100 è stato già convalidato
	//200 non esiste
	public int convalidaBiglietto(String QRcode) {
		for (int i=0;i<vB.size();i++) {
			if (vB.get(i).getQRcode().equals(QRcode)){
				if (vB.get(i).getConvalidato()==false) {
					vB.get(i).setConvalidato();
					return 0;
				}else {
					return 100;
				}
			}
		}
		return 200;
	}
	
	public String resoconto() {
		double prezzoExecutive=0;
		double prezzoPremium=0;
		double prezzoStandard=0;
		int cExecutive=0;
		int cPremium=0;
		int cStandard=0;
		
		
		for (int i=0;i<vB.size();i++) {
			if (vP[vB.get(i).idPosto].getFascia()=="executive") {
				prezzoExecutive+=vB.get(i).getPrezzo();
			}else if (vP[vB.get(i).idPosto].getFascia()=="premium") {
				prezzoPremium+=vB.get(i).getPrezzo();
			}else {
				prezzoStandard+=vB.get(i).getPrezzo();
			}			
		}
		
		for (int i=0;i<vP.length;i++) {
			if (vP[i].getOccupato()==false) {
				if (vP[i].getFascia()=="executive") {
					cExecutive++;
				}else if (vP[i].getFascia()=="premium") {
					cPremium++;
				}else {
					cStandard++;
				}	
			}
		}
		
		return ("RESOCONTO BIGLIETTI VENDUTI\n"+
				"===========================\n\n"+
				"- Executive: " + prezzoExecutive +"\n "
				+ "- Premium: " + prezzoPremium +"\n "
				+ "-Standard: " + prezzoStandard +"\n"
				+ "\n\nRESOCONTO POSTI LIBERI\n"
				+ "======================\n\n"
				+ "- Executive: " + cExecutive +"\n"
				+ "- Premium: " + cPremium +"\n"
				+ "- Standard: " + cStandard +"\n");
	}
	
	public boolean equals(Object o) {
		if (o instanceof Evento) {
			Evento e=(Evento)o;
			if (this.nome.equals(e.nome) && this.idOrganizzatore==e.idOrganizzatore) {
				return true;
			}
		}
		return false;
	}
	
	

}
