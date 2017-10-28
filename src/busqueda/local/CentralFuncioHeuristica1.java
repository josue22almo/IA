package busqueda.local;

import IA.Gasolina.CentrosDistribucion;
import aima.search.framework.HeuristicFunction;

public class CentralFuncioHeuristica1 implements HeuristicFunction {

    public boolean equals(Object obj) {
        return super.equals(obj);
    }



    // Ingresos - Gastos totales por estado
    public double getHeuristicValue(Object state) {
        Central central = (Central)state;
        return central.getBeneficiosNetos();
    }


    //Ingresos/Gastos totales por estado
    public double getHeuristicValue2(Object state) {
        Central central = (Central)state;
        return central.getTotalIngresos()/central.getTotalGastos();

    }

    //Intenta reducir las perdidas del dia siguiente
    public double getHeuristicValue3(Object state) {
        Central cental = (Central)state;
        return 1/cental.getPerdidasDiaSiguiente();

    }

    //Beneficios - perdidas dia siguiente
    public double getHeuristicValue4(Object state) {
        Central central = (Central)state;
        return central.getBeneficiosNetos()-central.getPerdidasDiaSiguiente();
    }


}
