package busqueda.local;

import IA.Gasolina.Gasolinera;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.util.ArrayList;
import java.util.List;

public class CentralGeneradorEstatsHC implements SuccessorFunction {


    public CentralGeneradorEstatsHC() {
    }

    @Override
    public List getSuccessors(Object o) {

        ArrayList sucesores = new ArrayList();
        Central estado = (Central) o;
        int numCamiones = estado.getCamiones().size();
        int numGasolineras = estado.getGasolineras().size();

        for (int camionI = 0; camionI < numCamiones; ++camionI) {
            Central nuevoEstado = new Central(estado);
            /*String S = "Atender peticion (Camion: " + camionI + ", Gasolinera: " + gasolineraJ + ", Peticion: " + peticionK + ")";
            if (nuevoEstado.camionPuedeViajear(camionI, gasolinera)) {
                nuevoEstado.realizarViaje(camionI);
                sucesores.add(new Successor(S, nuevoEstado));
            } else
                break;*/

        }
        return sucesores;
    }
}

