package domain;

import constants.Fixture;
import domain.repositorios.RepositorioPublicacionesRescate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositorioPublicacionesRescateTest extends Fixture {
  List<String> fotos;
  String descripcion1, descripcion2, descripcion3;
  Ubicacion ubicacion1, ubicacion2, ubicacion3;
  LocalDate fecha;
  Mascota mascota1, mascota2, mascota3;
  Rescatista rescatista;
  RescateSinChapa rescate1, rescate2, rescate3;
  PublicacionRescate publicacionRescate1, publicacionRescate2, publicacionRescate3;
  RepositorioPublicacionesRescate repositorioPublicacionesRescate;
  Voluntario voluntario;

  @BeforeEach
  void setup() {
    fotos = new ArrayList<>(Collections.singletonList("unaFoto"));
    descripcion1 = "Canino macho, color negro, raza caniche";
    ubicacion1 = ubicacion1();
    fecha = LocalDate.of(2021, 5, 4);
    mascota1 = mascota1();
    rescatista = rescatista();
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
    ubicacion2 = ubicacion2();
    fecha = LocalDate.of(2021, 5, 4);
    mascota2 = mascota2();
    rescatista = rescatista();
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
    ubicacion3 = ubicacion2();
    fecha = LocalDate.of(2021, 5, 4);
    mascota3 = mascota3();
    rescatista = rescatista();
    rescate3 = new RescateSinChapa(
        fotos,
        descripcion3,
        ubicacion3,
        fecha,
        mascota3,
        rescatista
    );
    publicacionRescate1 = new PublicacionRescate(rescate1);
    publicacionRescate2 = new PublicacionRescate(rescate2);
    publicacionRescate3 = new PublicacionRescate(rescate3);
    repositorioPublicacionesRescate = RepositorioPublicacionesRescate.getRepositorioPublicaciones();
    repositorioPublicacionesRescate.setPublicaciones(new ArrayList<>(Arrays.asList(
            publicacionRescate1,
            publicacionRescate2,
            publicacionRescate3))
    );
    voluntario = voluntario();
  }

  @Test
  void elRepositorioPublicacionesTestPosee3Asociaciones() {
    assertEquals(repositorioPublicacionesRescate.getPublicaciones().size(), 3);
  }

  @Test
  void todasLasPublicacionesInicialmenteEstanEnEspera() {
    assertEquals(repositorioPublicacionesRescate.getEnEspera().size(), 3);
  }

  @Test
  void voluntarioApruebaUnaPublicacionEntoncesQuedanDosEnEspera() {
    assertEquals(repositorioPublicacionesRescate.getPublicaciones().size(), 3);
    voluntario.aprobarPublicacion(repositorioPublicacionesRescate.getPublicaciones().get(0));
    assertEquals(repositorioPublicacionesRescate.getEnEspera().size(), 2);
    assertEquals(repositorioPublicacionesRescate.getAceptadas().size(), 1);
  }

  @Test
  void voluntarioRechazaPublicacionEntoncesQuedaUnaRechazada() {
    assertEquals(repositorioPublicacionesRescate.getPublicaciones().size(), 3);
    voluntario.rechazarPublicacion(repositorioPublicacionesRescate.getPublicaciones().get(0));
    assertEquals(repositorioPublicacionesRescate.getRechazadas().size(), 1);
    assertEquals(repositorioPublicacionesRescate.getEnEspera().size(), 2);
  }
}