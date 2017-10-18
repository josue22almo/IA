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
    	CentralFuncioHeuristica1 fh = new CentralFuncioHeuristica1();
    	int numCamiones = estado.getCamiones().size();
    	int numGasolineras = estado.getGasolineras().size();
    	
    	for (int camionI = 0; camionI < numCamiones; ++camionI) {
    		for (int  gasolineraJ = 0; gasolineraJ < numGasolineras; ++gasolineraJ) {
    			int numPeticiones = estado.getGasolineras().get(gasolineraJ).getPeticiones().size();
    			for (int peticionK = 0; peticionK < numPeticiones; ++k) {
    				Central estadoActual = new Central(estado);
    				Camion camion = estado.getCamion(camionI);
    				Gasolinera gasolinera = estado.getGasolinera(gasolineraJ);
    				if (camion.puedeAtenderPeticion(gasolinera)) {
    					estado.atenderPeticion(camionI, gasolineraJ, peticionK);
    				}
    				double valor = fh.getHeuristicValue(estadoActual);
    				String S = "Atender peticion ("+camionI+","+gasolineraJ+","+peticionK+")"+"Costes:"+valor+estadoActual.toString();
    				sucesores.add(new Successor(S,estadoActual));
    			}
    		}
    	}
        return sucesores;
    }
}

