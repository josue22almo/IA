package busqueda.local;

import IA.Gasolina.CentrosDistribucion;
import aima.search.framework.HeuristicFunction;

public class CentralFuncioHeuristica1 implements HeuristicFunction {

    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    // Ingresos - Gastos totales por estado
    public double getHeuristicValue(Object state) {
        Central central = (Central) state;
        return 1 / central.getBeneficiosNetos();
    }
}
