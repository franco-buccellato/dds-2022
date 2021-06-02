package domain;

import constants.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositorioPublicacionesTest {


  List<String> fotos;
  String descripcion1, descripcion2, descripcion3;
  Ubicacion ubicacion1, ubicacion2, ubicacion3;
  LocalDate fecha;
  Mascota mascota1, mascota2, mascota3;
  Rescatista rescatista;
  RescateSinChapa rescate1, rescate2, rescate3;
  Publicacion publicacion1, publicacion2, publicacion3;
  RepositorioPublicaciones repositorioPublicaciones;
  Voluntario voluntario;

  @BeforeEach
  void setup() {
    Fixture fixture = new Fixture();
    fotos = new ArrayList<>(Collections.singletonList("unaFoto"));
    descripcion1 = "Canino macho, color negro, raza caniche";
    ubicacion1 = fixture.ubicacion1();
    fecha = LocalDate.of(2021, 5, 4);
    mascota1 = fixture.mascota1();
    rescatista = fixture.rescatista();
    rescate1 = new RescateSinChapa(
        fotos,
        descripcion1,
        ubicacion1,
        fecha,
        mascota1,
        rescatista
    );
    fotos = new ArrayList<>(Collections.singletonList("unaFoto"));
    descripcion2 = "Gato hembra, color claro, raza siames";
    ubicacion2 = fixture.ubicacion2();
    fecha = LocalDate.of(2021, 5, 4);
    mascota2 = fixture.mascota2();
    rescatista = fixture.rescatista();
    rescate2 = new RescateSinChapa(
        fotos,
        descripcion2,
        ubicacion2,
        fecha,
        mascota2,
        rescatista
    );
    fotos = new ArrayList<>(Collections.singletonList("unaFoto"));
    descripcion3 = "Gato hembra, color atigrado, raza europeo";
    ubicacion3 = fixture.ubicacion2();
    fecha = LocalDate.of(2021, 5, 4);
    mascota3 = fixture.mascota3();
    rescatista = fixture.rescatista();
    rescate3 = new RescateSinChapa(
        fotos,
        descripcion3,
        ubicacion3,
        fecha,
        mascota3,
        rescatista
    );
    publicacion1 = new Publicacion(rescate1);
    publicacion2 = new Publicacion(rescate2);
    publicacion3 = new Publicacion(rescate3);
    repositorioPublicaciones = RepositorioPublicaciones.getRepositorioPublicaciones();
    repositorioPublicaciones.setPublicaciones(new ArrayList<>(Arrays.asList(
        publicacion1,
        publicacion2,
        publicacion3))
    );
    voluntario = fixture.voluntario();
  }

  @Test
  void elRepositorioPublicacionesTestPosee3Asociaciones() {
    assertEquals(repositorioPublicaciones.size(), 3);
  }

  @Test
  void todasLasPublicacionesInicialmenteEstanEnEspera() {
    assertEquals(
        repositorioPublicaciones
            .getPublicaciones()
            .stream()
            .filter(
                publicacion -> publicacion
                                   .getEstado() == EstadoPublicacion.ESPERA)
            .collect(Collectors.toList())
            .size(),
        3
    );
  }

  @Test
  void voluntarioApruebaUnaPublicacionEntoncesQuedanDosEnEspera() {
    assertEquals(repositorioPublicaciones.size(), 3);
    voluntario.aprobarPublicacion(repositorioPublicaciones.getPublicaciones().get(0));
    assertEquals(
        repositorioPublicaciones
            .getPublicaciones()
            .stream()
            .filter(
                publicacion -> publicacion
                                   .getEstado() == EstadoPublicacion.ESPERA)
            .collect(Collectors.toList())
            .size(),
        2
    );
    assertEquals(
        repositorioPublicaciones
            .getPublicaciones()
            .stream()
            .filter(
                publicacion -> publicacion
                                   .getEstado() == EstadoPublicacion.ACEPTADA)
            .collect(Collectors.toList())
            .size(),
        1
    );
  }

}