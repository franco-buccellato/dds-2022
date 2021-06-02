package domain;

import constants.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoluntarioTest {
  Voluntario voluntario;

  List<String> fotos;
  String descripcion;
  Ubicacion ubicacion;
  LocalDate fecha;
  Mascota mascota;
  Rescatista rescatista;
  RescateSinChapa rescateSinChapa;
  Publicacion publicacion;

  @BeforeEach
  void setup() {
    Fixture fixture = new Fixture();
    voluntario = fixture.voluntario();
    fotos = new ArrayList<>(Collections.singletonList("unaFoto"));
    descripcion = "Canino macho, color negro, raza caniche";
    ubicacion = fixture.ubicacion1();
    fecha = LocalDate.of(2021, 5, 4);
    mascota = fixture.mascota();
    rescatista = fixture.rescatista();
    rescateSinChapa = new RescateSinChapa(
        fotos,
        descripcion,
        ubicacion,
        fecha,
        mascota,
        rescatista
    );
    publicacion = new Publicacion(rescateSinChapa);
  }

  @Test
  void elVoluntarioEsDeTipoUsuarioVoluntario() {
    assertEquals(voluntario.usuario.getTipoUsuario(), TipoUsuario.VOLUNTARIO);
  }

  @Test
  void elNombreDelVoluntarioEsVoluntario1(){
    assertEquals(voluntario.usuario.getNombreUsuario(), "Voluntario1");
  }

  @Test
  void elVoluntarioPuedeAprobarUnaPublicacion(){
    assertEquals(publicacion.getEstado(), EstadoPublicacion.ESPERA);
    voluntario.aprobarPublicacion(publicacion);
    assertEquals(publicacion.getEstado(), EstadoPublicacion.ACEPTADA);
  }

  @Test
  void elVoluntarioPuedeRechazarUnaPublicacion(){
    assertEquals(publicacion.getEstado(), EstadoPublicacion.ESPERA);
    voluntario.rechazarPublicacion(publicacion);
    assertEquals(publicacion.getEstado(), EstadoPublicacion.RECHAZADA);
  }

}
