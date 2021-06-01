package constants;

import domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class Fixture {
  public Rescatista rescatista() {
    return new Rescatista(datoPersonal(), contacto(), ubicacion1());
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
        BigDecimal.valueOf(56.54684),
        BigDecimal.valueOf(56.54684)
    );
  }

  public Ubicacion ubicacion2() {
    return new Ubicacion(
        "El Rescatista 123",
        "1417",
        "CABA",
        BigDecimal.valueOf(54.54684),
        BigDecimal.valueOf(58.54684)
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
}
