package busqueda.local;

import aima.search.framework.HeuristicFunction;

public class CentralFuncioHeuristica5 implements HeuristicFunction {

    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    //Perdidas dia siguiente/Beneficios
    public double getHeuristicValue(Object state) {
        Central central = (Central) state;
        return central.getPerdidasDiaSiguiente() / central.getBeneficiosNetos();

    }


}
