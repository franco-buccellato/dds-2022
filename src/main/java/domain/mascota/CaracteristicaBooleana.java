package domain.mascota;

import java.util.Arrays;
import java.util.List;

import static domain.mascota.TipoCaracteristica.BOOLEAN;

public class CaracteristicaBooleana extends Caracteristica{
  private List<Opcion> opciones;

  public CaracteristicaBooleana(String descripcion, Boolean obligatoria) {
    super(BOOLEAN, descripcion, obligatoria);
    this.opciones = Arrays.asList(new Opcion("Verdadero"), new Opcion("Falso"));
  }

  public List<Opcion> getOpciones() {
    return opciones;
  }
}
