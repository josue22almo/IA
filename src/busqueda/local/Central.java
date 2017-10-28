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
    private int numGasolineras;
    private int seed;
    private int mult;

    public Central(int ncen, int mult, int ngas, int seed) {
        centrosDistribucion = new CentrosDistribucion(ncen, mult, seed);
        gasolineras = new Gasolineras(ngas, seed);
        camiones = new ArrayList<>();
        numGasolineras = ngas;
        this.mult = mult;
        this.seed = seed;
    }

    public Central(Central central){
        this.gasolineras = central.gasolineras;
        copiarCapiones(central);
        copiarGasolineras(central);
    }

    private void copiarGasolineras(Central central) {
        gasolineras = new Gasolineras(numGasolineras, seed);
        gasolineras.clear();
        for (Gasolinera gasolinera : central.gasolineras){
            int coordX = gasolinera.getCoordX();
            int coordY = gasolinera.getCoordY();
            ArrayList<Integer> peticiones = new ArrayList<>();
            for (int peticion : gasolinera.getPeticiones())
                peticiones.add(peticion);
            gasolineras.add(new Gasolinera(coordX, coordY, peticiones));
        }
    }

    private void copiarCapiones(Central central) {
        camiones = new ArrayList<>();
        for (Camion camion : central.camiones)
            camiones.add(new Camion(camion));
    }

    public void aplicarSolucion1() {
        for (Distribucion distribucion : centrosDistribucion) {
            camiones.add(new Camion(distribucion));
        }
    }

    //desplazamos todos los camiones a alguna gasolinera
    public void aplicarSolucion2(){
        aplicarSolucion1();
        int i = 0;
        int numGasolineras = gasolineras.size();
        for (Camion camion : camiones) {
            Gasolinera gasolinera = gasolineras.get(i % numGasolineras);
            camion.atenderMaxPeticiones(gasolinera);
            ++i;
        }
    }

    //desplazamos numCamiones a alguna gasolinera
    public void aplicarSolucion3(int numCamiones){
        aplicarSolucion1();
        for (Camion camion : camiones)
            break;
    }

    public double getBeneficiosNetos() {
        double beneficioNeto = 0;
        for (Camion camion : camiones){
            if (camion.estoyEnElCentroDistribucion())
                beneficioNeto += camion.getBeneficiosNetos();
        }
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
