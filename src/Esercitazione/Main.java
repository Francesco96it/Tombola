package Esercitazione;

import java.util.*;

public class Main {
    static Banco banco;
    static ArrayList<Giocatore> giocatori = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        banco = new Banco();
        scegliNumeroGiocatori();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!banco.numeri_terminati()) {
                    int num_estratto = banco.estrai_numero();
                    System.out.println("Numero estratto: " + num_estratto);
                    for (Giocatore giocatore :
                            giocatori) {
                        giocatore.getCartelle().forEach(cartella -> {
                            cartella.controlla_numeri_cartella(num_estratto);
                            controlla_vincite(cartella);
                            controlla_tombola(cartella);
                            if (banco.tombola) {
                                this.cancel();
                                System.out.println("Partita terminata!");
                                System.exit(-1);
                            }
                        });

                    }
                } else {
                    System.out.println("Numeri terminati");
                    this.cancel();
                }
            }
        }, 0, 500);
    }


    public static void scegliNumeroGiocatori() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Scegli il numero dei partecipanti: ");

        int num = scan.nextInt();

        for (int i = 0; i < num; i++) {
            giocatori.add(new Giocatore());
            System.out.print("Scegli il nome del giocatore " + (i + 1) + ":");
            String nome = scan.next();
            giocatori.get(i).setNome(nome);
            System.out.print("Quante cartelle vuoi assegnare a " + nome + "?");
            assegnaNumeroCartelle(i);
        }
        scan.close();
    }

    public static void assegnaNumeroCartelle(int id) {
        int numCartelle;
        try {
            numCartelle = scan.nextInt();
            System.out.println("Giocatore Creato");
            giocatori.get(id).assegnaCartelle(numCartelle);
        } catch (InputMismatchException e) {
            System.out.println("Devi inserire un numero!");
            System.out.print("Quante cartelle vuoi assegnare a " + giocatori.get(id).getNome() + "?");
            scan.nextLine();
            assegnaNumeroCartelle(id);
        }
    }

    static void controlla_vincite(Cartella cartella) {
        for (HashMap riga :
                cartella.righe_cartella
        ) {
            switch ((int) riga.values().stream().filter(v -> (boolean) v).count()) {
                case 2:
                    if (!banco.ambo) {
                        banco.ambo = true;
                        System.out.println(cartella.getGiocatore().getNome() + " ha fatto ambo");
                    }
                    break;
                case 3:
                    if (!banco.terno) {
                        banco.terno = true;
                        System.out.println(cartella.getGiocatore().getNome() + " ha fatto terno");
                    }
                    break;
                case 4:
                    if (!banco.quaterna) {
                        banco.quaterna = true;
                        System.out.println(cartella.getGiocatore().getNome() + " ha fatto quaterna");
                    }
                    break;
                case 5:
                    if (!banco.cinquina) {
                        banco.cinquina = true;
                        System.out.println(cartella.getGiocatore().getNome() + " ha fatto cinquina");
                    }
                    break;
            }
        }
    }

    static void controlla_tombola(Cartella cartella) {
        Boolean riga_1 = (int) cartella.riga_1.values().stream().filter(v -> v).count() == 5;
        Boolean riga_2 = (int) cartella.riga_2.values().stream().filter(v -> v).count() == 5;
        Boolean riga_3 = (int) cartella.riga_3.values().stream().filter(v -> v).count() == 5;
        if (riga_1 && riga_2 && riga_3) {
            banco.tombola = true;
            System.out.println(cartella.getGiocatore().getNome() + " ha fatto tombola!!!!");
        }
    }

}
