package domain;

import constants.Fixture;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CaracteristicaTest extends Fixture {

  @Test
  public void puedoSetearCaracteristicaBooleana() {
    CaracteristicaChoice estaCastrada = estaCastrada();
    Opcion opcionSeleccionada = (Opcion) estaCastrada.getOpciones().get(0);

    estaCastrada.seleccionarOpcion(opcionSeleccionada, true);
    assertTrue(opcionSeleccionada.getSeleccionada());
    assertTrue(estaCastrada.getOpcionesSeleccionas().contains(opcionSeleccionada.getDescripcion()));

    estaCastrada.seleccionarOpcion(opcionSeleccionada, false);
    assertFalse(opcionSeleccionada.getSeleccionada());
    assertFalse(estaCastrada.getOpcionesSeleccionas().contains(opcionSeleccionada.getDescripcion()));
  }

  @Test
  public void puedoCompletarUnaCaracteristicaTexto(){
    CaracteristicaInput datoCurioso = datosDeInteres();
    String dato = "Ronca mucho cuando duerme";
    datoCurioso.setInput(new Opcion(dato));

    assertTrue(datoCurioso.getOpcionesSeleccionas().get(0).equals(dato));
  }
  @Test
  public void puedoDejarOpcionalUnaCaracteristicaTexto(){
    CaracteristicaInput datoCurioso = datosDeInteres();
    datoCurioso.setInput(new Opcion(""));
    assertTrue(datoCurioso.getOpciones().get(0).getDescripcion().equals(""));
  }
  @Test
  public void puedoSetearUnaCaracteristicaMultipleChoice(){
    CaracteristicaChoice vacunas = vacunas();
    int index1 = setOpcionRandom(vacunas.getOpciones());
    int index2 = setOpcionRandom(vacunas.getOpciones());
    List<Opcion> opciones = vacunas.getOpciones();

    assertEquals(vacunas.getOpcionesSeleccionas().get(0), opciones.get(index1).getDescripcion());
    assertEquals(vacunas.getOpcionesSeleccionas().get(1), opciones.get(index2).getDescripcion());
  }
  @Test
  public void puedoSetearUnaCaracteristicaSingleChoice(){
    CaracteristicaChoice comportamiento = comportamientoConNiños();
    int index = setOpcionRandom(comportamiento.getOpciones());
    List<Opcion> opciones = comportamiento.getOpciones();

    assertEquals(comportamiento.getOpcionesSeleccionas().get(0), opciones.get(index).getDescripcion());
  }
}
