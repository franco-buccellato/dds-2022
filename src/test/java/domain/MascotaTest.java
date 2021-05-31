package domain;

import static domain.TipoMascota.*;
import static org.junit.jupiter.api.Assertions.*;
import static domain.exception.Mensajes.NOT_NULO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MascotaTest {
  Mascota perroPepe;
  Mascota gatoBenito;
  Caracteristica colorPrincipalNaranja;
  Caracteristica colorSecundarioBlanco;
  List<Caracteristica> caracteristicas;
  List<String> fotos;

  @BeforeEach
  public void setup() {
    colorPrincipalNaranja = new Caracteristica(new TipoCaracteristicaTextoLibre("Color Principal"), "naranja");
    colorSecundarioBlanco = new Caracteristica(new TipoCaracteristicaTextoLibre("Color Secundario"), "blanco");
    caracteristicas = new ArrayList<>(Collections.singletonList(colorPrincipalNaranja));

    fotos = new ArrayList<>(Collections.singletonList("unaFoto"));

    perroPepe = new MascotaConChapa(
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
    gatoBenito = new MascotaConChapa(
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
      new MascotaConChapa(null, "Pedro", "Pepe", 3.0, Sexo.MACHO, "Tiene pulgas", null, null, null);
    });

    assertEquals(NOT_NULO.mensaje("tipoMascota"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinNombre() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new MascotaConChapa(PERRO, null, "Pepe", 3.0, Sexo.MACHO, "Tiene pulgas", null, null, null);
    });

    assertEquals(NOT_NULO.mensaje("nombre"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinApodo() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new MascotaConChapa(PERRO, "Pedro", null, 3.0, Sexo.MACHO, "Tiene pulgas", null, null, null);
    });

    assertEquals(NOT_NULO.mensaje("apodo"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinEdadAproximada() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new MascotaConChapa(PERRO, "Pedro", "Pepe", null, Sexo.MACHO, "Tiene pulgas", null, null, null);
    });

    assertEquals(NOT_NULO.mensaje("edadAproximada"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinSexo() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new MascotaConChapa(PERRO, "Pedro", "Pepe", 4.0, null, "Tiene pulgas", null, null, null);
    });

    assertEquals(NOT_NULO.mensaje("sexo"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinDescripcionFisica() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new MascotaConChapa(PERRO, "Pedro", "Pepe", 4.0, Sexo.MACHO, null, null, null, null);
    });

    assertEquals(NOT_NULO.mensaje("descripcionFisica"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinFoto() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new MascotaConChapa(PERRO, "Pedro", "Pepe", 4.0, Sexo.MACHO, "Flaco", null, null, null);
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
    assertEquals(1, gatoBenito.getCaracteristicas().size());
    assertEquals(colorPrincipalNaranja, gatoBenito.getCaracteristicas().get(0));

    gatoBenito.setCaracteristicas(Collections.singletonList(colorSecundarioBlanco));

    assertEquals(1, gatoBenito.getCaracteristicas().size());
    assertEquals(colorSecundarioBlanco, gatoBenito.getCaracteristicas().get(0));
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

    perroPepe.addCaracteristica(colorPrincipalNaranja);

    assertEquals(1, perroPepe.getCaracteristicas().size());
    assertEquals(colorPrincipalNaranja, perroPepe.getCaracteristicas().get(0));
  }

  @Test
  void puedoAgregarCaracteristicaAMascotaConCaracteristicas() {
    assertEquals(1, gatoBenito.getCaracteristicas().size());
    assertEquals(colorPrincipalNaranja, gatoBenito.getCaracteristicas().get(0));

    gatoBenito.addCaracteristica(colorSecundarioBlanco);

    assertEquals(2, gatoBenito.getCaracteristicas().size());
    assertEquals(colorSecundarioBlanco, gatoBenito.getCaracteristicas().get(1));
  }
}
