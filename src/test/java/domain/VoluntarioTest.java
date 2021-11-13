//package domain;
//
//import constants.Fixture;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class VoluntarioTest extends Fixture {
//  Voluntario voluntario;
//
//  List<String> fotos;
//  String descripcion;
//  Ubicacion ubicacion;
//  LocalDate fecha;
//  Mascota mascota;
//  Rescatista rescatista;
//  RescateSinChapa rescateSinChapa;
//  PublicacionRescate publicacionRescate;
//
//  @BeforeEach
//  void setup() {
//    voluntario = voluntario();
//    fotos = new ArrayList<>(Collections.singletonList("unaFoto"));
//    descripcion = "Canino macho, color negro, raza caniche";
//    ubicacion = ubicacion1();
//    fecha = LocalDate.of(2021, 5, 4);
//    mascota = mascota1();
//    rescatista = rescatista();
//
//    rescateSinChapa = new RescateSinChapa(
//      fotos,
//      descripcion,
//      ubicacion,
//      fecha,
//      mascota,
//      rescatista
//    );
//    publicacionRescate = new PublicacionRescate(rescateSinChapa);
//  }
//
//  @Test
//  void elVoluntarioEsDeTipoUsuarioVoluntario() {
//    assertEquals(voluntario.getUsuario().getTipoUsuario(), TipoUsuario.VOLUNTARIO);
//  }
//
//  @Test
//  void elVoluntarioPuedeAprobarUnaPublicacion() {
//    assertEquals(publicacionRescate.getEstado(), EstadoPublicacion.ESPERA);
//    voluntario.aprobarPublicacion(publicacionRescate);
//    assertEquals(publicacionRescate.getEstado(), EstadoPublicacion.ACEPTADA);
//  }
//
//  @Test
//  void elVoluntarioPuedeRechazarUnaPublicacion() {
//    assertEquals(publicacionRescate.getEstado(), EstadoPublicacion.ESPERA);
//    voluntario.rechazarPublicacion(publicacionRescate);
//    assertEquals(publicacionRescate.getEstado(), EstadoPublicacion.RECHAZADA);
//  }
//
//}
