package Esercitazione;

import java.util.ArrayList;

public class Giocatore {

    private final ArrayList<Cartella> cartelle = new ArrayList<>();

    private String nome;

    public ArrayList<Cartella> getCartelle() {
        return cartelle;
    }

   public void assegnaCartelle(int quantita){
        for (int i=0; i<quantita; i++){
            Cartella cartella  = new Cartella();
            cartella.setGiocatore(this);
         cartelle.add(cartella);
        }
   }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
