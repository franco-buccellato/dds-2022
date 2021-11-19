package domain;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import constants.Fixture;
import domain.repositorios.RepositorioAsociaciones;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PublicacionRescateTest extends Fixture {
  List<String> fotos;
  String descripcion;
  Ubicacion ubicacion;
  LocalDate fecha;
  Mascota mascota;
  Rescatista rescatista;
  RescateSinChapa rescate;
  PublicacionRescate publicacionRescate;
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
    asociacion1 = new Asociacion("Asociacion1", ubicacionAsociacion1());
    asociacion2 = new Asociacion("Asociacion2", ubicacionAsociacion2());
    asociacion3 = new Asociacion("Asociacion3", ubicacionAsociacion3());
    rescate = new RescateSinChapa(
        fotos,
        descripcion,
        ubicacion,
        fecha,
        mascota,
        rescatista
    );
    publicacionRescate = new PublicacionRescate(rescate);


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
      new PublicacionRescate(null);
    });
    assertEquals(NOT_NULO.mensaje("rescate"), exception.getMessage());
  }

  @Test
  void cuandoSeCreaLaPublicacionSuEstadoEsEnEspera() {
    assertEquals(publicacionRescate.getEstado(), EstadoPublicacion.ESPERA);
  }

  @Test
  void alBuscarAsociacionMasCercanaALaPublicacionDevuelveLaAsociacion() {
    publicacionRescate.buscarAsociacionCercana();
    assertEquals(publicacionRescate.getAsociacion(), asociacion1);
  }
/*
  @Test
  void alNotificarRescatistaElRescatistaEsNotificado() throws NoSePudoEnviarMailException {
    Rescate rescateMock = mock(Rescate.class);
    Rescatista rescatistaMock = mock(Rescatista.class);
    Contacto contactoMock = mock(Contacto.class);
    Duenio duenioMock = mock(Duenio.class);
    Contacto contactoDuenioMock = mock(Contacto.class);
    Asociacion asociacionMock = mock(Asociacion.class);
    Ubicacion ubicacionMock = mock(Ubicacion.class);
    Publicacion publicacion = new Publicacion(rescateMock);

    when(rescateMock.getRescatista()).thenReturn(rescatistaMock);
    when(rescateMock.getRescatista().getContacto()).thenReturn(contactoMock);
    when(duenioMock.contactoTitular()).thenReturn(contactoDuenioMock);
    when(rescatistaMock.getUbicacion()).thenReturn(ubicacionMock);
    when(publicacion.buscarAsociacionCercana()).thenReturn(asociacionMock);

    publicacion.notificarRescatista(duenioMock);

    assertEquals(publicacion.getRescate().getRescatista().getContacto(), contactoMock);

    verify(contactoMock).notificar(any());
  }
 */
}
