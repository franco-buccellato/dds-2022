package domain;

import java.util.List;
import java.util.Set;

public class CaracteristicaChoice extends Caracteristica {
  // Aplica para tipos CHECKBOX, BULLET, BOOLEAN donde los constraints se resuelven en otra capa
  public CaracteristicaChoice(
      TipoCaracteristica tipoCaracteristica,
      String descripcion,
      List<Opcion> opciones,
      Set<AlcanceCaracteristica> alcanceCaracteristica
  ) {
    super(tipoCaracteristica, descripcion, alcanceCaracteristica);
    this.opciones = opciones;
  }

  public void removeOpciones(List<Opcion> opciones) {
    this.opciones.removeAll(opciones);
  }

  public void addOpciones(List<Opcion> opciones) {
    this.opciones.addAll(opciones);
  }
}
