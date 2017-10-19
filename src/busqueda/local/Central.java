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
    private double distanciaRecorrida;
    private double beneficios;
    private double gastos;

    public Central(int ncen, int mult, int ngas, int seed) {
        centrosDistribucion = new CentrosDistribucion(ncen, mult, seed);
        gasolineras = new Gasolineras(ngas, seed);
        distanciaRecorrida = 0;
        beneficios = 0;
        gastos = 0;
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

    public double getdistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public double getbeneficios() {
        return beneficios;
    }

    public double getgastos() {
        return gastos;
    }

    public double getBenefits() {
        return beneficios - gastos;
    }

    public Gasolineras getGasolineras() {
        return gasolineras;
    }

    public List<Camion> getCamiones(){
        return camiones;
    }

    public void atenderPeticion(int camionI, int gasolineraJ, int numPet){
        Camion camion = camiones.get(camionI);
        Gasolinera gasolinera = gasolineras.get(gasolineraJ);
        camion.atenderPeticion(gasolinera, numPet);
    }
}
