package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.List;
import java.util.Objects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = TipoPregunta.Values.BULLET)
public class PreguntaBullet extends Pregunta {

  public PreguntaBullet() {
  }

  public PreguntaBullet(List<ObjetivoPregunta> objetivos, String descripcion, List<Opcion> opciones, Boolean obligatoria) {
    super(objetivos, descripcion, obligatoria);
    this.opciones = Objects.requireNonNull(opciones, NOT_NULO.mensaje("opciones"));
  }

  @Override
  public Boolean sonMismasSelecciones(Opcion seleccion1, Opcion seleccion2) {
    return seleccion1.getId().equals(seleccion2.getId());
  }

  @Override
  public Boolean sonSeleccionesValidas(List<Opcion> selecciones) {
    return selecciones.size() == 1 && this.getOpciones().stream().anyMatch(
        opcion -> opcion.esMismaOpcion(selecciones.get(0))
    );
  }
}
