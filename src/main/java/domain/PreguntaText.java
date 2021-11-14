package domain;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = TipoPregunta.Values.TEXT)
public class PreguntaText extends Pregunta {
  public PreguntaText() {
  }

  public PreguntaText(List<ObjetivoPregunta> objetivos, String descripcion, Boolean obligatoria) {
    super(objetivos, descripcion, obligatoria);
  }

  @Override
  public Boolean esMismaPregunta(Pregunta pregunta) {
    return this.getDescripcion().equals(pregunta.getDescripcion());
  }

  @Override
  public Boolean sonMismasSelecciones(Opcion seleccion1, Opcion seleccion2) {
    return seleccion1.getDescripcion().equals(seleccion2.getDescripcion());
  }

  @Override
  public Boolean sonSeleccionesValidas(List<Opcion> selecciones) {
    return selecciones.size() == 1 && !selecciones.get(0).getDescripcion().isEmpty();
  }
}
