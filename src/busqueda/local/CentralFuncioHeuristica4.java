package busqueda.local;

import aima.search.framework.HeuristicFunction;

public class CentralFuncioHeuristica4 implements HeuristicFunction {

    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    //Beneficios - perdidas dia siguiente(Horrible)
    public double getHeuristicValue(Object state) {
        Central central = (Central) state;
        return 1 / (central.getBeneficiosNetos() - central.getPerdidasDiaSiguiente());
    }


}
