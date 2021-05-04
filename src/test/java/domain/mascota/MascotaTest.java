package domain.mascota;

import static constants.Mensajes.NOT_NULO;
import static constants.TipoMascota.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MascotaTest {
  Mascota perroPepe;
  Mascota gatoBenito;
  Caracteristica colorPrincipalNaranja;
  Caracteristica colorSecundarioBlanco;
  List<Caracteristica> caracteristicas;

  @BeforeEach
  public void setup() {
    colorPrincipalNaranja = new Caracteristica(new TipoCaracteristica("Color Principal"), "naranja");
    colorSecundarioBlanco = new Caracteristica(new TipoCaracteristica("Color Secundario"), "blanco");
    caracteristicas = new ArrayList<>(Collections.singletonList(colorPrincipalNaranja));

    perroPepe = new Mascota(PERRO, "Pedro", "Pepe", 3.0, "Masculino", "Tiene pulgas", null, null);
    gatoBenito = new Mascota(GATO, "Benito", "Beno", 2.0, "Masculino", "Esta gordito", null, caracteristicas);
  }

  @Test
  public void puedoCrearUnaMascota() {
    assertNotNull(perroPepe);
  }

  @Test
  public void noPuedoCrearMascotaSinTipoMascota() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Mascota(null, "Pedro", "Pepe", 3.0, "Masculino", "Tiene pulgas", null, null);
    });

    assertEquals(NOT_NULO.mensaje("tipoMascota"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinNombre() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Mascota(PERRO, null, "Pepe", 3.0, "Masculino", "Tiene pulgas", null, null);
    });

    assertEquals(NOT_NULO.mensaje("nombre"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinApodo() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Mascota(PERRO, "Pedro", null, 3.0, "Masculino", "Tiene pulgas", null, null);
    });

    assertEquals(NOT_NULO.mensaje("apodo"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinEdadAproximada() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Mascota(PERRO, "Pedro", "Pepe", null, "Masculino", "Tiene pulgas", null, null);
    });

    assertEquals(NOT_NULO.mensaje("edadAproximada"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinSexo() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Mascota(PERRO, "Pedro", "Pepe", 4.0, null, "Tiene pulgas", null, null);
    });

    assertEquals(NOT_NULO.mensaje("sexo"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaSinDescripcionFisica() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Mascota(PERRO, "Pedro", "Pepe", 4.0, "Masculino", null, null, null);
    });

    assertEquals(NOT_NULO.mensaje("descripcionFisica"), exception.getMessage());
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
  public void elNombreDePedroEsPedro() {
    assertEquals("Pedro", perroPepe.getNombre());
  }

  @Test
  public void elApodoDePepeEsPepe() {
    assertEquals("Pepe", perroPepe.getApodo());
  }

  @Test
  public void laEdadDePerroDeTresEsTres() {
    assertEquals(3, perroPepe.getEdadAproximada());
  }

  @Test
  public void elSexoMasculinoEsMasculino() {
    assertEquals("Masculino", perroPepe.getSexo());
  }

  @Test
  public void laDescripcionTienePulgasEsTienePulgas() {
    assertEquals("Tiene pulgas", perroPepe.getDescripcionFisica());
  }

  @Test
  public void unaMascotaSinFotosTieneNullFotos() {
    assertNull(perroPepe.getFotos());
  }

  @Test
  public void unaMascotaSinCaracteristicasTieneNullCaracteristicas() {
    assertNull(perroPepe.getCaracteristicas());
  }

  @Test
  public void lasCaracteristicasConColorPrincipalNaranjaEsColorPrincipalNaranja() {
    assertEquals(1, gatoBenito.getCaracteristicas().size());
    assertEquals(colorPrincipalNaranja, gatoBenito.getCaracteristicas().get(0));
    assertEquals("Color Principal", gatoBenito.getCaracteristicas().get(0).getTipoCaracteristica().getDescripcion());
    assertEquals("naranja", gatoBenito.getCaracteristicas().get(0).getDescripcion());
  }

  @Test
  public void puedoActualizarEdadAproximadaDeMascota() {
    assertEquals(3, perroPepe.getEdadAproximada());
    perroPepe.setEdadAproximada(4.5);
    assertEquals(4.5, perroPepe.getEdadAproximada());
  }

  @Test
  public void puedoActualizarCaracteristicasDeMascota() {
    assertEquals(1, gatoBenito.getCaracteristicas().size());
    assertEquals(colorPrincipalNaranja, gatoBenito.getCaracteristicas().get(0));
    assertEquals("Color Principal", gatoBenito.getCaracteristicas().get(0).getTipoCaracteristica().getDescripcion());
    assertEquals("naranja", gatoBenito.getCaracteristicas().get(0).getDescripcion());

    gatoBenito.setCaracteristicas(Collections.singletonList(colorSecundarioBlanco));

    assertEquals(1, gatoBenito.getCaracteristicas().size());
    assertEquals(colorSecundarioBlanco, gatoBenito.getCaracteristicas().get(0));
    assertEquals("Color Secundario", gatoBenito.getCaracteristicas().get(0).getTipoCaracteristica().getDescripcion());
    assertEquals("blanco", gatoBenito.getCaracteristicas().get(0).getDescripcion());
  }

  @Test
  public void puedoAgregarCaracteristicaAMascotaSinCaracteristicas() {
    assertNull(perroPepe.getCaracteristicas());

    perroPepe.addCaracteristica(colorPrincipalNaranja);

    assertEquals(1, perroPepe.getCaracteristicas().size());
    assertEquals(colorPrincipalNaranja, perroPepe.getCaracteristicas().get(0));
    assertEquals("Color Principal", perroPepe.getCaracteristicas().get(0).getTipoCaracteristica().getDescripcion());
    assertEquals("naranja", perroPepe.getCaracteristicas().get(0).getDescripcion());
  }

  @Test
  void puedoAgregarCaracteristicaAMascotaConCaracteristicas() {
    assertEquals(1, gatoBenito.getCaracteristicas().size());
    assertEquals(colorPrincipalNaranja, gatoBenito.getCaracteristicas().get(0));
    assertEquals("Color Principal", gatoBenito.getCaracteristicas().get(0).getTipoCaracteristica().getDescripcion());
    assertEquals("naranja", gatoBenito.getCaracteristicas().get(0).getDescripcion());

    gatoBenito.addCaracteristica(colorSecundarioBlanco);

    assertEquals(2, gatoBenito.getCaracteristicas().size());
    assertEquals(colorSecundarioBlanco, gatoBenito.getCaracteristicas().get(1));
    assertEquals("Color Secundario", gatoBenito.getCaracteristicas().get(1).getTipoCaracteristica().getDescripcion());
    assertEquals("blanco", gatoBenito.getCaracteristicas().get(1).getDescripcion());
  }
}
