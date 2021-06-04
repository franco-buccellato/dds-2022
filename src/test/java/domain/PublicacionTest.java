package domain;

import constants.Fixture;
import domain.exception.NoSePudoEnviarMailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PublicacionTest extends Fixture {

  List<String> fotos;
  String descripcion;
  Ubicacion ubicacion;
  LocalDate fecha;
  Mascota mascota;
  Rescatista rescatista;
  RescateSinChapa rescate;
  Publicacion publicacion;
  Asociacion asociacion1;
  Asociacion asociacion2;
  Asociacion asociacion3;
  Duenio duenio;

  @BeforeEach
  void setup() {
    fotos = new ArrayList<>(Collections.singletonList("unaFoto"));
    descripcion = "Canino macho, color negro, raza caniche";
    ubicacion = ubicacion1();
    fecha = LocalDate.of(2021, 5, 4);
    mascota = mascota1();
    rescatista = rescatista();

    rescate = new RescateSinChapa(
        fotos,
        descripcion,
        ubicacion,
        fecha,
        mascota,
        rescatista
    );
    publicacion = new Publicacion(rescate);

    asociacion1 = new Asociacion("Asociacion1", ubicacionAsociacion1());
    asociacion2 = new Asociacion("Asociacion2", ubicacionAsociacion2());
    asociacion3 = new Asociacion("Asociacion3", ubicacionAsociacion3());
    RepositorioAsociaciones repositorioAsociacionesTest = RepositorioAsociaciones.getRepositorioAsociaciones();
    repositorioAsociacionesTest.setAsociaciones(new ArrayList<>(Arrays.asList(
        asociacion1,
        asociacion2,
        asociacion3
    )));

    duenio = duenio();
  }

  @Test
  void noPuedoCrearPublicacionSinRescate() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      new Publicacion(null);
    });
    assertEquals(NOT_NULO.mensaje("rescate"), exception.getMessage());
  }

  @Test
  void cuandoSeCreaLaPublicacionSuEstadoEsEnEspera() {
    assertEquals(publicacion.getEstado(), EstadoPublicacion.ESPERA);
  }

  @Test
  void alBuscarAsociacionMasCercanaALaPublicacionDevuelveLaAsociacion() {
    publicacion.buscarAsociacionCercana();
    assertEquals(publicacion.getAsociacion(), asociacion1);
  }

  @Test
  void alNotificarRescatistaElRescatistaEsNotificado() throws NoSePudoEnviarMailException {
    Rescate rescateMock = mock(Rescate.class);
    Rescatista rescatistaMock = mock(Rescatista.class);
    Contacto contactoMock = mock(Contacto.class);
    Duenio duenioMock = mock(Duenio.class);
    Contacto contactoDuenioMock = mock(Contacto.class);
    Publicacion publicacion = new Publicacion(rescateMock);

    when(rescateMock.getRescatista()).thenReturn(rescatistaMock);
    when(rescateMock.getRescatista().getContacto()).thenReturn(contactoMock);
    when(duenioMock.contactoTitular()).thenReturn(contactoDuenioMock);

    publicacion.notificarRescatista(duenioMock);

    assertEquals(publicacion.getRescate().getRescatista().getContacto(), contactoMock);

    verify(contactoMock).notificar(any());
  }
}
