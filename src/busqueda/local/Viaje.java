package busqueda.local;

import IA.Gasolina.Gasolinera;

import java.util.ArrayList;

public class Viaje {

    private ArrayList<Gasolinera> recorrido;
    private ArrayList<Integer> pedidoAtendido;
    private double ingresoAcumulado;
    private double gastoAcumulado;
    private double distanciaRecorrida;


    public Viaje() {
        this.recorrido = new ArrayList<>();
        this.pedidoAtendido = new ArrayList<Integer>();
        this.ingresoAcumulado = 0;
        this.gastoAcumulado = 0;
        this.distanciaRecorrida = 0;

    }

    public Viaje(ArrayList<Gasolinera> recorrido, ArrayList<Integer> pedidoAtendido, double ingresoAcumulado, double gastoAcumulado, double distanciaRecorrida) {
        this.recorrido = recorrido;
        this.pedidoAtendido = pedidoAtendido;
        this.ingresoAcumulado = ingresoAcumulado;
        this.gastoAcumulado = gastoAcumulado;
        this.distanciaRecorrida = distanciaRecorrida;
    }

    public ArrayList<Gasolinera> getRecorrido() {
        return recorrido;
    }

    public ArrayList<Integer> getPedidoAtendido() {
        return pedidoAtendido;
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

    public void setRecorrido(ArrayList<Gasolinera> recorrido) {
        this.recorrido = recorrido;
    }

    public void setPedidoAtendido(ArrayList<Integer> pedidoAtendido) {
        this.pedidoAtendido = pedidoAtendido;
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
