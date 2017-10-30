package busqueda.local;

import IA.Gasolina.Gasolinera;

public class PeticionAtendida {

    private Gasolinera gasolinera;
    private Integer peticion;

    public PeticionAtendida() {
    }

    public Gasolinera getGasolinera() {
        return gasolinera;
    }

    public Integer getPeticion() {
        return peticion;
    }

    public void setGasolinera(Gasolinera gasolinera) {
        this.gasolinera = gasolinera;
    }

    public void setPeticion(Integer peticion) {
        this.peticion = peticion;
    }
}
