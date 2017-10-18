package busqueda.local;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;


public class Simulador {

    public static void main(String[] args) {
        int ncen, mult, ngas, seed;
        ncen = 10; mult = 1; ngas = 100; seed = 1234;

        Search search = new HillClimbingSearch();
        Central central = new Central(ncen, mult, ngas, seed);
        central.solucion1();
        Problem problem = new Problem(central, new CentralGeneradorEstats(), new CentralEstatFinal());

        try {
            long before = System.currentTimeMillis();
            SearchAgent agent = new SearchAgent(problem, search);
            long after = System.currentTimeMillis();
            Central centralEstadoFinal = (Central)((Search)search).getGoalState();

            System.out.printf("Benefits obtained: %.5f \n", centralEstadoFinal.getBenefits());
            System.out.printf("Total time: %d ms\n", after - before);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
