import java.util.*;

//Classe per la gestione degli utenti

public class Login {
	
	protected Vector<Utente> v;
	
	public Login () {
		v=new Vector(3);
	}
	
	public void aggiungiUtente(Utente u) {
		for (int i=0;i<v.size();i++) {
			if (v.get(i).equals(u)) {
				System.out.println("ERRORE! Utente già presente!");
				return;
			}
		}
		
		v.add(u);
	}
	
	public void rimuoviUtente(Utente u) {
		for (int i=0;i<v.size();i++) {
			if (v.get(i).equals(u)) {
				v.remove(i);
				System.out.println("L'utente è stato eliminato con successo! ");
				return;
			}
		}
		System.out.println("ERRORE! Non è presente nessun utente oppure l'utente inserito non è presente!");
	}
	
	public Utente checkUtente(String username, String password) {
		for (int i=0;i<v.size();i++) {
			if (v.get(i).getUsername().equals(username) && v.get(i).getPassword().equals(password)) {
				return v.get(i);
			}
		}
		return null;
	}
	
}
