import java.util.*;

public class ListaEventi {
    protected Vector<Evento> v;

    public ListaEventi() {
        v = new Vector<Evento>();
    }

    public void aggiungiEvento(Evento e) {
        v.add(e);
    }

    // Rimuove l'evento SOLO se l'ID corrisponde E l'organizzatore è il proprietario
    public boolean rimuoviEvento(int idEvento, int idOrganizzatore) {
        for (int i = 0; i < v.size(); i++) {
            Evento e = v.get(i);
            if (e.getIdEvento() == idEvento && e.getIdOrganizzatore() == idOrganizzatore) {
                v.remove(i);
                return true;
            }
        }
        return false;
    }

    // Cerca l'evento per ID (utile per acquisto o convalida)
    public Evento getEvento(int idEvento) {
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).getIdEvento() == idEvento) {
                return v.get(i);
            }
        }
        return null;
    }

    // Restituisce una stringa con i soli eventi di un certo organizzatore
    public String toStringPerOrganizzatore(int idOrganizzatore) {
        String s = "--- I TUOI EVENTI ---\n";
        boolean trovato = false;
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).getIdOrganizzatore() == idOrganizzatore) {
                s += v.get(i).toString() + "\n-----------------\n";
                trovato = true;
            }
        }
        if (!trovato) s += "Non hai ancora creato eventi.\n";
        return s;
    }
    
    // Il toString normale rimane utile per i clienti (che vedono tutto)
    public String toString() {
        String s = "";
        for (int i = 0; i < v.size(); i++) {
            s += v.get(i).toString() + "\n-----------------\n";
        }
        return s;
    }
}
