package domain.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.anyList;

import constants.Fixture;
import domain.Duenio;
import domain.PublicacionAdopcion;
import domain.PublicacionInteresAdopcion;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RepositorioPublicacionesAdopcionTest extends Fixture {
  RepositorioPublicacionesInteresAdopcion repositorioInteresAdopcion;
  RepositorioPublicacionesAdopcion repositorioAdopcion;

  @BeforeEach
  public void setup() {
    repositorioAdopcion = RepositorioPublicacionesAdopcion.getRepositorioPublicaciones();
    repositorioAdopcion.setPublicaciones(Arrays.asList(
        publicacionAdopcion1(),
        publicacionAdopcion2(),
        publicacionAdopcion3()
    ));

    repositorioInteresAdopcion = RepositorioPublicacionesInteresAdopcion.getRepositorioPublicaciones();
    repositorioInteresAdopcion.setPublicaciones(Arrays.asList(
        publicacionInteresAdopcion1(),
        publicacionInteresAdopcion2(),
        publicacionInteresAdopcion3()
    ));
  }

  @Test
  public void puedoEnviarSugerenciasDeAdopcion() {
    Duenio interesadoConPublicacionRecomendada = mock(Duenio.class);
    Duenio interesadoSinPublicacionRecomendada = mock(Duenio.class);
    PublicacionAdopcion publicacion1 = mock(PublicacionAdopcion.class);
    PublicacionInteresAdopcion publicacionInteres1 = mock(PublicacionInteresAdopcion.class);
    PublicacionInteresAdopcion publicacionInteres2 = mock(PublicacionInteresAdopcion.class);
    RepositorioPublicacionesAdopcion repositorioAdopcion = mock(RepositorioPublicacionesAdopcion.class);
    RepositorioPublicacionesInteresAdopcion repositorioInteresAdopcion = RepositorioPublicacionesInteresAdopcion.getRepositorioPublicaciones();
    repositorioInteresAdopcion.setPublicaciones(Arrays.asList(
        publicacionInteres1,
        publicacionInteres2
    ));

    when(repositorioAdopcion.getAdopcionesRecomendadas(publicacionInteres1))
        .thenReturn(Arrays.asList(publicacion1));
    when(publicacionInteres1.getInteresado()).thenReturn(interesadoConPublicacionRecomendada);
    doAnswer(invocation -> {
      List<PublicacionAdopcion> publicaciones = invocation.getArgument(0);
      assertEquals(1, publicaciones.size());
      return null;
    }).when(interesadoConPublicacionRecomendada).notificarPublicacionesDeInteres(anyList());

    when(repositorioAdopcion.getAdopcionesRecomendadas(publicacionInteres2)).thenReturn(Collections.emptyList());
    when(publicacionInteres2.getInteresado()).thenReturn(interesadoSinPublicacionRecomendada);
    doAnswer(invocation -> {
      List<PublicacionAdopcion> publicaciones = invocation.getArgument(0);
      assertEquals(0, publicaciones.size());
      return null;
    }).when(interesadoSinPublicacionRecomendada).notificarPublicacionesDeInteres(anyList());

    repositorioInteresAdopcion.enviarSugerenciasAdopcion(repositorioAdopcion);
  }
}




