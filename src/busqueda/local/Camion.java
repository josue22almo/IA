package busqueda.local;

import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;


public class Camion{
    private int viajes;
    private int tanques;
    private double distanciaDisponible;
    private int coordsCentreX;
    private int coordsCentreY;
    private int coordX;
    private int coordY;
    private double ingresos;
    private double gastos;
    private int peticionesAtendidas;
    private static int PRECIOKM = 2;
    private static int GANANCIAPORTANQUE = 1000;

    public Camion(Distribucion distribucion) {
        this.coordX = this.coordsCentreX = distribucion.getCoordX();
        this.coordY = this.coordsCentreY = distribucion.getCoordY();
        viajes = 5;
        tanques = 2;
        distanciaDisponible = 640;
        gastos = ingresos = 0;
        peticionesAtendidas = 0;
    }

    public void volverAlCentroDeDistribucion(){
        if (this.coordX != this.coordsCentreX && this.coordY != this.coordsCentreY) {
            viajes = --viajes;
            tanques = 2;
            gastos += 2 * calcularDistancia(coordsCentreX, coordsCentreY);
            distanciaDisponible -=  calcularDistancia(coordsCentreX, coordsCentreY);
            this.coordX = this.coordsCentreX;
            this.coordY = this.coordsCentreY;
        }
    }

    public double calcularDistancia(int destX, int destY){
        return Math.sqrt(Math.pow(coordX-destX, 2) + Math.pow(coordY-destY, 2));
    }

    public void atenderPeticion(Gasolinera gasolinera, int numPet){
        double percent;
        ++peticionesAtendidas;
        int dias = gasolinera.getPeticiones().get(numPet);
        if(dias == 0) percent = 102;
        else percent = 100 - Math.pow(2, dias);
        ingresos += GANANCIAPORTANQUE*percent/100;
        double distRecorrida = calcularDistancia(gasolinera.getCoordX(), gasolinera.getCoordY());
        gastos += PRECIOKM*distRecorrida;
        distanciaDisponible -= distRecorrida;
        tanques--;
        coordY = gasolinera.getCoordY();
        coordX = gasolinera.getCoordX();
    }

    public double getBeneficiosNetos(){
        return ingresos - gastos;
    }

    public boolean puedoAtenderPeticion(Gasolinera gasolinera){
        return viajes > 0 && tanques > 0 && 2*calcularDistancia(gasolinera.getCoordX(), gasolinera.getCoordY()) <= distanciaDisponible;
    }

    public double getIngresos() {
        return ingresos;
    }

    public double getGastos() {
        return gastos;
    }

    public int getViajes() {
        return viajes;
    }

    @Override
    public String toString() {
        return "origen = (" + String.valueOf(coordsCentreX)+ "," + String.valueOf(coordsCentreY) + ") - actual (" + String.valueOf(coordX)+ "," + String.valueOf(coordY) + ")"
                + " viajes disponibles " + viajes + " tanques disponibles " + String.valueOf(tanques) +  " peticones atendidas " +
                String.valueOf(peticionesAtendidas) + " ganancias = " + String.valueOf(getBeneficiosNetos()) + " km restantes " +
                String.valueOf(distanciaDisponible);
    }
}
