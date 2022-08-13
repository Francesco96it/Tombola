package Esercitazione;


import java.util.ArrayList;
import java.util.Random;


public final class Banco {
    private final Random random;
    private final ArrayList<Integer> numeri_estratti = new ArrayList<>(90);

    public Boolean ambo = false;
    public Boolean terno = false;
    public Boolean quaterna = false;
    public Boolean cinquina = false;
    public Boolean tombola = false;

    public Banco() {
        random = new Random();
        generaNumeri();
    }

    public void generaNumeri() {
        for (int i = 1; i < 90; i++) {
            numeri_estratti.add(i);
        }
    }

    public Integer estrai_numero() {
        Integer num_estratto = numeri_estratti.get(random.nextInt(numeri_estratti.size()));
        numeri_estratti.remove(num_estratto);
        return num_estratto;
    }

    public Boolean numeri_terminati() {
        return numeri_estratti.isEmpty();
    }
}
