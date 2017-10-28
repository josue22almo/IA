package busqueda.local;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;


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
        Problem problem = new Problem(central, new CentralGeneradorEstats(), new CentralEstatFinal(), new CentralFuncioHeuristica1());

        try {
            long before = System.currentTimeMillis();
            SearchAgent agent = new SearchAgent(problem, search);
            long after = System.currentTimeMillis();
            Central centralEstadoFinal = (Central)search.getGoalState();

            System.out.printf("Benefits obtained: %.2f \n", centralEstadoFinal.getBeneficiosNetos());
            System.out.printf("Total time: %d ms\n", after - before);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
