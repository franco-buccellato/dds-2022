package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "publicaciones_interes_adopcion")
public class PublicacionInteresAdopcion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "publicacion_interes_adopcion_id")
  private Long id;

  @OneToOne
  @JoinColumn(name = "duenio_id")
  private Duenio interesado;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "publicacion_interes_adopcion_id")
  private List<RespuestaInteresAdopcion> selecciones;

  @Column(name = "activa")
  private Boolean estaActiva;

  public PublicacionInteresAdopcion() {
  }

  public PublicacionInteresAdopcion(Duenio interesado, List<RespuestaInteresAdopcion> selecciones) {
    this.interesado = Objects.requireNonNull(interesado, NOT_NULO.mensaje("interesado"));
    this.selecciones = Objects.requireNonNull(selecciones, NOT_NULO.mensaje("selecciones"));
    this.estaActiva = Boolean.TRUE;
//    TODO levantar preguntas asociacion
//    TODO Definir comportamiento boton y habilitar
//    this.enviarBotonDeBaja();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Duenio getInteresado() {
    return interesado;
  }

  public List<RespuestaInteresAdopcion> getSelecciones() {
    return selecciones;
  }

  public Boolean getEstaActiva() {
    return estaActiva;
  }

  public void enviarBotonDeBaja() {
    // TODO definir template
    this.getInteresado().contactoTitular().notificar(new Notificacion(null));
  }

  public void anularPublicacion() {
    this.estaActiva = false;
  }

  public Boolean cumpleConPublicacionAdopcion(PublicacionAdopcion publicacion) {
    return this.cumpleConPreferencias(publicacion.getMascota().getCaracteristicas())
           && this.cumpleConComodidades(publicacion.getSeleccionesAdopcion());
  }

  public Boolean cumpleConComodidades(List<RespuestaPublicacionAdopcion> comodidades) {
    List<RespuestaInteresAdopcion> preguntasComodidad = this.getSeleccionesSegunObjetivo(
        ObjetivoPregunta.PREGUNTA_ASOCIACION_COMODIDAD
    );

    return comodidades
        .stream()
        .allMatch(comodidad -> preguntasComodidad.stream()
            .anyMatch(pregunta -> pregunta.esMismaPreguntaSeleccionada(comodidad))
        );
  }

  public Boolean cumpleConPreferencias(List<RespuestaCaracteristicaMascota> preferenciasMascota) {
    return this.getSeleccionesSegunObjetivo(ObjetivoPregunta.PREGUNTA_ASOCIACION_PREFERENCIAS)
        .stream()
        .allMatch(preferenciaPropia -> preferenciasMascota.stream().anyMatch(
            preferenciaMascota -> preferenciaMascota.esMismaPreguntaSeleccionada(preferenciaPropia))
        );
  }

  private List<RespuestaInteresAdopcion> getSeleccionesSegunObjetivo(ObjetivoPregunta alcance) {
    return this.getSelecciones()
        .stream()
        .filter(seleccion -> seleccion.tieneMismoObjetivo(alcance))
        .collect(Collectors.toList());
  }
}
