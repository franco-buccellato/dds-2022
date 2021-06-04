package domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import constants.Fixture;
import org.junit.jupiter.api.Test;

public class CaracteristicaTest extends Fixture {

  @Test
  public void puedoSetearCaracteristicaBooleana() {
    CaracteristicaChoice estaCastrada = estaCastrada();
    estaCastrada.getOpciones().get(0).setSeleccionada(true);
    assertTrue(estaCastrada.getOpciones().get(0).getSeleccionada());
    estaCastrada.getOpciones().get(0).setSeleccionada(false);
    assertFalse(estaCastrada.getOpciones().get(0).getSeleccionada());
  }

  @Test
  public void puedoCompletarUnaCaracteristicaTexto(){
    CaracteristicaInput datoCurioso = datosDeInteres();
    String dato = "Ronca mucho cuando duerme";
    datoCurioso.addOpcion(dato);

    assertTrue(datoCurioso.getOpcion().equals(dato));
  }
  @Test
  public void puedoDejarOpcionalUnaCaracteristicaTexto(){
    CaracteristicaInput datoCurioso = datosDeInteres();
    datoCurioso.addOpcion("");
    assertTrue(datoCurioso.getOpcion().equals(""));
  }
  @Test
  public void puedoSetearUnaCaracteristicaMultipleChoice(){
    CaracteristicaChoice vacunas = vacunas();
    int index1 = setOpcionRandom(vacunas.getOpciones());
    int index2 = setOpcionRandom(vacunas.getOpciones());

    assertTrue(vacunas.getOpciones().get(index1).getSeleccionada());
    assertTrue(vacunas.getOpciones().get(index2).getSeleccionada());
  }
  @Test
  public void puedoSetearUnaCaracteristicaSingleChoice(){
    CaracteristicaChoice comportamiento = comportamientoConNiños();
    int index = setOpcionRandom(comportamiento.getOpciones());

    assertTrue(comportamiento.getOpciones().get(index).getSeleccionada());
  }
}
