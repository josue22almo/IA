package busqueda.local;

import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;

public class Camion extends Distribucion{
    private int travels;
    private int tanques;
    private double availableDistance;

    public Camion(int coordX, int coordY) {
        super(coordX, coordY);
        travels = 5;
        tanques = 2;
        availableDistance = 640;
    }

    public double distanceTo(Gasolinera gasolinera){
        int gCoordX = gasolinera.getCoordX();
        int gCoordY = gasolinera.getCoordY();
        return Math.sqrt((gCoordX - getCoordX())^2*(gCoordY - getCoordY())^2);
    }
}
