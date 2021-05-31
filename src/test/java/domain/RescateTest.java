package domain;

import constants.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.*;

public class RescateTest {
  List<String> fotos;
  String descripcion;
  Ubicacion ubicacion;
  LocalDate fecha;
  Rescatista rescatista;

  @BeforeEach
  void setup() {
    Fixture fixture = new Fixture();
    fotos = new ArrayList<>(Collections.singletonList("unaFoto"));
    descripcion = "Canino macho, color negro, raza caniche";
    ubicacion = fixture.ubicacion();
    fecha = LocalDate.of(2021, 5, 4);
    rescatista = fixture.rescatista();
  }

  @Test
  void noPuedoCrearUnRescateSinFotos() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Rescate(null, descripcion, ubicacion, fecha, rescatista);
    });
    assertEquals(NOT_NULO.mensaje("fotos"), exception.getMessage());
  }

  @Test
  void noPuedoCrearUnRescateSinDescripcion() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Rescate(fotos, null, ubicacion, fecha, rescatista);
    });
    assertEquals(NOT_NULO.mensaje("descripcion"), exception.getMessage());
  }

  @Test
  void noPuedoCrearUnRescateSinLugarDeEncuentro() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Rescate(fotos, descripcion, null, fecha, rescatista);
    });
    assertEquals(NOT_NULO.mensaje("lugarEncuentro"), exception.getMessage());
  }

  @Test
  void noPuedoCrearUnRescateSinFecha() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Rescate(fotos, descripcion, ubicacion, null, rescatista);
    });
    assertEquals(NOT_NULO.mensaje("fecha"), exception.getMessage());
  }

  @Test
  void noPuedoCrearUnRescateSinRescatista() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Rescate(fotos, descripcion, ubicacion, fecha, null);
    });
    assertEquals(NOT_NULO.mensaje("rescatista"), exception.getMessage());
  }
}
