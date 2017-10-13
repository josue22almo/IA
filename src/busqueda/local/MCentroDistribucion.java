package busqueda.local;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Gasolinera;

public class MCentroDistribucion extends CentrosDistribucion {
    private float distanceTraveled;
    private int availableFuel;
    private int availableTravels;

    public MCentroDistribucion(int ncen, int mult, int seed) {
        super(ncen, mult, seed);
        distanceTraveled = 640;
        availableFuel = 2;
        availableTravels = 5;
    }

    public boolean canFillTanks() {
        return availableFuel != 0;
    }

    public boolean canTravel() {
        return availableTravels != 0;
    }

    public void fillTanks(Gasolinera gasolinera, int tanks) {

    }
}
