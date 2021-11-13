package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

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
      List<OpcionNueva> opciones,
      Boolean obligatoria
  ) {
    super(tipoCaracteristica, descripcion, obligatoria);
    this.opciones = opciones;
  }

  @Override
  public List<OpcionNueva> getOpciones() {
    return opciones;
  }

  @Override
  public String toString() {
    return this.descripcion + " " + this.opciones.stream()
        .map(opcion -> opcion.getDescripcion() + "\n")
        .reduce(String::concat);
  }

  @Override
  public Boolean esRespuestaValida(String respuesta) {
    return this.getOpciones().stream()
        .anyMatch(opcion -> opcion.getDescripcion().equals(respuesta));
  }
}
