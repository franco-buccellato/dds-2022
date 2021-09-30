package domain;

import java.util.List;
import java.util.stream.Collectors;

public class CaracteristicaChoice extends Caracteristica {
  // Aplica para tipos CHECKBOX, BULLET, BOOLEAN donde los constraints se resuelven en otra capa
  public CaracteristicaChoice(
      TipoCaracteristica tipoCaracteristica,
      String descripcion,
      List<Opcion> opciones
  ) {
    super(tipoCaracteristica, descripcion);
    this.opciones = opciones;
  }

  public void seleccionarOpcion(Opcion opcion, Boolean estado) {
    opcion.setSeleccionada(estado);
  }

  @Override
  public List<Opcion> getOpciones() {
    return opciones;
  }

  @Override
  public List<Opcion> getOpcionesSeleccionas() {
    return opciones
        .stream()
        .filter(Opcion::getSeleccionada)
        .collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return this.descripcion + " " + this.opciones.stream()
        .filter(Opcion::getSeleccionada)
        .map(opcion -> opcion.getDescripcion() + "\n")
        .reduce(String::concat);
  }
}
