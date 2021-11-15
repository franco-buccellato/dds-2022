package domain;

import static domain.TipoMascota.GATO;
import static domain.TipoMascota.PERRO;
import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.Fixture;

public class MascotaTest extends Fixture {
  private Mascota perroPepe;
  private Mascota gatoBenito;
  RespuestaCaracteristicaMascota vacunas;
  RespuestaCaracteristicaMascota comportamiento;
  List<RespuestaCaracteristicaMascota> caracteristicas;
  List<String> fotos;

  @BeforeEach
  public void setup() {
    super.generalSetup();
    vacunas = seleccionVacunas();
    comportamiento = seleccionComportamientoConNinios();
    caracteristicas = Arrays.asList(vacunas, comportamiento);

    fotos = new ArrayList<>(Collections.singletonList("unaFoto"));

    perroPepe = new Mascota(
        PERRO,
        "Pedro",
        "Pepe",
        3.0,
        Sexo.MACHO,
        "Tiene pulgas",
        fotos,
        null,
        SituacionMascota.EN_HOGAR_TRANSITORIO
    );
    gatoBenito = new Mascota(
        GATO,
        "Benito",
        "Beno",
        2.0,
        Sexo.MACHO,
        "Esta gordito",
        fotos,
        caracteristicas,
        SituacionMascota.EN_HOGAR_PROPIO
    );
  }

  @Test
  public void noPuedoCrearMascotaSinTipoMascota() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Mascota(null, "Pedro", "Pepe", 3.0, Sexo.MACHO, "Tiene pulgas", null, null, null);
    });

    assertEquals(NOT_NULO.mensaje("tipoMascota"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinNombre() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Mascota(PERRO, null, "Pepe", 3.0, Sexo.MACHO, "Tiene pulgas", null, null, null);
    });

    assertEquals(NOT_NULO.mensaje("nombre"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinApodo() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Mascota(PERRO, "Pedro", null, 3.0, Sexo.MACHO, "Tiene pulgas", null, null, null);
    });

    assertEquals(NOT_NULO.mensaje("apodo"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinEdadAproximada() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Mascota(PERRO, "Pedro", "Pepe", null, Sexo.MACHO, "Tiene pulgas", null, null, null);
    });

    assertEquals(NOT_NULO.mensaje("edadAproximada"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinSexo() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Mascota(PERRO, "Pedro", "Pepe", 4.0, null, "Tiene pulgas", null, null, null);
    });

    assertEquals(NOT_NULO.mensaje("sexo"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinDescripcionFisica() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Mascota(PERRO, "Pedro", "Pepe", 4.0, Sexo.MACHO, null, null, null, null);
    });

    assertEquals(NOT_NULO.mensaje("descripcionFisica"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinFoto() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Mascota(PERRO, "Pedro", "Pepe", 4.0, Sexo.MACHO, "Flaco", null, null, null);
    });

    assertEquals(NOT_NULO.mensaje("fotos"), exception.getMessage());
  }

  @Test
  public void elTipoDeUnPerroEsPerro() {
    assertEquals("PERRO", perroPepe.getTipoMascota().toString());
  }

  @Test
  public void elTipoDeUnGatoEsGato() {
    assertEquals("GATO", gatoBenito.getTipoMascota().toString());
  }

  @Test
  public void puedoActualizarFotoDePerro() {
    List<String> fotosNuevas = new ArrayList<>(Collections.singletonList("fotoNueva"));

    assertEquals(1, perroPepe.getFotos().size());
    assertEquals(fotos, perroPepe.getFotos());

    perroPepe.setFotos(fotosNuevas);

    assertEquals(1, perroPepe.getFotos().size());
    assertEquals(fotosNuevas, perroPepe.getFotos());
  }

  @Test
  public void puedoActualizarCaracteristicasDeMascota() {
    assertEquals(2, gatoBenito.getCaracteristicas().size());

    RespuestaCaracteristicaMascota castrado = seleccionEstaCastrado();
    gatoBenito.setCaracteristicas(Collections.singletonList(castrado));

    assertEquals(1, gatoBenito.getCaracteristicas().size());
    assertEquals(castrado, gatoBenito.getCaracteristicas().get(0));
    assertTrue(gatoBenito.getCaracteristicas()
                   .get(0)
                   .getSelecciones()
                   .get(0)
                   .getDescripcion()
                   .equals("Si")
    );
  }

  @Test
  public void puedoAgregarFotoAMascota() {
    String fotoNueva = "fotoNueva";

    assertEquals(1, perroPepe.getFotos().size());
    assertEquals(fotos, perroPepe.getFotos());

    perroPepe.addFoto(fotoNueva);

    assertEquals(2, perroPepe.getFotos().size());
    assertEquals(fotoNueva, perroPepe.getFotos().get(1));
  }

  @Test
  public void puedoAgregarCaracteristicaAMascotaSinCaracteristicas() {
    assertEquals(0, perroPepe.getCaracteristicas().size());

    perroPepe.addCaracteristica(vacunas);

    assertEquals(1, perroPepe.getCaracteristicas().size());
    assertEquals(vacunas, perroPepe.getCaracteristicas().get(0));
    assertEquals(
        "Vacunas administradas",
        perroPepe.getCaracteristicas().get(0).getPregunta().getDescripcion()
    );
  }

  @Test
  public void puedoAgregarCaracteristicaAMascotaConCaracteristicas() {
    perroPepe.addCaracteristica(vacunas);
    assertEquals(1, perroPepe.getCaracteristicas().size());

    RespuestaCaracteristicaMascota datoDeInteres = seleccionDatoDeInteres();
    perroPepe.addCaracteristica(datoDeInteres);

    assertEquals(2, perroPepe.getCaracteristicas().size());
    assertEquals(datoDeInteres, perroPepe.getCaracteristicas().get(1));
    assertEquals(
        "Datos de interes",
        perroPepe.getCaracteristicas().get(1).getPregunta().getDescripcion()
    );
  }
}
