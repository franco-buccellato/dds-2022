package domain;

import java.util.Arrays;
import java.util.Set;

public class CaracteristicaInput extends Caracteristica {
  private String input;

  // Aplica para tipos TEXTO y NUMERO donde los constraints van a ser resueltos en otra capa
  public CaracteristicaInput(
      TipoCaracteristica tipoCaracteristica,
      String descripcion,
      Set<AlcanceCaracteristica> alcanceCaracteristica
  ) {
    super(tipoCaracteristica, descripcion, alcanceCaracteristica);
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
