package busqueda.local;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;


public class Simulador {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Selecciona el tipus de búsqueda i presiona enter: \n 1-> Hill Climbing   2-> Simulated Annealing");
        int busqueda = sc.nextInt();
        Search search = null;
        if (busqueda == 1){
            System.out.println("Has seleccionat Hill Climbing");
            search = new HillClimbingSearch();
        }
        else if (busqueda == 2){
            System.out.println("Has seleccionat Simulated Annealing. Escriu els paràmetre de la búsqueda");
            int steps, stiter, k;
            steps = sc.nextInt(); stiter = sc.nextInt(); k = sc.nextInt();
            double lamb = sc.nextDouble();
            search = new SimulatedAnnealingSearch(steps, stiter, k, lamb);
        } else{
            System.out.println("ERROR! No has entrat un algoritme correcte");
            System.exit(0);
        }
        System.out.println("Ara entre els valors per generar els centres de distribució, el multiplicador, el nombre de benzineries" +
                " i el seed");
        System.out.println("ncen [enter]: ");
        int ncen = sc.nextInt();
        System.out.println("mult [enter]: ");
        int mult = sc.nextInt();
        System.out.println("ngas [enter]: ");
        int ngas = sc.nextInt();
        System.out.println("seed [enter]: ");
        int seed = sc.nextInt();

        Central central = new Central(ncen, mult, ngas, seed);

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
        }

        try {
            Problem problem = new Problem(central, new CentralGeneradorEstatsHC(), new CentralEstatFinal(), new CentralFuncioHeuristica1());
            long before = System.currentTimeMillis();
            SearchAgent agent = new SearchAgent(problem, search);
            long after = System.currentTimeMillis();
            Central centralEstadoFinal = (Central)search.getGoalState();

            System.out.printf("Beneficios netos obtenidos: %.2f \n", centralEstadoFinal.getBeneficiosNetos());
            System.out.printf("Total time: %d ms\n", after - before);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
