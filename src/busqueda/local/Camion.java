package busqueda.local;

import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;

public class Camion{
    private int travels;
    private int tanques;
    private double availableDistance;
    private int coordsCentreX;
    private int coordsCentreY;
    private int coordX;
    private int coordY;


    public Camion(int coordX, int coordY) {
        travels = 5;
        tanques = 2;
        availableDistance = 640;
    }

    public double distanceTo(Gasolinera gasolinera){
        int gCoordX = gasolinera.getCoordX();
        int gCoordY = gasolinera.getCoordY();
        return Math.sqrt((gCoordX - coordX)^2*(gCoordY - coordY)^2);
    }


    public void atenderPeticiones(int numPeticiones){
        tanques -= numPeticiones;
    }

    public int getTravels() {
        return travels;
    }

    public void setTravels(int travels) {
        this.travels = travels;
    }

    public int getTanques() {
        return tanques;
    }

    public void setTanques(int tanques) {
        this.tanques = tanques;
    }

    public double getAvailableDistance() {
        return availableDistance;
    }

    public void setAvailableDistance(double availableDistance) {
        this.availableDistance = availableDistance;
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
}
