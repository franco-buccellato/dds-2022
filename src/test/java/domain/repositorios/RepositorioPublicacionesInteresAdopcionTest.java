//package domain.repositorios;
//
//import constants.Fixture;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class RepositorioPublicacionesInteresAdopcionTest extends Fixture {
//    RepositorioPublicacionesAdopcion repositorio;
//
//    @BeforeEach
//    public void setup() {
//        repositorio = RepositorioPublicacionesAdopcion.getRepositorioPublicaciones();
//        repositorio.setPublicaciones(Arrays.asList(
//                publicacionAdopcion1(),
//                publicacionAdopcion2(),
//                publicacionAdopcion3())
//        );
//    }
//
//    @Test
//    public void puedoRecomendarAdopcion() {
//        //2da publicacion solamente matchea. El unico requirimiento es que sea un gato, no se especificaron otras
//        assertEquals(1, repositorio.getAdopcionesRecomendadas(publicacionInteresAdopcion1()).size());
//
//        //1era y 2da publicacion matchean. La publicacion tiene 3  preguntas, 2 requerimientos se necesitan
//        assertEquals(2, repositorio.getAdopcionesRecomendadas(publicacionInteresAdopcion2()).size());
//
//        //Ninguna publicacion matchea porque tiene 2 requerimientos y las publicaciones solo cumplen 1
//        assertEquals(0, repositorio.getAdopcionesRecomendadas(publicacionInteresAdopcion3()).size());
//    }
//}
//
//
//
//
