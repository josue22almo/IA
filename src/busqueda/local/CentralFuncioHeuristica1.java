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
        return 1/central.getBeneficiosNetos();
    }


    //Ingresos/Gastos totales por estado
    public double getHeuristicValue2(Object state) {
        Central central = (Central)state;
        return central.getTotalGastos()/central.getTotalIngresos();

    }

    //Intenta reducir las perdidas del dia siguiente
    public double getHeuristicValue3(Object state) {
        Central cental = (Central)state;
        return cental.getPerdidasDiaSiguiente();

    }

    //Beneficios - perdidas dia siguiente(Horrible)
    public double getHeuristicValue7(Object state) {
        Central central = (Central)state;
        return 1/(central.getBeneficiosNetos()-central.getPerdidasDiaSiguiente());
    }

    //Perdidas dia siguiente/Beneficios
    public double getHeuristicValue8(Object state) {
        Central central = (Central)state;
        return central.getPerdidasDiaSiguiente()/central.getBeneficiosNetos();

    }


}
