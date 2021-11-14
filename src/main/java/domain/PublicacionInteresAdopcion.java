package domain;

import static domain.ObjetivoPregunta.PUBLICACION_INTERES_ADOPCION_COMODIDAD;
import static domain.ObjetivoPregunta.PUBLICACION_INTERES_ADOPCION_PREFERENCIA;
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
  private int id;

  @OneToOne
  private Duenio interesado;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "publicacion_interes_adopcion_id")
  private List<SeleccionInteresAdopcion> selecciones;

  @Column(name = "activa")
  private Boolean estaActiva;

  public PublicacionInteresAdopcion() {
  }

  public PublicacionInteresAdopcion(Duenio interesado, List<SeleccionInteresAdopcion> selecciones) {
    this.interesado = Objects.requireNonNull(interesado, NOT_NULO.mensaje("interesado"));
    this.selecciones = Objects.requireNonNull(selecciones, NOT_NULO.mensaje("selecciones"));
    this.estaActiva = Boolean.TRUE;
    this.enviarBotonDeBaja();
  }

  public int getId() {
    return id;
  }

  public Duenio getInteresado() {
    return interesado;
  }

  public List<SeleccionInteresAdopcion> getSelecciones() {
    return selecciones;
  }

  public Boolean getEstaActiva() {
    return estaActiva;
  }

  public void enviarBotonDeBaja() {
    this.interesado.contactoTitular().notificar(new Notificacion(null));
  }

  public void anularPublicacion() {
    this.estaActiva = false;
  }

  public Boolean cumpleConPublicacionAdopcion(PublicacionAdopcion publicacion) {
    return this.cumpleConPreferencias(publicacion.getMascota().getCaracteristicas())
           && this.cumpleConComodidades(publicacion.getSeleccionesAdopcion());
  }

  public Boolean cumpleConComodidades(List<SeleccionPublicacionAdopcion> comodidades) {
    List<SeleccionInteresAdopcion> preguntasComodidad = this.getPreguntasSegun(PUBLICACION_INTERES_ADOPCION_COMODIDAD);

    return comodidades
        .stream()
        .allMatch(comodidad -> preguntasComodidad.stream()
            .anyMatch(pregunta -> pregunta.esMismaPreguntaSeleccionada(comodidad))
        );
  }

  public Boolean cumpleConPreferencias(List<SeleccionCaracteristicaMascota> preferenciasMascota) {
    return this.getPreguntasSegun(PUBLICACION_INTERES_ADOPCION_PREFERENCIA)
        .stream()
        .allMatch(preferenciaPropia -> preferenciasMascota.stream().anyMatch(
            preferenciaMascota -> preferenciaMascota.esMismaPreguntaSeleccionada(preferenciaPropia))
        );
  }

  private List<SeleccionInteresAdopcion> getPreguntasSegun(ObjetivoPregunta objetivoPregunta) {
    return this.getSelecciones()
        .stream()
        .filter(seleccion -> seleccion.getPregunta().getObjetivos().equals(objetivoPregunta))
        .collect(Collectors.toList());
  }
}
