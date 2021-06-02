package domain;

import constants.Fixture;
import domain.exception.MascotaSinDuenioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RescateTest {
  List<String> fotos;
  String descripcion;
  Ubicacion ubicacion;
  LocalDate fecha;
  Mascota mascota;
  Rescatista rescatista;
  Duenio duenio;

  @BeforeEach
  void setup() {
    Fixture fixture = new Fixture();
    fotos = new ArrayList<>(Collections.singletonList("unaFoto"));
    descripcion = "Canino macho, color negro, raza caniche";
    ubicacion = fixture.ubicacion1();
    fecha = LocalDate.of(2021, 5, 4);
    mascota = fixture.mascota1();
    rescatista = fixture.rescatista();
    duenio = fixture.duenio();
  }

  @Test
  void noPuedoCrearUnRescateSinFotos() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new RescateSinChapa(null, descripcion, ubicacion, fecha, mascota, rescatista);
    });
    assertEquals(NOT_NULO.mensaje("fotos"), exception.getMessage());
  }

  @Test
  void noPuedoCrearUnRescateSinDescripcion() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new RescateSinChapa(fotos, null, ubicacion, fecha, mascota, rescatista);
    });
    assertEquals(NOT_NULO.mensaje("descripcion"), exception.getMessage());
  }

  @Test
  void noPuedoCrearUnRescateSinLugarDeEncuentro() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new RescateSinChapa(fotos, descripcion, null, fecha, mascota, rescatista);
    });
    assertEquals(NOT_NULO.mensaje("lugarEncuentro"), exception.getMessage());
  }

  @Test
  void noPuedoCrearUnRescateSinFecha() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new RescateSinChapa(fotos, descripcion, ubicacion, null, mascota, rescatista);
    });
    assertEquals(NOT_NULO.mensaje("fecha"), exception.getMessage());
  }

  @Test
  void noPuedoCrearUnRescateSinMascota() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new RescateSinChapa(fotos, descripcion, ubicacion, fecha, null, rescatista);
    });
    assertEquals(NOT_NULO.mensaje("mascota"), exception.getMessage());
  }

  @Test
  void noPuedoCrearUnRescateSinRescatista() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new RescateSinChapa(fotos, descripcion, ubicacion, fecha, mascota, null);
    });
    assertEquals(NOT_NULO.mensaje("rescatista"), exception.getMessage());
  }

  @Test
  void alCrearUnRescateLaMascotaEstaEnHogarTransitorio() {
    new RescateSinChapa(
        fotos,
        descripcion,
        ubicacion,
        fecha,
        mascota,
        rescatista
    );
    assertEquals(SituacionMascota.EN_HOGAR_TRANSITORIO, mascota.getSituacionMascota());
  }

  @Test
  void alInformarRescateConChapaSeInformaAlDuenioDeLaMascota() {
    Duenio duenioMock = mock(Duenio.class);
    RepositorioDuenio repositorioDuenio = RepositorioDuenio.getInstance();
    repositorioDuenio.addDuenio(duenioMock);

    when(duenioMock.getMascotas()).thenReturn(new ArrayList<>(Arrays.asList(mascota)));

    RescateConChapa rescateConChapa = new RescateConChapa(
        fotos,
        descripcion,
        ubicacion,
        fecha,
        mascota,
        rescatista
    );

    rescateConChapa.informaRescate();

    verify(duenioMock).notificarMascotaEncontrada(mascota);
  }

  @Test
  void alInformarRescateConChapaSinDuenioLanzaUnaExcepcion() {
    Exception exception = assertThrows(MascotaSinDuenioException.class, () -> {
      new RescateConChapa(
          fotos,
          descripcion,
          ubicacion,
          fecha,
          mascota,
          rescatista
      ).informaRescate();
    });
    assertEquals("La mascota buscada no tiene duenio", exception.getMessage());
  }
}
