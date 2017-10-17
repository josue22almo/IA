package busqueda.local;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;
import IA.Gasolina.Gasolineras;

import java.util.ArrayList;
import java.util.List;

public class Central {
    private Gasolineras gasolineras;
    private static CentrosDistribucion centrosDistribucion;
    private List<Camion> camiones;
    private double distanceAmount;
    private double revenues;
    private double expenses;

    public Central(int ncen, int mult, int ngas, int seed) {
        centrosDistribucion = new CentrosDistribucion(ncen, mult, seed);
        gasolineras = new Gasolineras(ngas, seed);
        distanceAmount = 0;
        revenues = 0;
        expenses = 0;
        fillCamiones();

    }

    private void fillCamiones() {
        camiones = new ArrayList<>();
        for (Distribucion distribucion : centrosDistribucion) {
            camiones.add(new Camion(distribucion.getCoordX(), distribucion.getCoordY()));
        }
    }

    public void solucion1() {
        for (Camion camion: camiones){

        }
    }

    public double getDistanceAmount() {
        return distanceAmount;
    }

    public double getRevenues() {
        return revenues;
    }

    public double getExpenses() {
        return expenses;
    }

    public double getBenefits() {
        return revenues - expenses;
    }

    public Gasolineras getGasolineras() {
        return gasolineras;
    }

    public List<Camion> getCamiones(){
        return camiones;
    }

    public void desplazarCamionAGasolinera(int camionI, int gasolineraJ){
        Camion camion = camiones.get(camionI);
        Gasolinera gasolinera = gasolineras.get(gasolineraJ);
        double distanciaRecorrida = camion.distanceTo(gasolinera);
    }

    private double calcularDistancia(int oriX, int oriY, int destX, int destY){
        return Math.sqrt((oriX-destX)^2+(oriY-destY)^2);
    }
}
