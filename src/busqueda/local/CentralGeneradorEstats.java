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
    	Central c1 = (Central) o;
    	CentralFuncioHeuristica1 fh = new CentralFuncioHeuristica1();
    	int numcamiones = c1.getCamiones().size();
    	int numgasolineras = c1.getGasolineras().size();
    	for (int camionI = 0; camionI < numcamiones; ++camionI) {
    		for (int  gasolineraJ = 0; gasolineraJ < numgasolineras; ++gasolineraJ) {
    			Central c2 = new Central(numcamiones,1,numgasolineras,1234);
    			int numpeticiones = c1.getGasolineras().get(gasolineraJ).getPeticiones().size();
    			c1.atenderPeticion(camionI, gasolineraJ, numpeticiones);
    			double valor = fh.getHeuristicValue(c2);
    			String S = "intercambio ("+camionI+","+gasolineraJ+")"+"Costes:"+valor+c2.toString();
    			sucesores.add(new Successor(S,c2));
    		}
    	}
        return sucesores;
    }
}
