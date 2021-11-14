package domain;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = TipoPregunta.Values.BULLET)
public class PreguntaBullet extends Pregunta {

  public PreguntaBullet() {
  }

  public PreguntaBullet(List<ObjetivoPregunta> objetivos, String descripcion, List<Opcion> opciones, Boolean obligatoria) {
    super(objetivos, descripcion, opciones, obligatoria);
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
    return selecciones.size() == 1 && this.getOpciones().stream().anyMatch(
        opcion -> opcion.esMismaOpcion(selecciones.get(0))
    );
  }
}
