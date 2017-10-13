package busqueda.local;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Gasolineras;

public class Distribuidora {
    private static CentrosDistribucion centrosDistribucion;
    private static Gasolineras gasolineras;
    private float distanceAmount;
    private float revenues;
    private float expenses;

    public Distribuidora(int ncen, int mult, int ngas, int seed) {
        centrosDistribucion = new CentrosDistribucion(ncen, mult, seed);
        gasolineras = new Gasolineras(ngas, seed);
        distanceAmount = 0;
        revenues = 0;
        expenses = 0;
    }
}