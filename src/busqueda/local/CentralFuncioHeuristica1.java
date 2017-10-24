package busqueda.local;

import aima.search.framework.HeuristicFunction;

public class CentralFuncioHeuristica1 implements HeuristicFunction {

    public boolean equals(Object obj) {
        return super.equals(obj);
    }



    public double getHeuristicValue(Object state) {
        Central central = (Central)state;
        return central.getBeneficiosNetos();
        //return 0;
    }
}
