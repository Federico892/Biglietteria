
public class Posto {
	
	protected String fascia;
	protected int idPosto;
	protected boolean isOccupato;
	
	public Posto(String fascia, int idPosto) {
		if (fascia.toLowerCase().equals("executive") || fascia.toLowerCase().equals("premium") || fascia.toLowerCase().equals("standard")) {
			this.fascia=fascia;
		}else {
			this.fascia="standard";
		}
		this.idPosto=idPosto;
		this.isOccupato=false;
	}

	public String getFascia() {
		return fascia;
	}

	public void setFascia(String fascia) {
		this.fascia = fascia;
	}
	
	public int getIdPosto() {
		return idPosto;
	}
	
	public void setIdPosto(int idPosto) {
		this.idPosto=idPosto;
	}
	
	public boolean getOccupato() {
		return isOccupato;
	}
	
	public void setOccupato(boolean valore) {
		this.isOccupato=valore;
	}

	public String toString() {
		return ("Fascia: " + fascia + ", Settore: " + "id Posto: " + idPosto + ", Stato di occupazione: " + isOccupato);
	}
	
	
	
	

}
