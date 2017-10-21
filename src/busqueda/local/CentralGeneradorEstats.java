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
		int estadosAnadidos = 0;
		int peticionesAtendidas = 0;
		int vueltasCentro = 0;
    	for (int camionI = 0; camionI < numCamiones; ++camionI) {
			loopgasolineras:
    		for (int  gasolineraJ = 0; gasolineraJ < numGasolineras; ++gasolineraJ) {
				Gasolinera gasolinera = estado.getGasolinera(gasolineraJ);
				for (int peticionK = 0; peticionK < gasolinera.getPeticiones().size(); ++peticionK) {
					Central nuevoEstado = new Central(estado);
					if(nuevoEstado.camionPuedeAtenderPeticion(camionI, gasolinera)){
						nuevoEstado.atenderPeticion(camionI, gasolineraJ, peticionK);
						String S = "Atender peticion ("+camionI+","+gasolineraJ+","+peticionK+")";
						sucesores.add(new Successor(S,nuevoEstado));
						++estadosAnadidos;
						++peticionesAtendidas;
					} else if (nuevoEstado.getCamion(camionI).getViajes() >= 0) {
						nuevoEstado.desplazarCamionASuCentroDeDistribucion(camionI);
						String S = "Atender peticion ("+camionI+","+gasolineraJ+","+peticionK+")";
						sucesores.add(new Successor(S,nuevoEstado));
						++estadosAnadidos;
						++vueltasCentro;
					} else
						break loopgasolineras;
				}
    		}
    	}
        return sucesores;
    }
}

