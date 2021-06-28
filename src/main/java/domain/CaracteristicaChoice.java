package domain;

import java.util.List;
import java.util.stream.Collectors;

public class CaracteristicaChoice extends Caracteristica {
  private List<Opcion> opciones;
  // Aplica para tipos CHECKBOX, BULLET, BOOLEAN donde los constraints se resuelven en otra capa
  public CaracteristicaChoice( TipoCaracteristica tipoCaracteristica, String descripcion, List<Opcion> opciones, Boolean obligatoria) {
    super(tipoCaracteristica, descripcion, obligatoria);
    this.opciones = opciones;
  }

  public void seleccionarOpcion(Opcion opcion, Boolean estado) {
    opcion.setSeleccionada(estado);
  }

  @Override
  public List getOpciones() {
    return opciones;
  }

  @Override
  public List<String> getOpcionesSeleccionas() {
    return opciones
              .stream()
              .filter(Opcion::getSeleccionada)
              .map(Opcion::getDescripcion)
              .collect(Collectors.toList());
  }
}
