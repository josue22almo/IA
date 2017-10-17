package busqueda.local;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;
import IA.Gasolina.Gasolineras;

import java.util.ArrayList;
import java.util.List;

public class Central {
    private static Gasolineras gasolineras;
    private static CentrosDistribucion centrosDistribucion;
    private List<Camion> camiones;
    private float distanceAmount;
    private float revenues;
    private float expenses;

    public Central(int ncen, int mult, int ngas, int seed) {
        centrosDistribucion = new CentrosDistribucion(ncen, mult, seed);
        gasolineras = new Gasolineras(ngas, seed);
        distanceAmount = 0;
        revenues = 0;
        expenses = 0;
        llenarCamiones();
    }

    private void llenarCamiones() {
        camiones = new ArrayList<>();
        for (Distribucion distribucion : centrosDistribucion) {

        }
    }

    public static void solucion1() {
        for (Distribucion distribucion : centrosDistribucion){

        }
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

    public static Gasolineras getGasolineras() {
        return gasolineras;
    }

    public List<Camion> getCamiones() {
        return camiones;
    }
}
