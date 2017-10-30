package busqueda.local;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;
import IA.Gasolina.Gasolineras;

import java.util.*;
import java.util.stream.Collectors;

public class Central {
    private Gasolineras gasolineras;
    private static CentrosDistribucion centrosDistribucion;
    private ArrayList<Camion> camiones;
    private static int numGasolineras;
    private int seed;

    public Central(int ncen, int mult, int ngas, int seed) {
        centrosDistribucion = new CentrosDistribucion(ncen, mult, seed);
        gasolineras = new Gasolineras(ngas, seed);
        camiones = new ArrayList<>();
        numGasolineras = ngas;
        this.seed = seed;
    }

    public Central(Central central) {
        this.gasolineras = central.gasolineras;
        copiarCapiones(central);
        copiarGasolineras(central);
    }

    private void copiarGasolineras(Central central) {
        gasolineras = new Gasolineras(numGasolineras, seed);
        gasolineras.clear();
        for (Gasolinera gasolinera : central.gasolineras) {
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
    public void aplicarSolucion2() {
        aplicarSolucion1();
        int i = 0;
        int numGasolineras = gasolineras.size();
        for (Camion camion : camiones) {
            Gasolinera gasolinera = gasolineras.get(i % numGasolineras);
            camion.atenderMaxPeticiones(gasolinera);
            ++i;
        }
    }

    public static CentrosDistribucion getCentrosDistribucion() {
        return centrosDistribucion;
    }

    //desplazamos numCamiones a alguna gasolinera
    public void aplicarSolucion3(int numCamiones) {
        aplicarSolucion1();
        int i = camiones.size() - 1;
        while (i >= 0  && numCamiones >= 0) {
            int indexG = new Random().nextInt(gasolineras.size()-1);
            Gasolinera gasolinera = gasolineras.get(indexG);
            camiones.get(i).atenderMaxPeticiones(gasolinera);
            --i; --numCamiones;
        }
    }

    public double getBeneficiosNetos() {
        double benficios = 0;
        for (Camion camion : camiones)
            benficios += camion.getBeneficiosNetos();
        return benficios;
    }


    public double getMaximosBeneficiosActuales() {
        double maxben = 0;
        for (Gasolinera gasolinera : gasolineras) {
            ArrayList peticiones = gasolinera.getPeticiones();
            for (int i = 0; i < peticiones.size(); ++i) {
                int dias = (int) peticiones.get(i);
                if (dias == 0) maxben += 1000 * 1.02;
                else maxben += 1000 * ((100 - Math.pow(2, dias)) / 100);
            }
        }
        return maxben;
    }

    public double getPerdidasDiaSiguiente() {
        double perdidas = 0;
        for (Gasolinera gasolinera : gasolineras) {
            ArrayList peticiones = gasolinera.getPeticiones();
            for (int i = 0; i < peticiones.size(); ++i) {
                double actual;
                int dias = (int) peticiones.get(i);
                if (dias == 0) actual = 1000 * 1.02;
                else actual = 1000 * ((100 - Math.pow(2, dias)) / 100);

                double nuevo = 1000 * ((100 - Math.pow(2, dias + 1)) / 100);
                perdidas += actual - nuevo;
            }
        }
        return perdidas;
    }

    public double getTotalIngresos() {
        double ingresosTotales = 0;
        for (Camion camion : camiones) ingresosTotales += camion.getIngresos();
        return ingresosTotales;
    }

    public double getTotalGastos() {
        double gastosTotales = 0;
        for (Camion camion : camiones) gastosTotales += camion.getGastos();
        return gastosTotales;
    }

    public Gasolineras getGasolineras() {
        return gasolineras;
    }

    public ArrayList<Camion> getCamiones() {
        return camiones;
    }

    public void atenderPeticion(int indexCamion, int indexGasolinera, int numPet) {
        if (numPet >= 0 && numPet < gasolineras.get(indexGasolinera).getPeticiones().size()){
            Camion camion = camiones.get(indexCamion);
            Gasolinera gasolinera = gasolineras.get(indexGasolinera);
            camion.atenderPeticion(gasolinera, numPet);
        }
    }

    public void desplazarCamionASuCentroDeDistribucion(int indexCamion) {
        camiones.get(indexCamion).volverAlCentroDeDistribucion();
    }

    public Camion getCamion(int index) {
        return camiones.get(index);
    }

    public Gasolinera getGasolinera(int index) {
        return gasolineras.get(index);
    }

    public boolean camionPuedeAtenderPeticion(int indexCamion, Gasolinera gasolinera) {
        return camiones.get(indexCamion).puedoAtenderPeticion(gasolinera);
    }

    public double getDistanciaRecorrida() {
        double distanciaRecorrida = 0.f;
        for (Camion camion : camiones)
            distanciaRecorrida += camion.getDistanciaRecorrida();
        return distanciaRecorrida;
    }

    public void setCosteKM(int coste){
        camiones.get(0).setCosteKM(coste);
    }

    public int getPeticionesAtendidas() {
        int peticiones = 0;
        for (Camion camion : camiones)
            peticiones += camion.getPeticionesAtendidas();
        return peticiones;
    }

    public void setDistanciaTotal(int distanciaTotal) {
        Camion.DISTANCIAINICIAL = distanciaTotal;
    }

    public boolean camionPuedeViajear(int camionI, Gasolinera gasolinera) {
        return camiones.get(camionI).puedoAtenderPeticion(gasolinera) && gasolinera.getPeticiones().size() > 0;
    }

    public void realizarViaje(int camionI, int k) {
        Camion camion = camiones.get(camionI);
        camion.atenderMaxPeticiones(gasolineras.get(k));
        camion.volverAlCentroDeDistribucion();
    }

    public ArrayList<Tuple<Integer,Integer>> getPosiblesViajesCamion(int camionI) {
        Camion camion = camiones.get(camionI);
        ArrayList<Tuple<Integer,Integer>> viajes = new ArrayList<>();
        Map<String, Tuple<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < numGasolineras; ++i){
            Gasolinera gasolinera1 = gasolineras.get(i);
            for (int j = 0; j < numGasolineras && gasolinera1.getPeticiones().size() > 0; ++j){
                Gasolinera gasolinera2 = gasolineras.get(j);
                String key = String.valueOf(Math.min(i,j)) + String.valueOf(Math.max(i,j));
                if (i != j && camion.posibleViaje(gasolinera1, gasolinera2) && !map.keySet().contains(key))
                    map.put(key, new Tuple(i,j));
            }
        }
        return new ArrayList<>(map.values());
    }

    public void realizarViaje(int camion, Tuple<Integer, Integer> viaje){
        camiones.get(camion).atenderPeticion(gasolineras.get(viaje.x), mejorPeticion(viaje.x));
        camiones.get(camion).atenderPeticion(gasolineras.get(viaje.y), mejorPeticion(viaje.y));
        camiones.get(camion).volverAlCentroDeDistribucion();
    }

    private int mejorPeticion(Integer x) {
        int index = -1;
        int min = 100;
        for (int i = 0; i < gasolineras.get(x).getPeticiones().size(); ++i){
            if (gasolineras.get(x).getPeticiones().get(i) < min){
                min = gasolineras.get(x).getPeticiones().get(i);
                index = i;
            }
        }
        return index;
    }
}
