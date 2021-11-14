package domain;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("bullet")
public class PreguntaBullet extends Pregunta {

  public PreguntaBullet() {
  }

  public PreguntaBullet(List<ObjetivoPregunta> objetivos, String descripcion, List<Opcion> opciones, Boolean obligatoria) {
    super(objetivos, descripcion, opciones, obligatoria);
  }

  @Override
  public Boolean sonSeleccionesValidas(List<Opcion> selecciones) {
    return selecciones.size() == 1 && this.getOpciones().stream().anyMatch(
        opcion -> opcion.esMismaOpcion(selecciones.get(0))
    );
  }
}
