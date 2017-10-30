package busqueda.local.experimentos;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import busqueda.local.Central;
import busqueda.local.CentralEstatFinal;
import busqueda.local.CentralFuncioHeuristica1;
import busqueda.local.CentralGeneradorEstatsSA;

import java.util.Random;
import java.util.Scanner;

public class Experimentos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numExperimento = sc.nextInt();

        if (numExperimento == 1){

        } else if (numExperimento == 2) { // determinar cual es la mejor solucion inicial
            int ncen = 10, mult = 1, ngas = 100, seed = 1;
            Central central = new Central(ncen, mult, ngas, seed);
            Search search = new HillClimbingSearch();
            Central c1 = new Central(central);
            c1.aplicarSolucion1();
            Central c2 = new Central(central);
            c2.aplicarSolucion2();
            Central c3 = new Central(central);
            int numCamiones = new Random().nextInt(ncen*mult);
            c3.aplicarSolucion3(numCamiones);

            Problem problem1 = new Problem(c1, new CentralGeneradorEstatsSA(), new CentralEstatFinal(), new CentralFuncioHeuristica1());
            Problem problem2 = new Problem(c2, new CentralGeneradorEstatsSA(), new CentralEstatFinal(), new CentralFuncioHeuristica1());
            Problem problem3 = new Problem(c3, new CentralGeneradorEstatsSA(), new CentralEstatFinal(), new CentralFuncioHeuristica1());

            System.out.println("#beneficios con bonificacion        # beneficios netos       #tiempo" );
            simular(problem1, search);
            simular(problem2, search);
            simular(problem3, search);
        } else if (numExperimento == 3){ //determinar parámetros Simulated Annealing
            int t = 20;
            int ncen = 10, mult = 1, ngas = 100, seed = 1;
            Central central = new Central(ncen, mult, ngas, seed);
            central.aplicarSolucion1();
            System.out.println("#beneficios con bonificacion        # beneficios netos       #tiempo    #steps   #stiter   #k      #lamb");
            while (t >= 0){
                int steps = new Random().nextInt(10000);
                int stiter = new Random().nextInt(10000);
                int k = new Random().nextInt(10000);
                double lamb = Double.valueOf(new Random().nextInt(100000));
                Search search = new SimulatedAnnealingSearch(steps, stiter, k, lamb);
                Problem problem = new Problem(central, new CentralGeneradorEstatsSA(), new CentralEstatFinal(), new CentralFuncioHeuristica1());

                simularSA(problem, search, steps, stiter, k, lamb);
                --t;
            }
        } else if (numExperimento == 4){ // evolucion del tiempo de ejecución
            int steps = new Random().nextInt(10000);
            int stiter = new Random().nextInt(10000);
            int k = new Random().nextInt(10000);
            double lamb = Double.valueOf(new Random().nextInt(100000));
            Search searchSA = new SimulatedAnnealingSearch(steps, stiter, k, lamb);
            Search searchHC = new HillClimbingSearch();

            int nc = 0, mlt = 1, ng = 100, sd = 1;
            int times = 8;
            System.out.println("#centros #gasolineras #tiempo  #beneficio neto");
            while (times >= 0){
                Central c = new Central(nc, mlt, ng, sd);
                c.aplicarSolucion1();
                Problem problem = new Problem(c, new CentralGeneradorEstatsSA(), new CentralEstatFinal(), new CentralFuncioHeuristica1());
                System.out.printf("%d       %d          ", nc, ng);
                printExperimento4(problem, searchHC);
                System.out.printf("%d       %d          ", nc, ng);
                printExperimento4(problem, searchSA);
                nc += 10; ng += 10;
                --times;
            }
        } else if (numExperimento == 5){
            int ncen = 10, mult = 1, ngas = 100, seed = 1;
            Central central = new Central(ncen, mult, ngas, seed);
            central.aplicarSolucion1();
            Problem problem = new Problem(central, new CentralGeneradorEstatsSA(), new CentralEstatFinal(), new CentralFuncioHeuristica1());
            Search search = new HillClimbingSearch();
            System.out.println("#beneficios con bonificacion        # beneficios netos       #km recorridos" );
            experimento5(problem, search);
            ncen = 5;
            central = new Central(ncen, mult, ngas, seed);
            central.aplicarSolucion1();
            problem = new Problem(central, new CentralGeneradorEstatsSA(), new CentralEstatFinal(), new CentralFuncioHeuristica1());
            experimento5(problem, search);
        } else if (numExperimento == 6){
            int ncen = 10, mult = 1, ngas = 100, seed = 1;
            Central central = new Central(ncen, mult, ngas, seed);
            central.aplicarSolucion1();
            Problem problem = new Problem(central, new CentralGeneradorEstatsSA(), new CentralEstatFinal(), new CentralFuncioHeuristica1());
            Search search = new HillClimbingSearch();
            int times = 15, costeKM = 2;
            System.out.println("#coste del km  #peticiones atendidas" );
            while (times >= 0){
                System.out.printf("%d              ", costeKM);
                experimento6(problem, search);
                central.setCosteKM(costeKM);
                costeKM *= 2;
                --times;
            }
        } else if (numExperimento == 7) {
            int ncen = 10, mult = 1, ngas = 100, seed = 1;
            Central central = new Central(ncen, mult, ngas, seed);
            central.aplicarSolucion1();
            Problem problem = new Problem(central, new CentralGeneradorEstatsSA(), new CentralEstatFinal(), new CentralFuncioHeuristica1());
            Search search = new HillClimbingSearch();
            int distanciaInicail = 600;
            System.out.println("#distancia inicial #beneficio" );
            while (distanciaInicail >= 0){
                central = new Central(ncen, mult, ngas, seed);
                central.setDistanciaTotal(distanciaInicail);
                central.aplicarSolucion1();
                System.out.printf("%d                ", distanciaInicail);
                experimento7(problem, search);
                distanciaInicail -= 60;
            }
        }
    }

    private static void experimento7(Problem problem, Search search) {
        try {
            SearchAgent agent = new SearchAgent(problem, search);
            Central centralEstadoFinal = (Central)search.getGoalState();
            System.out.printf("%.2f\n", centralEstadoFinal.getBeneficiosNetos());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void experimento6(Problem problem, Search search) {
        try {
            SearchAgent agent = new SearchAgent(problem, search);
            Central centralEstadoFinal = (Central)search.getGoalState();
            System.out.printf("%d\n", centralEstadoFinal.getPeticionesAtendidas());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void experimento5(Problem problem, Search search) {
        try {
            SearchAgent agent = new SearchAgent(problem, search);
            Central centralEstadoFinal = (Central)search.getGoalState();
            System.out.printf("%.2f                            ", centralEstadoFinal.getBeneficiosNetos());
            System.out.printf("%.2f                  ", centralEstadoFinal.getBeneficiosNetos());
            System.out.printf("%.2f\n", centralEstadoFinal.getDistanciaRecorrida());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void simular(Problem problem, Search search){
        try {
            long before = System.currentTimeMillis();
            SearchAgent agent = new SearchAgent(problem, search);
            long after = System.currentTimeMillis();
            Central centralEstadoFinal = (Central)search.getGoalState();
            System.out.printf("%.2f                            ", centralEstadoFinal.getBeneficiosNetos());
            System.out.printf("%.2f                  ", centralEstadoFinal.getBeneficiosNetos());
            System.out.printf("%d       \n", after - before);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printExperimento4(Problem problem, Search search){
        try {
            long before = System.currentTimeMillis();
            SearchAgent agent = new SearchAgent(problem, search);
            long after = System.currentTimeMillis();
            Central centralEstadoFinal = (Central)search.getGoalState();
            System.out.printf("%d     %.2f\n", after - before, centralEstadoFinal.getBeneficiosNetos());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void simularSA(Problem problem, Search search, int steps, int filter, int k, double lamb){
        try {
            long before = System.currentTimeMillis();
            SearchAgent agent = new SearchAgent(problem, search);
            long after = System.currentTimeMillis();
            Central centralEstadoFinal = (Central)search.getGoalState();
            System.out.printf("%.2f                            ", centralEstadoFinal.getBeneficiosNetos());
            System.out.printf("%.2f                  ", centralEstadoFinal.getBeneficiosNetos());
            System.out.printf("%d       %d      %d     %d    %.2f\n", after - before, steps, filter, k, lamb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
