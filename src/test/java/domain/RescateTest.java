package domain;

import constants.Fixture;
import domain.exception.MascotaSinDuenioException;
import domain.repositorios.RepositorioAsociaciones;
import domain.repositorios.RepositorioDuenio;
import domain.repositorios.RepositorioPublicacionesRescate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RescateTest extends Fixture {
  List<String> fotos;
  String descripcion;
  Ubicacion ubicacion;
  LocalDate fecha;
  Mascota mascota;
  Rescatista rescatista;
  Duenio duenio;

  Asociacion asociacion1, asociacion2, asociacion3;
  RepositorioAsociaciones repositorioAsociacionesTest;

  @BeforeEach
  void setup() {
    fotos = new ArrayList<>(Collections.singletonList("unaFoto"));
    descripcion = "Canino macho, color negro, raza caniche";
    ubicacion = ubicacion1();
    fecha = LocalDate.of(2021, 5, 4);
    mascota = mascota1();
    rescatista = rescatista();
    duenio = duenio();

    asociacion1 = new Asociacion("Asociacion1", ubicacionAsociacion1());
    asociacion2 = new Asociacion("Asociacion2", ubicacionAsociacion2());
    asociacion3 = new Asociacion("Asociacion3", ubicacionAsociacion3());
    repositorioAsociacionesTest = RepositorioAsociaciones.getRepositorioAsociaciones();
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
    verify(duenioMock).notificarMascotaEncontrada(rescateConChapa);
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

  @Test
  void alInformarRescateSinChapaSeCreaPublicacion() {
    RepositorioPublicacionesRescate repositorioPublicacionesRescate = RepositorioPublicacionesRescate.getRepositorioPublicaciones();
    repositorioPublicacionesRescate.setPublicaciones(new ArrayList<>(Collections.emptyList()));
    assertEquals(0, repositorioPublicacionesRescate.getPublicaciones().size());

    RescateSinChapa rescateSinChapa = new RescateSinChapa(
        fotos,
        descripcion,
        ubicacion,
        fecha,
        mascota,
        rescatista
    );
    assertEquals(1, repositorioPublicacionesRescate.getPublicaciones().size());
  }

  @Test
  void noPuedoConseguirHogaresMuyCercaDeMiUbicacion() throws KeyManagementException, NoSuchAlgorithmException {
    RescateSinChapa rescateSinChapa = new RescateSinChapa(
        fotos,
        descripcion,
        ubicacion,
        fecha,
        mascota,
        rescatista
    );

    rescateSinChapa.buscarHogarDeTransito(0.1);
    assertEquals(0, rescateSinChapa.getHogaresTransito().size());
  }
}
