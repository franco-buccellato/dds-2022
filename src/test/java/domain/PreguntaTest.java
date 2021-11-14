package domain;

import constants.Fixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PreguntaTest extends Fixture {
  @Test
  public void puedoSaberSiSeleccionesSonvalidas() {
    List<Opcion> opcionSeleccionadaValida = Collections.singletonList(estaCastrada.getOpciones().get(0));
    List<Opcion> opcionSeleccionadaVariasInvalida = Arrays.asList(estaCastrada.getOpciones().get(0), estaCastrada.getOpciones().get(1));
    List<Opcion> opcionSeleccionadaOpcionInvalida = Collections.singletonList(tamanio().getOpciones().get(0));

    Assertions.assertTrue(estaCastrada.sonSeleccionesValidas(opcionSeleccionadaValida));
    Assertions.assertFalse(estaCastrada.sonSeleccionesValidas(Collections.emptyList()));
    Assertions.assertFalse(estaCastrada.sonSeleccionesValidas(opcionSeleccionadaVariasInvalida));
    Assertions.assertFalse(estaCastrada.sonSeleccionesValidas(opcionSeleccionadaOpcionInvalida));
  }
  @Test
  public void puedoSaberSiPreguntasSonLasMismas() {
    Pregunta preguntaEstaCastradaAsociacion = asociacionConPreguntasAdopcion().getPreguntasAdopcion().get(0);
    Pregunta preguntaEstaVacunasAsociacion = asociacionConPreguntasAdopcion().getPreguntasAdopcion().get(1);

    Assertions.assertTrue(estaCastrada.esMismaPregunta(preguntaEstaCastradaAsociacion));
    Assertions.assertFalse(estaCastrada.esMismaPregunta(preguntaEstaVacunasAsociacion));
  }
}


