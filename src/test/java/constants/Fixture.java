package constants;

import domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Fixture {
  public Rescatista rescatista() {
    return new Rescatista(datoPersonal(), contacto(), ubicacion());
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

  public Ubicacion ubicacion() {
    return new Ubicacion(
        "El Rescatista 123",
        "1417",
        "CABA",
        BigDecimal.valueOf(56.54684),
        BigDecimal.valueOf(56.54684)
    );
  }
}
