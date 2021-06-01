package domain;

public class Voluntario {

  Usuario usuario;
  Asociacion asociacion;

  void aprobarPublicacion(Publicacion publicacion) {
    /*
    publicacion.setAsociacion
        (RepositorioAsociaciones
            .getRrepositorioAsociaciones()
            .encontrarMasCercana(publicacion
                .rescate
                .getLugarEncuentro()));
    */
    publicacion.aprobar();
  }

  void rechazarPublicacion(Publicacion publicacion) {
    publicacion.rechazar();
  }

  Publicacion tomarUnaPublicacion() {
    return RepositorioPublicaciones.getRepositorioPublicaciones().getEnEspera().get(0);
  }

}
