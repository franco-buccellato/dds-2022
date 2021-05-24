package domain.mascota;

import static domain.mascota.TipoCaracteristica.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CaracteristicaTest {

  @Test
  public void puedoSetearCaracteristicaCastrada(){
    CaracteristicaBooleana estaCastrada = estaCastrada();
    estaCastrada.setSeleccionada(true);
    assertTrue(estaCastrada.getSeleccionada());
    estaCastrada.setSeleccionada(false);
    assertFalse(estaCastrada.getSeleccionada());
  }

  @Test
  public void puedoAgregarUnDatoCurioso(){
    CaracteristicaInput datoCurioso = datosDeInteres();
    String dato = "Ronca mucho cuando duerme";

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
    Integer index1 = setOpcionRandom(vacunas.getOpciones());
    Integer index2 = setOpcionRandom(vacunas.getOpciones());

    assertTrue(vacunas.getOpciones().get(index1).getSeleccionada());
    assertTrue(vacunas.getOpciones().get(index2).getSeleccionada());
  }
  @Test
  public void puedoSetearComportamiento(){
    CaracteristicaChoice comportamiento = comportamientoConNiños();
    Integer index = setOpcionRandom(comportamiento.getOpciones());

    assertTrue(comportamiento.getOpciones().get(index).getSeleccionada());
  }

  private CaracteristicaBooleana estaCastrada() {
    return new CaracteristicaBooleana("Esta Castrada:", false);
  }
  private CaracteristicaInput datosDeInteres() {
    return new CaracteristicaInput(TEXT, "Datos de interes", false);
  }
  private CaracteristicaChoice vacunas() {
    Opcion moquillo = new Opcion("Moquillo");
    Opcion hepatitis = new Opcion("Hepatitis");
    Opcion parvovirosis = new Opcion("Parvovirosis");
    Opcion rabia = new Opcion("Rabia");
    List<Opcion> vacunas = Arrays.asList(moquillo, hepatitis, parvovirosis, rabia);

    return new CaracteristicaChoice(CHECKBOX, "Vacunas administradas", vacunas, true);
  }
  private CaracteristicaChoice comportamientoConNiños() {
    Opcion jugueton = new Opcion("Jugueton");
    Opcion agresivo = new Opcion("Agresivo");
    Opcion desinteres = new Opcion("Desinteres");
    List<Opcion> comportamientos = Arrays.asList(jugueton, agresivo, desinteres);

    return new CaracteristicaChoice(BULLET, "Comportamiento con los niños", comportamientos, true);
  }
  private Integer setOpcionRandom(List<Opcion> opciones) {
    Random random = new Random();
    Integer index = random.nextInt(opciones.size());

    opciones.get(index).setSeleccionada(true);
    return index;
  }
}
