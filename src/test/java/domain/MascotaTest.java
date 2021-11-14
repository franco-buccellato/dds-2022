package domain;

import static domain.exception.Mensajes.NOT_NULO;
import static domain.TipoCaracteristica.*;
import static domain.TipoMascota.*;
import static org.junit.jupiter.api.Assertions.*;

import constants.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MascotaTest extends Fixture {
  private Mascota perroPepe;
  private Mascota gatoBenito;
  CaracteristicaChoice vacunas;
  CaracteristicaChoice comportamiento;
  List<RespuestaCaracteristica> caracteristicas;
  List<String> fotos;

  @BeforeEach
  public void setup() {
    vacunas = vacunas();
    RespuestaCaracteristica Respuestavacuna = new RespuestaCaracteristica(vacunas, Collections.singletonList(vacunas.getOpciones().get(0)));

    comportamiento = comportamientoConNinios();
    RespuestaCaracteristica RespuestaComportamiento = new RespuestaCaracteristica(comportamiento, Collections.singletonList(comportamientoManso));

    caracteristicas = Arrays.asList(Respuestavacuna, RespuestaComportamiento);

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

    CaracteristicaChoice castrado = estaCastrada();
    RespuestaCaracteristica RespuestaCastrado = new RespuestaCaracteristica(castrado, Collections.singletonList(si));
    gatoBenito.setCaracteristicas(Collections.singletonList(RespuestaCastrado));

    assertEquals(1, gatoBenito.getCaracteristicas().size());
    assertEquals(castrado, gatoBenito.getCaracteristicas().get(0).getCaracteristica());
    assertEquals(BOOLEAN, gatoBenito.getCaracteristicas().get(0).getCaracteristica().getTipoCaracteristica());
    assertTrue(gatoBenito.getCaracteristicas().get(0).getCaracteristica().getOpciones().get(0).getDescripcion().equals("Si"));
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

    CaracteristicaChoice vacunas = vacunas();
    RespuestaCaracteristica RespuestaVacunas = new RespuestaCaracteristica(vacunas, Collections.singletonList(vacunas.getOpciones().get(0)));
    perroPepe.setCaracteristicas(Collections.singletonList(RespuestaVacunas));

    perroPepe.addCaracteristica(RespuestaVacunas);

    assertEquals(1, perroPepe.getCaracteristicas().size());
    assertEquals(vacunas, perroPepe.getCaracteristicas().get(0).getCaracteristica());
    assertEquals(CHECKBOX, perroPepe.getCaracteristicas().get(0).getCaracteristica().getTipoCaracteristica());
    assertEquals("Vacunas administradas", perroPepe.getCaracteristicas().get(0).getCaracteristica().getDescripcion());
  }

  @Test
  public void puedoAgregarCaracteristicaAMascotaConCaracteristicas() {
    CaracteristicaChoice vacunas = vacunas();
    RespuestaCaracteristica RespuestaVacunas = new RespuestaCaracteristica(vacunas, Collections.singletonList(vacunas.getOpciones().get(0)));
    perroPepe.addCaracteristica(RespuestaVacunas);

    assertEquals(1, perroPepe.getCaracteristicas().size());

    CaracteristicaInput datoDeInteres = datosDeInteres();
    String dato = "Le gusta perseguir motos";
    RespuestaCaracteristica respuestaDatoDeInteres = new RespuestaCaracteristica(datoDeInteres, Collections.singletonList(new Opcion(dato)));
    perroPepe.addCaracteristica(respuestaDatoDeInteres);

    assertEquals(2, perroPepe.getCaracteristicas().size());
    assertEquals(datoDeInteres, perroPepe.getCaracteristicas().get(1).getCaracteristica());
    assertEquals(TEXT, perroPepe.getCaracteristicas().get(1).getCaracteristica().getTipoCaracteristica());
    assertEquals("Datos de interes", perroPepe.getCaracteristicas().get(1).getCaracteristica().getDescripcion());
  }
}
