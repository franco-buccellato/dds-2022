package domain;

import static domain.TipoPregunta.CHECKBOX;
import static domain.exception.Mensajes.NOT_NULO;

import java.util.List;
import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = TipoPregunta.Values.CHECKBOX)
public class PreguntaCheckBox extends Pregunta {
  public PreguntaCheckBox() {
  }

  public PreguntaCheckBox(List<ObjetivoPregunta> objetivos, String descripcion, List<Opcion> opciones, Boolean obligatoria) {
    super(CHECKBOX, objetivos, descripcion, obligatoria);
    this.opciones = Objects.requireNonNull(opciones, NOT_NULO.mensaje("opciones"));
  }

  @Override
  public Boolean esMismaPregunta(Pregunta pregunta) {
    return this.getId().equals(pregunta.getId());
  }

  @Override
  public Boolean sonMismasSelecciones(Opcion seleccion1, Opcion seleccion2) {
    return seleccion1.getId().equals(seleccion2.getId());
  }

  @Override
  public Boolean sonSeleccionesValidas(List<Opcion> selecciones) {
    return selecciones.size() > 0
           &&
           selecciones.stream().allMatch(
               seleccion -> this.getOpciones().stream()
                   .anyMatch(opcion -> opcion.esMismaOpcion(seleccion))
           );
  }
}
