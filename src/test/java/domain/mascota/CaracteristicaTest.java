package domain.mascota;

import static domain.mascota.TipoCaracteristica.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

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
  protected CaracteristicaBooleana estaCastrada() {
    return new CaracteristicaBooleana("Esta Castrada:", false);
  }
  protected CaracteristicaInput datosDeInteres() {
    return new CaracteristicaInput(TEXT, "Datos de interes", false);
  }
  protected CaracteristicaChoice vacunas() {
    Opcion moquillo = new Opcion("Moquillo");
    Opcion hepatitis = new Opcion("Hepatitis");
    Opcion parvovirosis = new Opcion("Parvovirosis");
    Opcion rabia = new Opcion("Rabia");
    List<Opcion> vacunas = Arrays.asList(moquillo, hepatitis, parvovirosis, rabia);

    return new CaracteristicaChoice(CHECKBOX, "Vacunas administradas", vacunas, true);
  }
  protected CaracteristicaChoice comportamientoConNiños() {
    Opcion jugueton = new Opcion("Jugueton");
    Opcion agresivo = new Opcion("Agresivo");
    Opcion desinteres = new Opcion("Desinteres");
    List<Opcion> comportamientos = Arrays.asList(jugueton, agresivo, desinteres);

    return new CaracteristicaChoice(BULLET, "Comportamiento con los niños", comportamientos, true);
  }
  private int setOpcionRandom(List<Opcion> opciones) {
    Random random = new Random();
    int index = random.nextInt(opciones.size());

    opciones.get(index).setSeleccionada(true);
    return index;
  }
}
