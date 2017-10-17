package busqueda.local;

import IA.Gasolina.Gasolinera;

import java.util.ArrayList;

public class Camion{
    private int viajes;
    private int tanques;
    private double distanciaDisponible;
    private int coordsCentreX;
    private int coordsCentreY;
    private int coordX;
    private int coordY;
    private double ingresos;
    private double gastos;


    public Camion(int coordX, int coordY) {
        this.coordX = this.coordsCentreX = coordX;
        this.coordY = this.coordsCentreY = coordY;
        viajes = 5;
        tanques = 2;
        distanciaDisponible = 640;
        gastos = ingresos = 0;
    }

    public void volvelOrigen(){
        tanques = 2;
        gastos += 2*calcularDistancia(coordsCentreX, coordsCentreY);
        this.coordX = this.coordsCentreX;
        this.coordX = this.coordsCentreX;
    }

    public double calcularDistancia(int destX, int destY){
        return Math.sqrt((coordX-destX)^2+(coordY-destY)^2);
    }

    public void atenderPeticion(Gasolinera gasolinera, int numPet){
        double percent;
        int dias = gasolinera.getPeticiones().get(numPet);
        if(dias == 0) percent = 102;
        else percent = 100 - Math.pow(2, dias);
        ingresos += 1000*percent/100;
        gastos += 2*calcularDistancia(gasolinera.getCoordX(), gasolinera.getCoordY());
    }

    public int getViajes() {
        return viajes;
    }

    public void setViajes(int viajes) {
        this.viajes = viajes;
    }

    public int getTanques() {
        return tanques;
    }

    public void setTanques(int tanques) {
        this.tanques = tanques;
    }

    public double getDistanciaDisponible() {
        return distanciaDisponible;
    }

    public void setDistanciaDisponible(double distanciaDisponible) {
        this.distanciaDisponible = distanciaDisponible;
    }

    public int getCoordsCentreX() {
        return coordsCentreX;
    }

    public void setCoordsCentreX(int coordsCentreX) {
        this.coordsCentreX = coordsCentreX;
    }

    public int getCoordsCentreY() {
        return coordsCentreY;
    }

    public void setCoordsCentreY(int coordsCentreY) {
        this.coordsCentreY = coordsCentreY;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public double getBeneficiosNetos(){
        return ingresos - gastos;
    }
}
