package domain;

import constants.Fixture;
import domain.exception.MascotaSinDuenioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;

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
    Duenio duenio = new Duenio(datoPersonal, Arrays.asList(contacto), Arrays.asList(mascota), null);

    assertEquals(duenio, repositorioDuenio.findDuenioMascota(mascota).get());
  }

  @Test
  void alInformarRescateDeMascotaSinDuenioLanzaExcepcion() {
    Exception exception = assertThrows(MascotaSinDuenioException.class, () -> {
      repositorioDuenio.informarMascotaRescatada(mascota);
    });

    assertEquals("La mascota buscada no tiene duenio", exception.getMessage());
  }

  //TODO: como testear que efectivamente se notifico a ese duenio específico sin meterse en la implementación de notificar?
  @Disabled
  @Test
  void alInformarRescateDeMascotaConDuenioSeNotificaAlDuenio() {
    Duenio duenio = mock(Duenio.class);
    doAnswer(InvocationOnMock::callRealMethod).when(duenio).setMascotas(Arrays.asList(mascota));
    duenio.setMascotas(Arrays.asList(mascota));
    System.out.println(duenio.getMascotas().toString());
    repositorioDuenio.addDuenio(duenio);

    repositorioDuenio.informarMascotaRescatada(mascota);

    verify(duenio).notificarMascotaEncontrada(mascota);
  }
}
