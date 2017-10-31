package busqueda.local;

import IA.Gasolina.Gasolinera;

public class PeticionAtendida {

    private Gasolinera gasolinera;
    private int numPet;

    public PeticionAtendida() {
    }

    public PeticionAtendida(Gasolinera gasolinera, Integer numPet) {
        this.gasolinera = gasolinera;
        this.numPet = numPet;
    }

    public Gasolinera getGasolinera() {
        return gasolinera;
    }

    public Integer getPeticion() {
        return numPet;
    }

    public void setGasolinera(Gasolinera gasolinera) {
        this.gasolinera = gasolinera;
    }

    public void setPeticion(Integer numPet) {
        this.numPet = numPet;
    }
}
