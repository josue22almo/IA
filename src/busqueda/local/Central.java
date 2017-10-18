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
        camiones = new ArrayList<>();
    }

    public Central(Central central){
        this.gasolineras = central.gasolineras;
        this.camiones = central.camiones;
        this.distanciaRecorrida = central.distanciaRecorrida;
        this.beneficios = central.beneficios;
        this.gastos = central.gastos;
    }

    public void solucion1() {
        for (Distribucion distribucion : centrosDistribucion) {
            camiones.add(new Camion(distribucion.getCoordX(), distribucion.getCoordY()));
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

    public double getBeneficiosNetos() {
        return beneficios - gastos;
    }

    public Gasolineras getGasolineras() {
        return gasolineras;
    }

    public List<Camion> getCamiones(){
        return camiones;
    }

    public void atenderPeticion(Camion camion, Gasolinera gasolinera, int numPet){
        camion.atenderPeticion(gasolinera, numPet);
        beneficios += camion.getBeneficiosNetos();
    }

    public Camion getCamion(int index){
        return camiones.get(index);
    }

    public Gasolinera getGasolinera(int index){
        return gasolineras.get(index);
    }
}
