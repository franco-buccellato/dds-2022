package domain;

import static domain.TipoCaracteristica.BOOLEAN;

import java.util.Arrays;
import java.util.List;

public class CaracteristicaBooleana extends Caracteristica {
  private List<Opcion> opciones;

  public CaracteristicaBooleana(String descripcion, Boolean obligatoria) {
    super(BOOLEAN, descripcion, obligatoria);
    this.opciones = Arrays.asList(new Opcion("Verdadero"), new Opcion("Falso"));
  }

  public List<Opcion> getOpciones() {
    return opciones;
  }
}
