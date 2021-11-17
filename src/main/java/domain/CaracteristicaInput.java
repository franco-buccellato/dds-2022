package domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.*;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.*;

@Entity
@DiscriminatorValue("I")
public class CaracteristicaInput extends Caracteristica {
  @OneToOne//(cascade = CascadeType.PERSIST)
  private Opcion input;

  // Aplica para tipos TEXTO y NUMERO donde los constraints van a ser resueltos en otra capa
  public CaracteristicaInput(
      TipoCaracteristica tipoCaracteristica,
      String descripcion,
      Opcion input
  ) {
    super(tipoCaracteristica, descripcion);
    this.input = Objects.requireNonNull(input, NOT_NULO.mensaje("input"));
  }

  public void setInput(Opcion input) {
    this.input = input;
  }

  @Override
  public List<Opcion> getOpciones() {
    return Arrays.asList(input);
  }

  @Override
  public List<Opcion> getOpcionesSeleccionas() {
    if (this.input.getDescripcion() != "") {
      Opcion opcionInput = new Opcion(input.getDescripcion());
      opcionInput.setSeleccionada(true);

      return Collections.singletonList(opcionInput);
    }
    return Collections.emptyList();
  }

  @Override
  public String toString() {
    return this.descripcion + ": " + this.input.getDescripcion();
  }
}
