package domain.mascota;

import static domain.exception.Mensajes.NOT_NULO;
import static domain.mascota.TipoCaracteristica.*;
import static domain.mascota.TipoMascota.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MascotaTest {
  Mascota perroPepe;
  Mascota gatoBenito;
  CaracteristicaChoice vacunas;
  CaracteristicaChoice comportamiento;
  List<Caracteristica> caracteristicas;
  Image fotoPerro;
  List<Image> fotos;

  @BeforeEach
  public void setup() throws IOException {
    vacunas = vacunas();
    vacunas.getOpciones().get(0).setSeleccionada(true);
    comportamiento = comportamientoConNiños();
    comportamiento.getOpciones().get(0).setSeleccionada(true);
    caracteristicas = Arrays.asList(vacunas, comportamiento);

    fotoPerro = ImageIO.read(new File("resources/images/perro.jpg"));
    fotos = new ArrayList<>(Collections.singletonList(fotoPerro));

    perroPepe = new Mascota(PERRO, "Pedro", "Pepe", 3.0, "Masculino", "Tiene pulgas", fotos, null);
    gatoBenito = new Mascota(GATO, "Benito", "Beno", 2.0, "Masculino", "Esta gordito", fotos, caracteristicas);
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
  public void noPuedoCrearMascotaSinFoto() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Mascota(PERRO, "Pedro", "Pepe", 4.0, "Masculino", "Flaco", null, null);
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
  public void unPerroConFotoTieneUnaFotoDePerro() {
    assertEquals(1, perroPepe.getFotos().size());
    assertEquals(fotoPerro, perroPepe.getFotos().get(0));
  }

  @Test
  public void unaMascotaSinCaracteristicasTieneNullCaracteristicas() {
    assertNull(perroPepe.getCaracteristicas());
  }

  @Test
  public void lasCaracteristicasConColorPrincipalNaranjaEsColorPrincipalNaranja() {
    assertEquals(2, gatoBenito.getCaracteristicas().size());
    assertEquals(vacunas, gatoBenito.getCaracteristicas().get(0));
    assertEquals( CHECKBOX, gatoBenito.getCaracteristicas().get(0).getTipoCaracteristica());
    assertEquals("Vacunas administradas" , gatoBenito.getCaracteristicas().get(0).getDescripcion());
  }

  @Test
  public void puedoActualizarEdadAproximadaDeMascota() {
    assertEquals(3, perroPepe.getEdadAproximada());
    perroPepe.setEdadAproximada(4.5);
    assertEquals(4.5, perroPepe.getEdadAproximada());
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
    assertEquals(2, gatoBenito.getCaracteristicas().size());

    CaracteristicaBooleana castrado = estaCastrada();
    castrado.setSeleccionada(true);
    gatoBenito.setCaracteristicas(Collections.singletonList(castrado));

    assertEquals(1, gatoBenito.getCaracteristicas().size());
    assertEquals(castrado, gatoBenito.getCaracteristicas().get(0));
    assertEquals(BOOLEAN, gatoBenito.getCaracteristicas().get(0).getTipoCaracteristica());
    assertEquals(true, gatoBenito.getCaracteristicas().get(0).getSeleccionada());
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

    perroPepe.addCaracteristica(vacunas);

    assertEquals(1, perroPepe.getCaracteristicas().size());
    assertEquals(vacunas, perroPepe.getCaracteristicas().get(0));
    assertEquals(CHECKBOX, perroPepe.getCaracteristicas().get(0).getTipoCaracteristica());
    assertEquals("Vacunas administradas", perroPepe.getCaracteristicas().get(0).getDescripcion());
  }

  @Test
  void puedoAgregarCaracteristicaAMascotaConCaracteristicas() {
    perroPepe.addCaracteristica(vacunas);
    assertEquals(1, perroPepe.getCaracteristicas().size());

    Caracteristica datoDeInteres = datosDeInteres();
    String dato = "Le gusta perseguir motos";
    datoDeInteres.addOpcion(dato);
    perroPepe.addCaracteristica(datoDeInteres);

    assertEquals(2, perroPepe.getCaracteristicas().size());
    assertEquals(datoDeInteres, perroPepe.getCaracteristicas().get(1));
    assertEquals(TEXT, perroPepe.getCaracteristicas().get(1).getTipoCaracteristica());
    assertEquals("Datos de interes", perroPepe.getCaracteristicas().get(1).getDescripcion());
  }
  protected CaracteristicaBooleana estaCastrada() {
    return new CaracteristicaBooleana("Esta Castrada:", false);
  }
  protected CaracteristicaInput datosDeInteres() {
    return new CaracteristicaInput(TEXT, "Datos de interes", false);
  }
  protected CaracteristicaChoice vacunas() {
    Opcion moquillo = new Opcion("Moquillo");
    Opcion hepatitis = new Opcion("Hepatitis");
    Opcion parvovirosis = new Opcion("Parvovirosis");
    Opcion rabia = new Opcion("Rabia");
    List<Opcion> vacunas = Arrays.asList(moquillo, hepatitis, parvovirosis, rabia);

    return new CaracteristicaChoice(CHECKBOX, "Vacunas administradas", vacunas, true);
  }
  protected CaracteristicaChoice comportamientoConNiños() {
    Opcion jugueton = new Opcion("Jugueton");
    Opcion agresivo = new Opcion("Agresivo");
    Opcion desinteres = new Opcion("Desinteres");
    List<Opcion> comportamientos = Arrays.asList(jugueton, agresivo, desinteres);

    return new CaracteristicaChoice(BULLET, "Comportamiento con los niños", comportamientos, true);
  }
}
