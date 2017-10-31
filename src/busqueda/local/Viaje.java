package busqueda.local;

import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;

import java.util.ArrayList;

public class Viaje {

    private ArrayList<PeticionAtendida> recorrido;
    private double ingresoViaje;
    private double gastoViaje;
    private double distanciaRecorrida;
    private Distribucion centroDistribucion;


    public Viaje() {
        this.recorrido = new ArrayList<>();
        this.ingresoViaje = 0;
        this.gastoViaje = 0;
        this.distanciaRecorrida = 0;

    }

    public Viaje(ArrayList<PeticionAtendida> recorrido, double ingresoViaje, double gastoViaje, double distanciaRecorrida, Distribucion cd) {
        this.recorrido = recorrido;
        this.ingresoViaje = ingresoViaje;
        this.gastoViaje = gastoViaje;
        this.distanciaRecorrida = distanciaRecorrida;
        this.centroDistribucion = cd;
    }

    public void introducirRecorrido(PeticionAtendida p1, PeticionAtendida p2, Distribucion cd){

    }

    public ArrayList<PeticionAtendida> getRecorrido() {
        return recorrido;
    }
    
    public double getIngresoViaje() {
        return ingresoViaje;
    }

    public double getgastoViaje() {
        return gastoViaje;
    }

    public double getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public Distribucion getCentroDistribucion() {
        return centroDistribucion;
    }

}
