package domain;

import constants.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PublicacionTest {

  List<String> fotos;
  String descripcion;
  Ubicacion ubicacion;
  LocalDate fecha;
  Mascota mascota;
  Rescatista rescatista;
  RescateSinChapa rescate;
  Publicacion publicacion;

  @BeforeEach
  void setup() {
    Fixture fixture = new Fixture();

    fotos = new ArrayList<>(Collections.singletonList("unaFoto"));
    descripcion = "Canino macho, color negro, raza caniche";
    ubicacion = fixture.ubicacion1();
    fecha = LocalDate.of(2021, 5, 4);
    mascota = fixture.mascota();
    rescatista = fixture.rescatista();
    rescate = new RescateSinChapa(
        fotos,
        descripcion,
        ubicacion,
        fecha,
        mascota,
        rescatista
    );

    publicacion = new Publicacion(rescate);
  }

  @Test
  void cuandoSeCreaLaPublicacionSuEstadoEsEnEspera() {
    assertEquals(publicacion.getEstado(), EstadoPublicacion.ESPERA);
  }

}
