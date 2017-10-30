package busqueda.local;

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
        for (int camionI = 0; camionI < numCamiones; ++camionI) {
            String S = "Atender peticion (Camion: " + camionI + ")";
            for (int k = 0; k < estado.getGasolineras().size(); ++k){
                Central nuevoEstado = new Central(estado);
                if (nuevoEstado.camionPuedeViajear(camionI, estado.getGasolinera(k))) {
                    nuevoEstado.realizarViaje(camionI, k);
                    sucesores.add(new Successor(S, nuevoEstado));
                }
            }

            ArrayList<Tuple<Integer, Integer>> viajes = estado.getPosiblesViajesCamion(camionI);

            for (Tuple<Integer, Integer> viaje : viajes){
                Central nuevoEstado = new Central(estado);
                nuevoEstado.realizarViaje(camionI, viaje);
                sucesores.add(new Successor(S, nuevoEstado));
            }
        }
        return sucesores;
    }
}

