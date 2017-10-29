package busqueda.local;

import aima.search.framework.HeuristicFunction;

public class CentralFuncioHeuristica3 implements HeuristicFunction {

    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    //Intenta reducir las perdidas del dia siguiente
    public double getHeuristicValue(Object state) {
        Central cental = (Central) state;
        return cental.getPerdidasDiaSiguiente();

    }

}
