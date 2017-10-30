package busqueda.local;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import IA.Gasolina.Gasolinera;

import java.util.ArrayList;
import java.util.List;

public class CentralGeneradorEstats implements SuccessorFunction {


    public CentralGeneradorEstats() {
    }

    @Override
    public List getSuccessors(Object o) {

        ArrayList sucesores = new ArrayList();
        Central estado = (Central) o;
        int numCamiones = estado.getCamiones().size();
        int numGasolineras = estado.getGasolineras().size();
        CentralFuncioHeuristica1 FH = new CentralFuncioHeuristica1();

        for (int camionI = 0; camionI < numCamiones; ++camionI) {
            loopgasolineras:
            for (int gasolineraJ = 0; gasolineraJ < numGasolineras; ++gasolineraJ) {
                Gasolinera gasolinera = estado.getGasolinera(gasolineraJ);
                for (int peticionK = 0; peticionK < gasolinera.getPeticiones().size(); ++peticionK) {
                    Central nuevoEstado = new Central(estado);
                    if (nuevoEstado.camionPuedeAtenderPeticion(camionI, gasolinera)) {
                        nuevoEstado.atenderPeticion(camionI, gasolineraJ, peticionK);
                        double v = FH.getHeuristicValue(nuevoEstado);
                        String S = "Atender peticion (Camion: " + camionI + ", Gasolinera: " + gasolineraJ + ", Peticion: " + peticionK + "Valor heuristico: " + v + ")";
                        sucesores.add(new Successor(S, nuevoEstado));
                    } else if (nuevoEstado.getCamion(camionI).getViajes() > 0) {
                        nuevoEstado.desplazarCamionASuCentroDeDistribucion(camionI);
                        double v = FH.getHeuristicValue(nuevoEstado);
                        String S = "Atender peticion (Camion: " + camionI + ", Gasolinera: " + gasolineraJ + ", Peticion: " + peticionK + "Valor heuristico: " + v + ")";
                        sucesores.add(new Successor(S, nuevoEstado));
                    } else
                        break loopgasolineras;
                }
            }
        }
        return sucesores;
    }
}

