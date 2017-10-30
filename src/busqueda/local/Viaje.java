package busqueda.local;

import IA.Gasolina.Gasolinera;

import java.util.ArrayList;

public class Viaje {

    private ArrayList<PeticionAtendida> recorrido;
    private double ingresoAcumulado;
    private double gastoAcumulado;
    private double distanciaRecorrida;


    public Viaje() {
        this.recorrido = new ArrayList<>();
        this.ingresoAcumulado = 0;
        this.gastoAcumulado = 0;
        this.distanciaRecorrida = 0;

    }

    public Viaje(ArrayList<PeticionAtendida> recorrido, double ingresoAcumulado, double gastoAcumulado, double distanciaRecorrida) {
        this.recorrido = recorrido;
        this.ingresoAcumulado = ingresoAcumulado;
        this.gastoAcumulado = gastoAcumulado;
        this.distanciaRecorrida = distanciaRecorrida;
    }

    public ArrayList<PeticionAtendida> getRecorrido() {
        return recorrido;
    }


    public double getIngresoAcumulado() {
        return ingresoAcumulado;
    }

    public double getGastoAcumulado() {
        return gastoAcumulado;
    }

    public double getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public void setRecorrido(ArrayList<PeticionAtendida> recorrido) {
        this.recorrido = recorrido;
    }

    public void setIngresoAcumulado(double ingresoAcumulado) {
        this.ingresoAcumulado = ingresoAcumulado;
    }

    public void setGastoAcumulado(double gastoAcumulado) {
        this.gastoAcumulado = gastoAcumulado;
    }

    public void setDistanciaRecorrida(double distanciaRecorrida) {
        this.distanciaRecorrida = distanciaRecorrida;
    }
}
