import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);
    
    static Login sistemaLogin = new Login();
    static ListaEventi sistemaEventi = new ListaEventi();
    static Utente utenteCorrente = null; 

    public static void main(String[] args) {
        
        // Carichiamo dati di prova
        inizializzaDati();

        boolean esegui = true;
        
        while (esegui) {
            System.out.println("\n--- BENVENUTO NEL SISTEMA BIGLIETTERIA ---");
            
            // Loop di Login
            utenteCorrente = null; 
            while (utenteCorrente == null) {
                effettuaLogin();
            }

            System.out.println("\nBenvenuto " + utenteCorrente.getUsername() + "!");
            System.out.println("Ruolo: " + utenteCorrente.getTipo().toUpperCase());

            String ruolo = utenteCorrente.getTipo().toLowerCase();
            
            if (ruolo.equals("amministratore")) {
                menuAmministratore();
            } else if (ruolo.equals("organizzatore")) {
                menuOrganizzatore();
            } else if (ruolo.equals("cliente")) {
                menuCliente();
            } else if (ruolo.equals("sicurezza")) {
                menuSicurezza();
            } else {
                System.out.println("Errore: Ruolo non riconosciuto.");
            }
        }
    }

    // --- GESTIONE LOGIN ---
    public static void effettuaLogin() {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Username: ");
        String user = in.nextLine();
        System.out.print("Password: ");
        String pass = in.nextLine();

        Utente u = sistemaLogin.checkUtente(user, pass);
        
        if (u != null) {
            utenteCorrente = u;
        } else {
            System.out.println("Credenziali errate. Riprova.");
        }
    }

    // --- MENU AMMINISTRATORE ---
    public static void menuAmministratore() {
        boolean attivo = true;
        while (attivo) {
            System.out.println("\n--- MENU AMMINISTRATORE ---");
            System.out.println("1. Crea nuovo utente");
            System.out.println("2. Elimina utente");
            System.out.println("0. Logout");
            System.out.print("Scelta: ");

            int scelta = in.nextInt();
            in.nextLine(); 

            switch (scelta) {
                case 1:
                    creaUtente();
                    break;
                case 2:
                    eliminaUtente();
                    break;
                case 0:
                    attivo = false;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
    }

    // --- MENU ORGANIZZATORE ---
    public static void menuOrganizzatore() {
        boolean attivo = true;
        while (attivo) {
            System.out.println("\n--- MENU ORGANIZZATORE ---");
            System.out.println("1. Crea nuovo evento");
            System.out.println("2. Rimuovi evento (per ID)");
            System.out.println("3. Visualizza Resoconto Evento");
            System.out.println("4. Lista dei miei eventi");
            System.out.println("0. Logout");
            System.out.print("Scelta: ");
            
            int scelta = in.nextInt();
            in.nextLine(); 

            switch (scelta) {
                case 1:
                    creaEvento();
                    break;
                case 2:
                    rimuoviEvento();
                    break;
                case 3:
                    visualizzaResoconto();
                    break;
                case 4:
                	System.out.println(sistemaEventi.toStringPerOrganizzatore(utenteCorrente.idUtente));
                    break;
                case 0:
                    attivo = false;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
    }

    // --- MENU CLIENTE ---
    public static void menuCliente() {
        boolean attivo = true;
        while (attivo) {
            System.out.println("\n--- MENU CLIENTE ---");
            System.out.println("1. Visualizza Eventi Disponibili");
            System.out.println("2. Acquista Biglietto");
            System.out.println("0. Logout");
            System.out.print("Scelta: ");

            int scelta = in.nextInt();
            in.nextLine(); 

            switch (scelta) {
                case 1:
                    System.out.println(sistemaEventi.toString());
                    break;
                case 2:
                    acquistaBiglietto();
                    break;
                case 0:
                    attivo = false;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
    }

    // --- MENU SICUREZZA ---
    public static void menuSicurezza() {
        boolean attivo = true;
        while (attivo) {
            System.out.println("\n--- MENU SICUREZZA ---");
            System.out.println("1. Convalida Biglietto (QR Code)");
            System.out.println("0. Logout");
            System.out.print("Scelta: ");

            int scelta = in.nextInt();
            in.nextLine(); 

            switch (scelta) {
                case 1:
                    convalidaIngresso();
                    break;
                case 0:
                    attivo = false;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
    }

    // --- FUNZIONI AMMINISTRATORE ---

    private static void creaUtente() {
        System.out.println("\n--- CREAZIONE UTENTE ---");
        System.out.print("Username: ");
        String user = in.nextLine();
        System.out.print("Password: ");
        String pass = in.nextLine();
        System.out.print("ID Utente: ");
        int id = in.nextInt();
        in.nextLine(); 
        System.out.print("Tipo (organizzatore, cliente, sicurezza, amministratore): ");
        String tipo = in.nextLine();

        sistemaLogin.aggiungiUtente(new Utente(user, pass, id, tipo));
        System.out.println("Utente creato con successo!");
    }

    private static void eliminaUtente() {
        System.out.println("\n--- ELIMINAZIONE UTENTE ---");
        System.out.print("Username dell'utente da eliminare: ");
        String user = in.nextLine();
        System.out.print("Password dell'utente (per conferma): ");
        String pass = in.nextLine();

        // Si crea un oggetto temporaneo per sfruttare il metodo equals di Utente
        Utente temporaneo = new Utente(user, pass, 0, "cliente");
        sistemaLogin.rimuoviUtente(temporaneo);
    }

    // --- FUNZIONI OPERATIVE EVENTI ---

    private static void creaEvento() {
        System.out.println("\n--- CREAZIONE EVENTO ---");
        System.out.print("Nome Evento: ");
        String nome = in.nextLine();
        
        System.out.print("ID Evento (numero intero univoco): ");
        int idEvento = in.nextInt();
        in.nextLine(); 
  
        System.out.print("Prezzo Executive: ");
        double pExec = in.nextDouble();
        System.out.print("Prezzo Premium: ");
        double pPrem = in.nextDouble();
        System.out.print("Prezzo Standard: ");
        double pStan = in.nextDouble();
        
      
   
        System.out.print("Numero Posti Executive: ");
        int nExec = in.nextInt();
        System.out.print("Numero Posti Premium: ");
        int  nPrem = in.nextInt();
        System.out.print("Numero Posti Standard: ");
        int nStan= in.nextInt();
        in.nextLine(); 
  
       

        Evento nuovo = new Evento(nome, utenteCorrente.idUtente, idEvento, pExec, pPrem, pStan, nExec, nPrem, nStan);
        sistemaEventi.aggiungiEvento(nuovo);
        System.out.println("Evento creato con successo!");
       
       
    }

    private static void rimuoviEvento() {
        System.out.println("\n--- RIMOZIONE EVENTO ---");
        System.out.println(sistemaEventi.toStringPerOrganizzatore(utenteCorrente.idUtente));
        
        System.out.print("Inserisci ID dell'evento da rimuovere: ");
        int id = in.nextInt();
        in.nextLine(); 

        boolean esito = sistemaEventi.rimuoviEvento(id, utenteCorrente.idUtente);
        
        if (esito) {
            System.out.println("Evento rimosso con successo.");
        } else {
            System.out.println("Errore: Evento non trovato o non sei il proprietario.");
        }
    }
    
    private static void visualizzaResoconto() {
        System.out.println(sistemaEventi.toStringPerOrganizzatore(utenteCorrente.idUtente));
        System.out.print("Inserisci ID Evento per il resoconto: ");
        int id = in.nextInt();
        in.nextLine(); 

        Evento e = sistemaEventi.getEvento(id);
        
        if(e != null && e.getIdOrganizzatore() == utenteCorrente.idUtente) {
            System.out.println(e.resoconto());
        } else {
            System.out.println("Errore: Evento non trovato o permessi insufficienti.");
        }
    }

    private static void acquistaBiglietto() {
        System.out.print("Inserisci ID dell'evento a cui vuoi partecipare: ");
        int id = in.nextInt();
        in.nextLine(); 

        Evento e = sistemaEventi.getEvento(id);
        
        if (e != null) {
            System.out.print("Seleziona fascia (executive, premium, standard): ");
            String fascia = in.nextLine();
            
            Biglietto b = e.acquistaBiglietto(fascia, utenteCorrente.idUtente);
            
            if (b != null) {
                System.out.println("Acquisto Riuscito!");
                System.out.println("Dettagli: " + b.toString());
                System.out.println("QR CODE: " + b.getQRcode()); 
            } else {
                System.out.println("Acquisto fallito: posti esauriti o fascia inesistente.");
            }
        } else {
            System.out.println("Evento non esistente.");
        }
    }

    private static void convalidaIngresso() {
        System.out.print("Inserisci ID Evento da controllare: ");
        int id = in.nextInt();
        in.nextLine(); 

        Evento e = sistemaEventi.getEvento(id);
        
        if (e != null) {
            System.out.print("Inserisci stringa QR Code del cliente: ");
            String qr = in.nextLine();
            
            int esito = e.convalidaBiglietto(qr);
            
            if (esito == 0) {
                System.out.println(">>> ACCESSO CONSENTITO");
            } else if (esito == 100) {
                System.out.println("!!! ATTENZIONE: Biglietto GIÀ usato !!!");
            } else {
                System.out.println("XXX ERRORE: Biglietto non valido XXX");
            }
        } else {
            System.out.println("Evento non trovato.");
        }
    }

    // --- POPOLAMENTO DATI INIZIALI ---
    private static void inizializzaDati() {
        // Aggiungiamo un admin per poter gestire il sistema dall'inizio
        sistemaLogin.aggiungiUtente(new Utente("admin", "admin", 0, "amministratore"));
        sistemaLogin.aggiungiUtente(new Utente("boss", "123", 1, "organizzatore"));
        sistemaLogin.aggiungiUtente(new Utente("mario", "1234", 2, "cliente"));
        sistemaLogin.aggiungiUtente(new Utente("guardia", "pass", 3, "sicurezza"));
        
        Evento concerto = new Evento("Concerto Rock", 1, 100, 50.0, 30.0, 20.0, 5, 5, 5);
        sistemaEventi.aggiungiEvento(concerto);
    }
}
