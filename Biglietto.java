
public class Biglietto {
	
	protected int idBiglietto;
	protected int idCliente;
	protected int idPosto;
	protected String QRcode;
	protected double prezzo;
	protected boolean isConvalidato;
	
	public Biglietto(int idBiglietto, int idCliente, int idPosto, String QRcode, double prezzo) {
		this.idBiglietto=idBiglietto;
		this.idCliente=idCliente;
		this.idPosto=idPosto;
		this.QRcode=QRcode;
		this.prezzo=prezzo;
		this.isConvalidato=false;
	}

	public int getIdBiglietto() {
		return idBiglietto;
	}

	public void setIdBiglietto(int idBiglietto) {
		this.idBiglietto = idBiglietto;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public int getIdPosto() {
		return idPosto;
	}

	public void setIdPosto(int idPosto) {
		this.idPosto = idPosto;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getQRcode() {
		return QRcode;
	}

	public void setQRcode(String qRcode) {
		QRcode = qRcode;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	public boolean getConvalidato() {
		return isConvalidato;
	}
	
	public void setConvalidato() {
		isConvalidato=true;
	}

	public String toString() {
		return "Biglietto [idBiglietto=" + idBiglietto + ", idCliente=" + idCliente + ", idPosto=" + idPosto
				+ ", QRcode=" + QRcode + ", prezzo=" + prezzo + ", isConvalidato=" + isConvalidato + "]";
	}
	

}
