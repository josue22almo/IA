package busqueda.local;

public class Simulador {

    public static void main(String[] args) {
        int ncen, mult, ngas, seed;
        ncen = 10; mult = 1; ngas = 100; seed = 1234;

        long before = System.currentTimeMillis();
        Distribuidora distribuidora = new Distribuidora(ncen, mult, ngas, seed);
        long after = System.currentTimeMillis();

        System.out.printf("Benefits obtained: %.5f \n", distribuidora.getBenefits());
        System.out.printf("Total time (miliseconds): %d \n",after - before);
    }
}
