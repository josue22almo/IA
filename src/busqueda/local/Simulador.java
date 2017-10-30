package busqueda.local;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.SimulatedAnnealingSearch;

import java.util.Iterator;
import java.util.Properties;



public class Simulador {

    public static void main(String[] args) {
        int ncen, mult, ngas, seed;
        ncen = 10; mult = 1; ngas = 100; seed = 1234;// Canviar a generic

        int steps, stiter, k;
        steps = 2000; stiter = 1500; k = 1;
        double lamb = 10;

        //Search search = new HillClimbingSearch();
        Search search = new SimulatedAnnealingSearch(steps, stiter, k, (double)lamb);
        Central central = new Central(ncen, mult, ngas, seed);
        central.aplicarSolucion1();
        System.out.printf("Maximum benefits possible: %.2f \n", central.getMaximosBeneficiosActuales());
        System.out.printf("Maximum loses possible: %.2f \n", central.getPerdidasDiaSiguiente());
        Problem problem = new Problem(central, new CentralGeneradorEstatsSA(), new CentralEstatFinal(), new CentralFuncioHeuristica1());

        try {
            long before = System.currentTimeMillis();
            SearchAgent agent = new SearchAgent(problem, search);
            long after = System.currentTimeMillis();
            Central centralEstadoFinal = (Central)search.getGoalState();

            //printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
            System.out.printf("Beneficios obtenidos: %.2f \n", centralEstadoFinal.getBeneficiosNetos());
            System.out.printf("Beneficios netos obtenidos: %.2f \n", centralEstadoFinal.getBeneficiosNetos());
            System.out.printf("Total loses: %.2f \n", centralEstadoFinal.getPerdidasDiaSiguiente());
            System.out.printf("Total time: %d ms\n", after - before);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }

    }

   /* private static void printActions(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String)actions.get(i);
            System.out.println(action);
        }
    }*/
}
