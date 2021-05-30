package domain;

import domain.mascota.CaracteristicaBooleana;
import domain.mascota.CaracteristicaChoice;
import domain.mascota.CaracteristicaInput;
import domain.mascota.Opcion;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static domain.mascota.TipoCaracteristica.*;

public class Caracteristicas {
  protected CaracteristicaBooleana estaCastrada() {
    return new CaracteristicaBooleana("Esta Castrada:", false);
  }

  protected CaracteristicaInput datosDeInteres() {
    return new CaracteristicaInput(TEXT, "Datos de interes", false);
  }

  protected CaracteristicaInput visitasAlVeterinarioUltimoAnio() {
    return new CaracteristicaInput(NUMBER, "Cantidad de consultas veterinarias", false);
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

  protected static int setOpcionRandom(List<Opcion> opciones) {
    Random random = new Random();
    int index = random.nextInt(opciones.size());

    opciones.get(index).setSeleccionada(true);
    return index;
  }
}
