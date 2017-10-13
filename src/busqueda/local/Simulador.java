package busqueda.local;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;
import IA.Gasolina.Gasolineras;

import java.util.Iterator;

public class Simulador {

    public static void main(String[] args) {
        Gasolineras s = new Gasolineras(100, 1234);
        CentrosDistribucion c = new CentrosDistribucion(10, 1, 1234);
    }
}
