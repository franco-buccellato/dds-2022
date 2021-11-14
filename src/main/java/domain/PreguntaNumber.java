package domain;

import java.util.List;
import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("number")
public class PreguntaNumber extends Pregunta {
  public PreguntaNumber() {
  }

  public PreguntaNumber(List<ObjetivoPregunta> objetivos, String descripcion, List<Opcion> opciones, Boolean obligatoria) {
    super(objetivos, descripcion, opciones, obligatoria);
  }

  @Override
  public Boolean sonSeleccionesValidas(List<Opcion> selecciones) {
    String seleccion = selecciones.get(0).getDescripcion();

    Boolean esValida = selecciones.size() == 1 && !Objects.isNull(seleccion) && !seleccion.isEmpty();

    if (esValida) {
      try {
        Double.parseDouble(seleccion);
      } catch (NumberFormatException numberFormatException) {
        return false;
      }
    }

    return true;
  }
}
