package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import constants.Fixture;
import domain.repositorios.RepositorioDuenio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RepositorioDuenioTest extends Fixture {
  RepositorioDuenio repositorioDuenio;
  Mascota mascota;
  DatoPersonal datoPersonal;
  Contacto contacto;

  @BeforeEach
  void setup() {
    repositorioDuenio = RepositorioDuenio.getInstance();
    mascota = mascota1();
    datoPersonal = datoPersonal();
    contacto = contacto();
  }

  @Test
  void alBuscarMascotaSinDuenioNoDevuelveDuenio() {
    assertEquals(Optional.empty(), repositorioDuenio.findDuenioMascota(mascota));
  }

  @Test
  void puedoBuscarUnDuenioDeUnaMascota() {
    Duenio duenio = new Duenio(
        datoPersonal,
        new ArrayList<>(Arrays.asList(contacto)),
        new ArrayList<>(Arrays.asList(mascota)),
        null
    );

    assertEquals(duenio, repositorioDuenio.findDuenioMascota(mascota).get());
  }

  @Test
  void alInformarRescateDeMascotaConDuenioSeNotificaAlDuenio() {
    Duenio duenioMock = mock(Duenio.class);
    Rescate rescateMock = mock(Rescate.class);
    Mascota mascotaMock = mock(Mascota.class);

    repositorioDuenio.addDuenio(duenioMock);
    when(duenioMock.getMascotas()).thenReturn(new ArrayList<>(Arrays.asList(mascotaMock)));
    when(rescateMock.getMascota()).thenReturn(mascotaMock);

    repositorioDuenio.informarMascotaRescatada(rescateMock, duenioMock);

    verify(duenioMock).notificarMascotaEncontrada(rescateMock);
  }

  @Test
  void AlBuscarPorUsuarioInexistenteTraeNull() {
    Duenio duenio = RepositorioDuenio.getInstance().getDuenioByIdUsuario(1L);
    assertNull(duenio);
  }
}
