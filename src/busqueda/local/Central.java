package busqueda.local;

import IA.Gasolina.Gasolineras;

public class Central {
    private static MCentrosDistribucion centrosDistribucion;
    private static Gasolineras gasolineras;
    private float distanceAmount;
    private float revenues;
    private float expenses;

    public Central(int ncen, int mult, int ngas, int seed) {
        centrosDistribucion = new MCentrosDistribucion(ncen, mult, seed);
        gasolineras = new Gasolineras(ngas, seed);
        distanceAmount = 0;
        revenues = 0;
        expenses = 0;
    }

    public float getDistanceAmount() {
        return distanceAmount;
    }

    public float getRevenues() {
        return revenues;
    }

    public float getExpenses() {
        return expenses;
    }

    public float getBenefits() {
        return revenues - expenses;
    }

    public static MCentrosDistribucion getCentrosDistribucion() {
        return centrosDistribucion;
    }

    public static Gasolineras getGasolineras() {
        return gasolineras;
    }
}
