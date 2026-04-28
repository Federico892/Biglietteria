
public class Utente {
	
	protected String username;
	protected String password;
	protected String tipo;
	protected int idUtente;
	
	public Utente(String username, String password, int  idUtente, String tipo) {
		this.username=username;
		this.password=password;
		this.idUtente=idUtente;
		if (   tipo.toLowerCase().equals("organizzatore") 
			|| tipo.toLowerCase().equals("cliente") 
			|| tipo.toLowerCase().equals("sicurezza") 
			|| tipo.toLowerCase().equals("amministratore"))
		{
			this.tipo=tipo;
		}else {
			this.tipo="errore";
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public boolean equals(Object o) {
		if (o instanceof Utente) {
			Utente u=(Utente)o;
			if (this.username.equals(u.username)) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return ("Username: "+ username + ", Password: " + password + ", Tipo: " + tipo);  
	}
	
}
