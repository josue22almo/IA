package busqueda.local.experimentos;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import busqueda.local.Central;
import busqueda.local.CentralEstatFinal;
import busqueda.local.CentralFuncioHeuristica1;
import busqueda.local.CentralGeneradorEstats;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Experimentos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numExperimento = sc.nextInt();
        int ncen = 10, mult = 1, ngas = 100, seed = 1;
        Central central = new Central(ncen, mult, ngas, seed);
        if (numExperimento == 2) { // determinar cual es la mejor solucion inicial
            Search search = new HillClimbingSearch();
            Central c1 = new Central(central);
            c1.aplicarSolucion1();
            Central c2 = new Central(central);
            c2.aplicarSolucion2();
            Central c3 = new Central(central);
            int numCamiones = new Random().nextInt(ncen*mult);
            c3.aplicarSolucion3(numCamiones);

            Problem problem1 = new Problem(c1, new CentralGeneradorEstats(), new CentralEstatFinal(), new CentralFuncioHeuristica1());
            Problem problem2 = new Problem(c2, new CentralGeneradorEstats(), new CentralEstatFinal(), new CentralFuncioHeuristica1());
            Problem problem3 = new Problem(c3, new CentralGeneradorEstats(), new CentralEstatFinal(), new CentralFuncioHeuristica1());

            System.out.println("#beneficios con bonificacion        # beneficios netos       #tiempo" );
            try {
                long before = System.currentTimeMillis();
                SearchAgent agent = new SearchAgent(problem1, search);
                long after = System.currentTimeMillis();
                Central centralEstadoFinal = (Central)search.getGoalState();
                System.out.printf("%.2f                            ", centralEstadoFinal.getBeneficios());
                System.out.printf("%.2f                  ", centralEstadoFinal.getBeneficiosNetos());
                System.out.printf("%d \n", after - before);

                before = System.currentTimeMillis();
                agent = new SearchAgent(problem2, search);
                after = System.currentTimeMillis();
                centralEstadoFinal = (Central)search.getGoalState();

                System.out.printf("%.2f                            ", centralEstadoFinal.getBeneficios());
                System.out.printf("%.2f                  ", centralEstadoFinal.getBeneficiosNetos());
                System.out.printf("%d \n", after - before);


                before = System.currentTimeMillis();
                agent = new SearchAgent(problem3, search);
                after = System.currentTimeMillis();
                centralEstadoFinal = (Central)search.getGoalState();

                System.out.printf("%.2f                            ", centralEstadoFinal.getBeneficios());
                System.out.printf("%.2f                  ", centralEstadoFinal.getBeneficiosNetos());
                System.out.printf("%d \n", after - before);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (numExperimento == 3){ //determinar parámetros Simulated Annealing
            int t = 20;
            central.aplicarSolucion1();
            System.out.println("#beneficios con bonificacion        # beneficios netos       #tiempo    #steps   #stiter   #k      #lamb");
            while (t >= 0){
                int steps = new Random().nextInt(10000);
                int stiter = new Random().nextInt(10000);
                int k = new Random().nextInt(10000);
                double lamb = Double.valueOf(new Random().nextInt(100000));
                Search search = new SimulatedAnnealingSearch(steps, stiter, k, lamb);
                Problem problem = new Problem(central, new CentralGeneradorEstats(), new CentralEstatFinal(), new CentralFuncioHeuristica1());

                try {
                    long before = System.currentTimeMillis();
                    SearchAgent agent = new SearchAgent(problem, search);
                    long after = System.currentTimeMillis();
                    Central centralEstadoFinal = (Central)search.getGoalState();
                    System.out.printf("%.2f                            ", centralEstadoFinal.getBeneficios());
                    System.out.printf("%.2f                  ", centralEstadoFinal.getBeneficiosNetos());
                    System.out.printf("%d       ", after - before);
                    System.out.printf("%d     %d     %d    %.2f \n", steps, stiter, k, lamb);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                --t;
            }
        } else if (numExperimento == 4){

        } else if (numExperimento == 5){

        } else if (numExperimento == 6){

        }
    }
}
