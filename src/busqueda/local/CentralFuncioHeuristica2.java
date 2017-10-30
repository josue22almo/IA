package busqueda.local;

import IA.Gasolina.CentrosDistribucion;
import aima.search.framework.HeuristicFunction;

public class CentralFuncioHeuristica2 implements HeuristicFunction {

    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    //Ingresos/Gastos totales por estado
    public double getHeuristicValue(Object state) {
        Central central = (Central) state;
        return central.getTotalGastos() / central.getTotalIngresos();

    }
}
