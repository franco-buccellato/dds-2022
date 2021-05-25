package domain.mascota;

import static domain.TipoMascota.*;
import static org.junit.jupiter.api.Assertions.*;
import static domain.exception.Mensajes.NOT_NULO;

import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
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
  Image fotoPerro;
  List<Image> fotos;

  @BeforeEach
  public void setup() throws IOException {
    colorPrincipalNaranja = new Caracteristica(new TipoCaracteristicaTextoLibre("Color Principal"), "naranja");
    colorSecundarioBlanco = new Caracteristica(new TipoCaracteristicaTextoLibre("Color Secundario"), "blanco");
    caracteristicas = new ArrayList<>(Collections.singletonList(colorPrincipalNaranja));

    fotoPerro = ImageIO.read(new File("resources/images/perro.jpg"));
    fotos = new ArrayList<>(Collections.singletonList(fotoPerro));

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
  public void puedoActualizarFotoDePerro() throws IOException {
    Image fotoNueva = ImageIO.read(new File("resources/images/perro.jpg"));
    List<Image> fotosNuevas = new ArrayList<>(Collections.singletonList(fotoNueva));

    assertEquals(1, perroPepe.getFotos().size());
    assertEquals(fotoPerro, perroPepe.getFotos().get(0));

    perroPepe.setFotos(fotosNuevas);

    assertEquals(1, perroPepe.getFotos().size());
    assertEquals(fotoNueva, perroPepe.getFotos().get(0));
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
  public void puedoAgregarFotoAMascota() throws IOException {
    Image fotoNueva = ImageIO.read(new File("resources/images/perro.jpg"));

    assertEquals(1, perroPepe.getFotos().size());
    assertEquals(fotoPerro, perroPepe.getFotos().get(0));

    perroPepe.addFoto(fotoNueva);

    assertEquals(2, perroPepe.getFotos().size());
    assertEquals(fotoPerro, perroPepe.getFotos().get(0));
    assertEquals(fotoNueva, perroPepe.getFotos().get(1));
  }

  @Test
  public void puedoAgregarCaracteristicaAMascotaSinCaracteristicas() {
    assertNull(perroPepe.getCaracteristicas());

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
