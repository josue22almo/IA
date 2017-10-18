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
			loopgasolineras:
    		for (int  gasolineraJ = 0; gasolineraJ < numGasolineras; ++gasolineraJ) {
				Gasolinera gasolinera = estado.getGasolinera(gasolineraJ);

				for (int peticionK = 0; peticionK < gasolinera.getPeticiones().size(); ++peticionK) {
					Central nuevoEstado = new Central(estado);
					if(nuevoEstado.camionPuedeAtenderPeticion(camionI, gasolinera)){
						nuevoEstado.atenderPeticion(camionI, gasolineraJ, peticionK);
						double valor = fh.getHeuristicValue(nuevoEstado);
						String S = "Atender peticion ("+camionI+","+gasolineraJ+","+peticionK+")"+"Costes:"+valor+nuevoEstado.toString();
						sucesores.add(new Successor(S,nuevoEstado));
					} else {
						nuevoEstado.desplazarCamionASuCentroDeDistribucion(camionI);
						double valor = fh.getHeuristicValue(nuevoEstado);
						String S = "Atender peticion ("+camionI+","+gasolineraJ+","+peticionK+")"+"Costes:"+valor+nuevoEstado.toString();
						sucesores.add(new Successor(S,nuevoEstado));
						break loopgasolineras;
					}
				}

    		}
    	}
        return sucesores;
    }
}

