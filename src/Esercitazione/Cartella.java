package Esercitazione;

import java.util.*;

public class Cartella {

    HashMap<Integer, Boolean> riga_1 = new HashMap<>();
    HashMap<Integer, Boolean> riga_2 = new HashMap<>();
    HashMap<Integer, Boolean> riga_3 = new HashMap<>();
    ArrayList<HashMap> righe_cartella = new ArrayList<>();

    private Giocatore giocatore;

    public Cartella() {
        inizializza_cartella();
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public void setGiocatore(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    private void inizializza_cartella() {
        for (int i = 0; i < 5; i++) {
            riga_1.put(new Random().nextInt(90), false);
            riga_2.put(new Random().nextInt(90), false);
            riga_3.put(new Random().nextInt(90), false);
        }
        righe_cartella.add(riga_1);
        righe_cartella.add(riga_2);
        righe_cartella.add(riga_3);
    }

    public void controlla_numeri_cartella(int numero_estratto) {
        for (HashMap riga : righe_cartella) {
            if (riga.containsKey(numero_estratto)) {
                riga.replace(numero_estratto, true);
                System.out.println(getGiocatore().getNome() + " ha il numero " + numero_estratto);
            }
        }
    }


}

