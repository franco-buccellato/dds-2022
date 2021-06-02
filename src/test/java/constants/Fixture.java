package constants;

import domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class Fixture {

  public Rescatista rescatista() {
    return new Rescatista(datoPersonal(), contacto(), ubicacion1());
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
    return new Contacto(
      "Pedro",
      "Gonzalez",
      "494949",
      "pedro@g.com",
      Vinculo.AMISTAD
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

  public Mascota mascota() {
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

  public Duenio duenio() {
    return new Duenio(datoPersonal(), Arrays.asList(contacto()), null, null);
  }
}
