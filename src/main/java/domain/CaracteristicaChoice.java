package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("C")
public class CaracteristicaChoice extends Caracteristica {

  public CaracteristicaChoice() {
    super();
  }

  // Aplica para tipos CHECKBOX, BULLET, BOOLEAN donde los constraints se resuelven en otra capa
  public CaracteristicaChoice(
      TipoCaracteristica tipoCaracteristica,
      String descripcion,
      List<Opcion> opciones,
      Boolean obligatoria
  ) {
    super(tipoCaracteristica, descripcion, obligatoria);
    this.opciones = opciones;
  }

  @Override
  public List<Opcion> getOpciones() {
    return opciones;
  }

  @Override
  public String toString() {
    return this.descripcion + " " + this.opciones.stream()
        .map(opcion -> opcion.getDescripcion() + "\n")
        .reduce(String::concat);
  }

  @Override
  public Boolean esRespuestaValida(List<Opcion> opcionesSeleccionadas) {
    return opcionesSeleccionadas
        .stream()
        .allMatch(
            opcionSeleccionada -> this.getOpciones()
              .stream()
              .anyMatch(
                  opcion -> opcion.getId().equals(opcionSeleccionada.getId())
              )
    );
  }

  @Override
  public Boolean tieneMismasOpciones(Pregunta pregunta) {
    return null;
  }

  @Override
  public String setRespuesta(List<Opcion> opciones) {
    return null;
  }
}
