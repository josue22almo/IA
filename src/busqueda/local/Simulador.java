package busqueda.local;

import aima.search.framework.*;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;


public class Simulador {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entra los valores para generar los centros de distribución, el multiplicador, el nombre de gasolineras y el seed");
        System.out.println("ncen [enter]: ");
        int ncen = sc.nextInt();
        System.out.println("mult [enter]: ");
        int mult = sc.nextInt();
        System.out.println("ngas [enter]: ");
        int ngas = sc.nextInt();
        System.out.println("seed [enter]: ");
        int seed = sc.nextInt();

        Central central = new Central(ncen, mult, ngas, seed);

        System.out.println("Selecciona el tipus de búsqueda i presiona enter: \n 1-> Hill Climbing   2-> Simulated Annealing");
        int busqueda = sc.nextInt();
        Search search = null;

        SuccessorFunction successorFunction = null;
        if (busqueda == 1){
            System.out.println("Has seleccionat Hill Climbing");
            search = new HillClimbingSearch();
            successorFunction = new CentralGeneradorEstatsHC();
        }
        else if (busqueda == 2){
            System.out.println("Has seleccionat Simulated Annealing. Escriu els paràmetre de la búsqueda");
            int steps, stiter, k;
            System.out.println("steps [enter]: ");
            steps = sc.nextInt();
            System.out.println("stiter [enter]: ");
            stiter = sc.nextInt();
            System.out.println("k [enter]: ");
            k = sc.nextInt();
            System.out.println("lamb [enter]: ");
            double lamb = sc.nextDouble();
            search = new SimulatedAnnealingSearch(steps, stiter, k, lamb);
            successorFunction = new CentralGeneradorEstatsSA();
        } else{
            System.out.println("ERROR! No has entrat un algoritme correcte");
            System.exit(0);
        }

        System.out.println("Tenemos 3 soluciones iniciales posibles, selecciona una");
        System.out.println("1.Solución inicial 1: los camiones empiezan en el centro con 5 viajes");
        System.out.println("2.Solucion inicial 2: todos los camiones empiezan con un viaje realizado");
        System.out.println("3.Solución inicial 3: k camiones empiezan con 1 viaje realizado");

        int solucion = sc.nextInt();
        if (solucion == 1){
            System.out.println("Has seleccionado la solucion 1");
            central.aplicarSolucion1();
        } else if (solucion == 2){
            System.out.println("Has seleccionado la solucion 2");
            central.aplicarSolucion2();
        }else if (solucion == 3){
            System.out.println("Has seleccionado la solucion 3. Escribe el valor de k");
            int k = sc.nextInt();
            central.aplicarSolucion3(k);
        }else{
            System.out.println("Error al elegir el estado inicial ");
            System.exit(0);
        }

        System.out.println("Ahora toca seleccionar la función heuristica, tenemos 3 heuristicos disponibles, selecciona uno");
        System.out.println("1.Heuristico 1: máximiza el beneficio sin tener en cuenta otros parámetros");
        System.out.println("2.Heuristico 2: minimiza la relación entre gastos e ingresos");
        System.out.println("3.Heuristico 3: minimiza la relación entre las perdidas del día siguiente y los beneficios neto");
        int heuristico = sc.nextInt();
        HeuristicFunction heuristicFunction = null;
        if (heuristico == 1) heuristicFunction = new CentralFuncioHeuristica1();
        else if (heuristico == 2) heuristicFunction = new CentralFuncioHeuristica2();
        else if (heuristico == 3) heuristicFunction = new CentralFuncioHeuristica2();
        else{
            System.out.println("Error al elegir la funcion heuristica");
            System.exit(0);
        }
        System.out.println("La búsqueda ha empezado");
        Problem problem = new Problem(central, successorFunction, new CentralEstatFinal(), heuristicFunction);
        try {

            long before = System.currentTimeMillis();
            SearchAgent agent = new SearchAgent(problem, search);
            long after = System.currentTimeMillis();
            Central centralEstadoFinal = (Central)search.getGoalState();

            System.out.printf("Beneficios netos obtenidos: %.2f \n", centralEstadoFinal.getBeneficiosNetos());
            System.out.printf("Peticiones atendidas %d\n", centralEstadoFinal.getPeticionesAtendidas());
            System.out.printf("Total time: %d ms\n", after - before);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
