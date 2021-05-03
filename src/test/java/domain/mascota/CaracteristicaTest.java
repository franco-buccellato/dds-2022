package domain.mascota;

import static constants.Mensajes.NOT_NULO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaracteristicaTest {
  TipoCaracteristica colorPrincipal;
  Caracteristica colorPrincipalAzul;
  TipoCaracteristica colorSecundario;
  Caracteristica colorSecundarioRojo;
  TipoCaracteristica estaCastrada;
  Caracteristica estaCastradaSi;

  @BeforeEach
  public void setup() {
    colorPrincipal = new TipoCaracteristica("Color Principal");
    colorPrincipalAzul = new Caracteristica(colorPrincipal, "azul");

    colorSecundario = new TipoCaracteristica("Color Secundario");
    colorSecundarioRojo = new Caracteristica(colorSecundario, "rojo");

    estaCastrada = new TipoCaracteristica("Esta Castrada");
    estaCastradaSi = new Caracteristica(estaCastrada, "si");
  }

  @Test
  public void puedoCrearUnTipoCaracteristica() {
    assertNotNull(colorPrincipal);
  }

  @Test
  public void puedoCrearUnaCaracteristica() {
    assertNotNull(colorPrincipalAzul);
  }

  @Test
  public void noPuedoCrearUnTipoCaracteristicaSinDescripcion() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new TipoCaracteristica(null);
    });

    assertEquals(NOT_NULO.mensaje("descripcion"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearUnaCaracteristicaSinTipoCaracteristica() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Caracteristica(null, "verde");
    });

    assertEquals(NOT_NULO.mensaje("tipoCaracteristica"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearUnaCaracteristicaSinDescripcion() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Caracteristica(colorSecundario, null);
    });

    assertEquals(NOT_NULO.mensaje("descripcion"), exception.getMessage());
  }

  @Test
  public void laDescripcionDeColorPrincipalEsColorPrincipal() {
    assertEquals("Color Principal", colorPrincipal.getDescripcion());
  }

  @Test
  public void laDescripcionDeColorSecundarioEsColorSecundario() {
    assertEquals("Color Secundario", colorSecundario.getDescripcion());
  }

  @Test
  public void laDescripcionDeEstaCastradaEsEstaCastrada() {
    assertEquals("Esta Castrada", estaCastrada.getDescripcion());
  }

  @Test
  public void elTipoDeColorPrincipalAzulEsColorPrincipal() {
    assertEquals("Color Principal", colorPrincipalAzul.getTipoCaracteristica().getDescripcion());
  }

  @Test
  public void elTipoDeColorSecundarioRojoEsColorSecundario() {
    assertEquals("Color Secundario", colorSecundarioRojo.getTipoCaracteristica().getDescripcion());
  }

  @Test
  public void elTipoDeEstaCastradaSiEsEstaCastrada() {
    assertEquals("Esta Castrada", estaCastradaSi.getTipoCaracteristica().getDescripcion());
  }

  @Test
  public void laDescricpionDeColorPrincipalAzulEsAzul() {
    assertEquals("azul", colorPrincipalAzul.getDescripcion());
  }

  @Test
  public void laDescripcionDeColorSecundarioRojoEsRojo() {
    assertEquals("rojo", colorSecundarioRojo.getDescripcion());
  }

  @Test
  public void laDescripcionDeEstaCastradaSiEsSi() {
    assertEquals("si", estaCastradaSi.getDescripcion());
  }
}
