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

    public Central(int ncen, int mult, int ngas, int seed) {
        centrosDistribucion = new CentrosDistribucion(ncen, mult, seed);
        gasolineras = new Gasolineras(ngas, seed);
        camiones = new ArrayList<>();
    }

    public Central(Central central){
        this.gasolineras = central.gasolineras;
        this.camiones = central.camiones;
    }

    public void solucion1() {
        for (Distribucion distribucion : centrosDistribucion) {
            camiones.add(new Camion(distribucion));
        }
    }

    //desplazamos todos los camiones a alguna gasolinera
    public void solucion2(){
        solucion1();
        for (Camion camion : camiones)
            break;
    }

    //desplazamos numCamiones a alguna gasolinera
    public void solucion3(int numCamiones){
        solucion1();
        for (Camion camion : camiones)
            break;
    }

    public double getBeneficiosNetos() {
        double beneficioNeto = 0;
        for (Camion camion : camiones)
            beneficioNeto += camion.getBeneficiosNetos();
        return beneficioNeto;
    }

    public Gasolineras getGasolineras() {
        return gasolineras;
    }

    public List<Camion> getCamiones(){
        return camiones;
    }

    public void atenderPeticion(int indexCamion, int indexGasolinera, int numPet){
        Camion camion = camiones.get(indexCamion);
        Gasolinera gasolinera = gasolineras.get(indexGasolinera);
        camion.atenderPeticion(gasolinera, numPet);
        gasolinera.getPeticiones().remove(numPet);
    }

    public void desplazarCamionASuCentroDeDistribucion(int indexCamion) {
        camiones.get(indexCamion).volverAlCentroDeDistribucion();
    }

    public Camion getCamion(int index){
        return camiones.get(index);
    }

    public Gasolinera getGasolinera(int index){
        return gasolineras.get(index);
    }

    public boolean camionPuedeAtenderPeticion(int indexCamion, Gasolinera gasolinera) {
        return camiones.get(indexCamion).puedoAtenderPeticion(gasolinera);
    }
}
