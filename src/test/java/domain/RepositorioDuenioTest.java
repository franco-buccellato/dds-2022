package domain;

import constants.Fixture;
import domain.exception.MascotaSinDuenioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RepositorioDuenioTest {
  RepositorioDuenio repositorioDuenio;
  Mascota mascota;
  DatoPersonal datoPersonal;
  Contacto contacto;

  @BeforeEach
  void setup() {
    Fixture fixture = new Fixture();
    repositorioDuenio = RepositorioDuenio.getInstance();
    mascota = fixture.mascota1();
    datoPersonal = fixture.datoPersonal();
    contacto = fixture.contacto();
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
  void alInformarRescateDeMascotaSinDuenioLanzaExcepcion() {
    Rescate rescateMock = mock(Rescate.class);
    when(rescateMock.getMascota()).thenReturn(mascota);
    Exception exception = assertThrows(MascotaSinDuenioException.class, () -> {
      repositorioDuenio.informarMascotaRescatada(rescateMock);
    });

    assertEquals("La mascota buscada no tiene duenio", exception.getMessage());
  }

  @Test
  void alInformarRescateDeMascotaConDuenioSeNotificaAlDuenio() {
    Duenio duenioMock = mock(Duenio.class);
    Rescate rescateMock = mock(Rescate.class);
    Mascota mascotaMock = mock(Mascota.class);

    repositorioDuenio.addDuenio(duenioMock);
    when(duenioMock.getMascotas()).thenReturn(new ArrayList<>(Arrays.asList(mascotaMock)));
    when(rescateMock.getMascota()).thenReturn(mascotaMock);

    repositorioDuenio.informarMascotaRescatada(rescateMock);

    verify(duenioMock).notificarMascotaEncontrada(rescateMock);
  }
}
