package domain;

import java.util.*;

import static domain.exception.Mensajes.NOT_NULO;

public class CaracteristicaInput extends Caracteristica {
  private String input;

  public CaracteristicaInput(TipoCaracteristica tipoCaracteristica, String descripcion, String input, Boolean obligatoria) {
    super(tipoCaracteristica, descripcion, obligatoria);
    this.input = Objects.requireNonNull(input, NOT_NULO.mensaje("input"));
  }

  public void setInput(String input) {
    this.input = input;
  }

  @Override
  public List getOpciones() {
    return Arrays.asList(input);
  }

  @Override
  public List<String> getOpcionesSeleccionas() {
    if (this.input != "") {
      return Arrays.asList(input);
    }
    return Collections.emptyList();
  }
}
