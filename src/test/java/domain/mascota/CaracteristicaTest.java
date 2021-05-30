package domain.mascota;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import domain.Caracteristicas;

public class CaracteristicaTest extends Caracteristicas {

  @Test
  public void puedoSetearCaracteristicaCastrada(){
    CaracteristicaBooleana estaCastrada = estaCastrada();
    estaCastrada.getOpciones().get(0).setSeleccionada(true);
    assertTrue(estaCastrada.getOpciones().get(0).getSeleccionada());
    estaCastrada.getOpciones().get(0).setSeleccionada(false);
    assertFalse(estaCastrada.getOpciones().get(0).getSeleccionada());
  }

  @Test
  public void puedoAgregarUnDatoCurioso(){
    CaracteristicaInput datoCurioso = datosDeInteres();
    String dato = "Ronca mucho cuando duerme";
    datoCurioso.addOpcion(dato);

    assertTrue(datoCurioso.getOpciones().equals(dato));
  }
  @Test
  public void puedoDejarOpcionalUnDatoCurioso(){
    CaracteristicaInput datoCurioso = datosDeInteres();
    datoCurioso.addOpcion("");
    assertTrue(datoCurioso.getOpciones().equals(""));
  }
  @Test
  public void puedoSetearVacunas(){
    CaracteristicaChoice vacunas = vacunas();
    int index1 = setOpcionRandom(vacunas.getOpciones());
    int index2 = setOpcionRandom(vacunas.getOpciones());

    assertTrue(vacunas.getOpciones().get(index1).getSeleccionada());
    assertTrue(vacunas.getOpciones().get(index2).getSeleccionada());
  }
  @Test
  public void puedoSetearComportamiento(){
    CaracteristicaChoice comportamiento = comportamientoConNiños();
    int index = setOpcionRandom(comportamiento.getOpciones());

    assertTrue(comportamiento.getOpciones().get(index).getSeleccionada());
  }
}
