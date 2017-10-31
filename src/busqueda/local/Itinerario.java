package busqueda.local;

import IA.Gasolina.Distribucion;

import java.util.ArrayList;

public class Itinerario {
    private ArrayList<Viaje> itinerario;
    private double ingresoTotal;
    private double gastoTotal;
    private double distanciaTotal;


    public Itinerario() {
        this.itinerario = new ArrayList<>();
        ingresoTotal = 0;
        gastoTotal = 0;
        distanciaTotal = 0;
    }

    public Itinerario(ArrayList<Viaje> itinerario, double ingresoTotal, double gastoTotal, double distanciaTotal) {
        this.itinerario = itinerario;
        this.ingresoTotal = ingresoTotal;
        this.gastoTotal = gastoTotal;
        this.distanciaTotal = distanciaTotal;
    }


    public ArrayList<Viaje> getItinerario() {
        return itinerario;
    }


    public void setItinerario(ArrayList<Viaje> itinerario) {
        this.itinerario = itinerario;
    }

    public double getIngresoTotal() {
        return ingresoTotal;
    }

    public double getGastoTotal() {
        return gastoTotal;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setIngresoTotal(double ingresoTotal) {
        this.ingresoTotal = ingresoTotal;
    }

    public void setGastoTotal(double gastoTotal) {
        this.gastoTotal = gastoTotal;
    }

    public void setDistanciaTotal(double distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public void introducirViaje() {

    }
}
