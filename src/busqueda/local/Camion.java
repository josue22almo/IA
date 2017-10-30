package busqueda.local;

import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;

import java.util.ArrayList;

public class Camion {
    private ArrayList<Viaje> itinerario;
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
    public static int DISTANCIAINICIAL = 640;

    public Camion(Distribucion distribucion) {
        this.coordX = this.coordsCentreX = distribucion.getCoordX();
        this.coordY = this.coordsCentreY = distribucion.getCoordY();
        this.itinerario = new ArrayList<>();
        viajes = 5;
        tanques = 2;
        distanciaDisponible = DISTANCIAINICIAL;
        gastos = ingresos = 0;
        peticionesAtendidas = 0;
    }

    public Camion(Camion c) {
        viajes = c.getViajes();
        tanques = c.getTanques();
        distanciaDisponible = c.getDistanciaDisponible();
        coordsCentreX = c.getCoordsCentreX();
        coordsCentreY = c.getCoordsCentreY();
        coordX = c.getCoordX();
        coordY = c.getCoordY();
        ingresos = c.getIngresos();
        gastos = c.getGastos();
        peticionesAtendidas = c.getPeticionesAtendidas();
    }

    public void volverAlCentroDeDistribucion() {
        viajes = viajes - 1;
        tanques = 2;
        double dist = calcularDistancia(coordsCentreX, coordsCentreY);
        distanciaDisponible -= dist;
        this.coordX = this.coordsCentreX;
        this.coordY = this.coordsCentreY;
    }

    private double calcularDistancia(int destX, int destY) {
        return Math.sqrt(Math.pow(coordX - destX, 2) + Math.pow(coordY - destY, 2));
    }

    private double calcularRetorno(int destX, int destY) {
        return Math.sqrt(Math.pow(coordsCentreX - destX, 2) + Math.pow(coordsCentreY - destY, 2));
    }

    public void atenderPeticion(Gasolinera gasolinera, int numPet) {
        double percent;
        ++peticionesAtendidas;
        int dias = gasolinera.getPeticiones().get(numPet);
        if (dias == 0) percent = 102;
        else percent = 100 - Math.pow(2, dias);
        ingresos += GANANCIAPORTANQUE * percent / 100;
        double distRecorrida = calcularDistancia(gasolinera.getCoordX(), gasolinera.getCoordY());
        gastos += PRECIOKM * distRecorrida;
        distanciaDisponible -= distRecorrida;
        tanques--;
        setCoordsFromGasolinera(gasolinera);
    }

    public void atenderMaxPeticiones(Gasolinera gasolinera) {
        int i = gasolinera.getPeticiones().size() - 1;
        while (tanques > 0 && i >= 0) {
            int diasPeticion = gasolinera.getPeticiones().get(i);
            double percent;
            if (diasPeticion == 0) percent = 102;
            else percent = 100 - Math.pow(2, diasPeticion);
            ingresos += GANANCIAPORTANQUE * percent / 100;
            gasolinera.getPeticiones().remove(i);
            --i;
            --tanques;
            ++peticionesAtendidas;
        }
        double distRecorrida = calcularDistancia(gasolinera.getCoordX(), gasolinera.getCoordY());
        gastos += PRECIOKM * distRecorrida;
        distanciaDisponible -= distRecorrida;
        setCoordsFromGasolinera(gasolinera);
    }

    public double getBeneficiosNetos() {
        return ingresos - gastos;
    }


    public double getBeneficiosNetosNetos() {
        return ingresos - gastos;
    }

    public boolean puedoAtenderPeticion(Gasolinera gasolinera) {
        boolean puedovoler;
        if (estoyEnElCentroDistribucion())
            puedovoler = distanciaDisponible - 2 * calcularDistancia(gasolinera.getCoordX(), gasolinera.getCoordY()) >= 0;
        else puedovoler = distanciaDisponible - (calcularDistancia(gasolinera.getCoordX(), gasolinera.getCoordY()) + calcularRetorno(gasolinera.getCoordX(),gasolinera.getCoordY())) >= 0;
        return viajes > 0 && tanques > 0 && puedovoler;
    }

    public double getIngresos() {
        return ingresos;
    }

    public double getGastos() {
        return gastos;
    }

    public boolean estoyEnElCentroDistribucion() {
        return coordsCentreX == coordX && coordsCentreY == coordY;
    }

    public int getViajes() {
        return viajes;
    }

    @Override
    public String toString() {
        return "origen = (" + String.valueOf(coordsCentreX) + "," + String.valueOf(coordsCentreY) + ") - actual (" + String.valueOf(coordX) + "," + String.valueOf(coordY) + ")"
                + " viajes disponibles " + viajes + " tanques disponibles " + String.valueOf(tanques) + " peticones atendidas " +
                String.valueOf(peticionesAtendidas) + " ganancias = " + String.valueOf(getBeneficiosNetos()) + " km restantes " +
                String.valueOf(distanciaDisponible) + " distancia recorrida = " + String.valueOf(getDistanciaRecorrida() +
                " distancia inicial " + String.valueOf(DISTANCIAINICIAL));
    }

    public int getTanques() {
        return tanques;
    }

    public double getDistanciaDisponible() {
        return distanciaDisponible;
    }

    public int getCoordsCentreX() {
        return coordsCentreX;
    }

    public int getCoordsCentreY() {
        return coordsCentreY;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public int getPeticionesAtendidas() {
        return peticionesAtendidas;
    }

    public void setViajes(int viajes) {
        this.viajes = viajes;
    }

    public void setTanques(int tanques) {
        this.tanques = tanques;
    }

    public void setDistanciaDisponible(double distanciaDisponible) {
        this.distanciaDisponible = distanciaDisponible;
    }

    public void setCoordsCentreX(int coordsCentreX) {
        this.coordsCentreX = coordsCentreX;
    }

    public void setCoordsCentreY(int coordsCentreY) {
        this.coordsCentreY = coordsCentreY;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    public void setPeticionesAtendidas(int peticionesAtendidas) {
        this.peticionesAtendidas = peticionesAtendidas;
    }

    public void setCoordsFromGasolinera(Gasolinera gasolinera) {
        coordY = gasolinera.getCoordY();
        coordX = gasolinera.getCoordX();
    }

    public double getDistanciaRecorrida() {
        return DISTANCIAINICIAL - distanciaDisponible;
    }

    public void setCosteKM(int costeKM) {
        this.PRECIOKM = costeKM;
    }
}
