package domain;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("text")
public class PreguntaText extends Pregunta {
  public PreguntaText() {
  }

  public PreguntaText(List<ObjetivoPregunta> objetivos, String descripcion, List<Opcion> opciones, Boolean obligatoria) {
    super(objetivos, descripcion, opciones, obligatoria);
  }

  @Override
  public Boolean sonSeleccionesValidas(List<Opcion> selecciones) {
    return selecciones.size() == 1 && !selecciones.get(0).getDescripcion().isEmpty();
  }
}
