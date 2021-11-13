package domain;

import javax.persistence.*;

import java.util.*;

@Entity
@DiscriminatorValue("I")
public class CaracteristicaInput extends Caracteristica {

  public CaracteristicaInput() {
  }

  // Aplica para tipos TEXTO y NUMERO donde los constraints van a ser resueltos en otra capa
  public CaracteristicaInput(TipoCaracteristica tipoCaracteristica, String descripcion) {
    super(tipoCaracteristica, descripcion, false);
  }

  @Override
  public List<Opcion> getOpciones() {
    return Collections.emptyList();
  }

  @Override
  public String toString() {
    return this.getDescripcion();
  }

  @Override
  public Boolean esRespuestaValida(String respuesta) {
    return true;
  }

  @Override
  public Boolean tieneMismasOpciones(Pregunta pregunta) {
    return this.getDescripcion().equals(pregunta.getDescripcion());
  }
}
