package domain;

import java.util.Arrays;

public class CaracteristicaInput extends Caracteristica {
  private String input;

  // Aplica para tipos TEXTO y NUMERO donde los constraints van a ser resueltos en otra capa
  public CaracteristicaInput(
      TipoCaracteristica tipoCaracteristica,
      String descripcion,
      Boolean obligatoria
  ) {
    super(tipoCaracteristica, descripcion, obligatoria);
    this.opciones = Arrays.asList(new Opcion(""));
    this.input = "";
  }

  public String getOpcion() {
    return this.input;
  }

  public void addOpcion(String texto) {
    this.input = texto;
    opciones.get(0).setSeleccionada(true);
  }

  public void removeOpcion() {
    this.input = "";
    opciones.get(0).setSeleccionada(false);
  }
}
