package busqueda.local;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;
import IA.Gasolina.Gasolineras;

import java.util.ArrayList;
import java.util.Random;

public class Central {
    private Gasolineras gasolineras;
    private static CentrosDistribucion centrosDistribucion;
    private ArrayList<Itinerario> itinerariosCamiones;
    private ArrayList<PeticionAtendida> totalPeticiones;
    private static int numGasolineras;
    private int seed;

    public Central(int ncen, int mult, int ngas, int seed) {
        centrosDistribucion = new CentrosDistribucion(ncen, mult, seed);
        gasolineras = new Gasolineras(ngas, seed);
        itinerariosCamiones = new ArrayList<>();
        numGasolineras = ngas;
        this.seed = seed;
        totalPeticiones = new ArrayList<>();
        generarListaPeticiones();

        for(int i = 0; i < ncen; ++i){
            itinerariosCamiones.add(generarItinerario(centrosDistribucion.get(i), seed));
        }
    }


    //Genera una lista con todas las peticiones de todas las gasolineras
    private void generarListaPeticiones() {
        for(int i = 0; i<gasolineras.size(); ++i){
            Gasolinera gas = gasolineras.get(i);
            for(int numPet = 0; numPet<gas.getPeticiones().size(); ++numPet){
                PeticionAtendida pa = new PeticionAtendida(gas, numPet);
                totalPeticiones.add(pa);
            }
        }
    }



    private Itinerario generarItinerario(Distribucion cd, int seed) {
        Random myRandom = new Random();
        myRandom.setSeed(seed);
        Itinerario it;
        for(int i = 0; i < 4; ++i){
            Viaje v = new Viaje();
            int a, b;
            //a = myRandom.nextInt()
            //
            v.introducirRecorrido(totalPeticiones.);



        }
        return it;
    }
/*
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

*/

    public static CentrosDistribucion getCentrosDistribucion() {
        return centrosDistribucion;
    }


    public double getBeneficiosNetos() {
        double benficios = 0;
        for (Itinerario itinerario : itinerariosCamiones)
            benficios += itinerario.getIngresoTotal() - itinerario.getGastoTotal();
        return benficios;
    }



    //Se debe modificar
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

}
