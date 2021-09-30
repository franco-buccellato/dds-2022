package domain;

import constants.Fixture;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class CaracteristicaTest extends Fixture {

  @Test
  public void puedoSetearCaracteristicaBooleana() {
    CaracteristicaChoice estaCastrada = estaCastrada();
    Opcion opcionSeleccionada = estaCastrada.getOpciones().get(0);

    estaCastrada.seleccionarOpcion(opcionSeleccionada, true);
    assertTrue(opcionSeleccionada.getSeleccionada());
    assertTrue(estaCastrada.getOpcionesSeleccionas().contains(opcionSeleccionada));

    estaCastrada.seleccionarOpcion(opcionSeleccionada, false);
    assertFalse(opcionSeleccionada.getSeleccionada());
    assertFalse(estaCastrada.getOpcionesSeleccionas().contains(opcionSeleccionada));
  }

  @Test
  public void puedoCompletarUnaCaracteristicaTexto(){
    CaracteristicaInput datoCurioso = datosDeInteres();
    String dato = "Ronca mucho cuando duerme";
    datoCurioso.setInput(new Opcion(dato));

    assertEquals(datoCurioso.getOpcionesSeleccionas().get(0).getDescripcion(), dato);
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
    List<Opcion> opciones = vacunas.getOpciones();
    opciones.get(0).setSeleccionada(true);
    opciones.get(1).setSeleccionada(true);

    assertEquals(vacunas.getOpcionesSeleccionas().get(0).getDescripcion(), opciones.get(0).getDescripcion());
    assertEquals(vacunas.getOpcionesSeleccionas().get(1).getDescripcion(), opciones.get(1).getDescripcion());
  }
  @Test
  public void puedoSetearUnaCaracteristicaSingleChoice(){
    CaracteristicaChoice comportamiento = comportamientoConNiños();
    int index = setOpcionRandom(comportamiento.getOpciones());
    List<Opcion> opciones = comportamiento.getOpciones();

    assertEquals(comportamiento.getOpcionesSeleccionas().get(0).getDescripcion(), opciones.get(index).getDescripcion());
  }
}
