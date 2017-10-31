package busqueda.local;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;
import IA.Gasolina.Gasolineras;

import java.util.ArrayList;
import java.util.Random;

public class CentralOld {
    private Gasolineras gasolineras;
    private static CentrosDistribucion centrosDistribucion;
    private ArrayList<Camion> camiones;
    private static int numGasolineras;
    private int seed;

    public CentralOld(int ncen, int mult, int ngas, int seed) {
        centrosDistribucion = new CentrosDistribucion(ncen, mult, seed);
        gasolineras = new Gasolineras(ngas, seed);
        camiones = new ArrayList<>();
        colocarCamiones();
        numGasolineras = ngas;
        this.seed = seed;

        for(int camionI = 0; camionI < camiones.size(); ++camionI){
            generarItinerario(camiones.get(camionI), seed);
        }
    }

    private void generarItinerario(Camion camion, int seed) {
        Random myRandom = new Random();
        myRandom.setSeed(seed);
        for(int i = 0; i < 4; ++i){
            Gasolinera gasol = gasolineras.get(myRandom.nextInt(99));
            Integer numPet = gasol.getPeticiones().get(myRandom.nextInt(gasol.getPeticiones().size()-1));




        }
    }

    public CentralOld(CentralOld central) {
        this.gasolineras = central.gasolineras;
        copiarCapiones(central);
        copiarGasolineras(central);
    }

    private void copiarGasolineras(CentralOld central) {
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

    private void copiarCapiones(CentralOld central) {
        camiones = new ArrayList<>();
        for (Camion camion : central.camiones)
            camiones.add(new Camion(camion));
    }



    private void colocarCamiones() {
        for (Distribucion distribucion : centrosDistribucion) {
            camiones.add(new Camion(distribucion));
        }
    }


    public static CentrosDistribucion getCentrosDistribucion() {
        return centrosDistribucion;
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
        Camion camion = camiones.get(indexCamion);
        Gasolinera gasolinera = gasolineras.get(indexGasolinera);
        camion.atenderPeticion(gasolinera, numPet);
        gasolinera.getPeticiones().remove(numPet);
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
}