package constants;

import static domain.Vinculo.AMISTAD;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import domain.Asociacion;
import domain.Contacto;
import domain.DatoPersonal;
import domain.Duenio;
import domain.Mascota;
import domain.MedioNotificacion;
import domain.Rescatista;
import domain.Sexo;
import domain.SituacionMascota;
import domain.TipoIdentificacion;
import domain.TipoMascota;
import domain.TipoUsuario;
import domain.Ubicacion;
import domain.Usuario;
import domain.Voluntario;

public class Fixture {

  public Rescatista rescatista() {
    return new Rescatista(datoPersonal(), contacto(), ubicacion(), mock(Usuario.class));
  }

  public Voluntario voluntario() {
    return new Voluntario(
      new Usuario(
        "Voluntario1",
        "voluntariocorrecto",
        TipoUsuario.VOLUNTARIO),
      new Asociacion(
        "Asociacion1",
        ubicacionAsociacion1())
    );
  }

  public DatoPersonal datoPersonal() {
    return new DatoPersonal(
      "Juan",
      "Perez",
      TipoIdentificacion.DNI,
      "99919999",
      LocalDate.of(1995, 5, 24)
    );
  }

  public Contacto contacto() {
    MedioNotificacion medioNotificacion = mock(MedioNotificacion.class);
    return new Contacto(
      "Pedro",
      "Gonzalez",
      "494949",
      "pedro@g.com",
      AMISTAD,
      medioNotificacion
    );
  }

  public Ubicacion ubicacion1() {
    return new Ubicacion(
      "El Rescatista 123",
      "1417",
      "CABA",
      BigDecimal.valueOf(56.12345),
      BigDecimal.valueOf(56.12345)
    );
  }

  public Ubicacion ubicacion2() {
    return new Ubicacion(
      "El Rescatista 123",
      "1417",
      "CABA",
      BigDecimal.valueOf(53.12345),
      BigDecimal.valueOf(56.12345)
    );
  }

  public Ubicacion ubicacion3() {
    return new Ubicacion(
      "El Distinto 999",
      "1417",
      "CABA",
      BigDecimal.valueOf(56.12345),
      BigDecimal.valueOf(66.12345)
    );
  }

  public Ubicacion ubicacionAsociacion1() {
    return new Ubicacion(
      "La Asociacion",
      "1",
      "CABA",
      BigDecimal.valueOf(50.54235),
      BigDecimal.valueOf(50.55621)
    );
  }

  public Ubicacion ubicacionAsociacion2() {
    return new Ubicacion(
      "La Asociacion",
      "2",
      "CABA",
      BigDecimal.valueOf(70.12548),
      BigDecimal.valueOf(80.36587)
    );
  }

  public Ubicacion ubicacionAsociacion3() {
    return new Ubicacion(
      "La Asociacion",
      "3",
      "CABA",
      BigDecimal.valueOf(75.58962),
      BigDecimal.valueOf(75.15963)
    );
  }

  public Ubicacion ubicacionRescatista() {
    return new Ubicacion(
      "Le Rescatiste",
      "1",
      "CABA",
      BigDecimal.valueOf(30.45216),
      BigDecimal.valueOf(30.33652)
    );
  }

  public Mascota mascota1() {
    return new Mascota(
      TipoMascota.PERRO,
      "Canela",
      "Cane",
      5.2,
      Sexo.HEMBRA,
      "Gordita",
      Arrays.asList("unaFoto"),
      null,
      SituacionMascota.EN_HOGAR_PROPIO
    );
  }

  public Mascota mascota2() {
    return new Mascota(
        TipoMascota.GATO,
        "Minerva",
        "Mine",
        .7,
        Sexo.HEMBRA,
        "Gordita",
        Arrays.asList("unaFoto"),
        null,
        SituacionMascota.PERDIDA
    );
  }

  public Mascota mascota3() {
    return new Mascota(
        TipoMascota.GATO,
        "Iris",
        "Terremoto",
        .7,
        Sexo.HEMBRA,
        "Atigrada",
        Arrays.asList("unaFoto"),
        null,
        SituacionMascota.PERDIDA
    );
  }

  public Duenio duenio() {
    return new Duenio(datoPersonal(), Arrays.asList(contacto()), null, null);
  }
}
