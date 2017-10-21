package busqueda.local;


import aima.search.framework.HeuristicFunction;

public class CentralFuncioHeuristica1 implements HeuristicFunction {
    public CentralFuncioHeuristica1() {
    }

    public boolean equals(Object obj) {
        boolean retValue = super.equals(obj);
        return retValue;
    }

    public double getHeuristicValue(Object state) {
        return ((Central)state).getBeneficiosNetos(); //modificar
    }
}
