package domain;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("checkbox")
public class PreguntaCheckBox extends Pregunta {
  public PreguntaCheckBox() {
  }

  public PreguntaCheckBox(List<ObjetivoPregunta> objetivos, String descripcion, List<Opcion> opciones, Boolean obligatoria) {
    super(objetivos, descripcion, opciones, obligatoria);
  }

  @Override
  public Boolean sonSeleccionesValidas(List<Opcion> selecciones) {
    return selecciones.size() > 0
           && selecciones.stream().allMatch(
               seleccion ->
                   this.getOpciones().stream().anyMatch(opcion -> opcion.esMismaOpcion(seleccion))
           );
  }
}
