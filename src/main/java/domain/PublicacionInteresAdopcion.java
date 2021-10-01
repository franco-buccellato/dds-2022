package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.exception.PreguntaObligatoriaNoContestadaException;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Entity(name = "publicaciones")
public class PublicacionInteresAdopcion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @OneToOne
  private Duenio interesado;
  @ManyToMany
  @JoinTable(
      name = "preguntas_interes_adopcion",
      joinColumns = @JoinColumn(name = "publicacion_interes_id"),
      inverseJoinColumns = @JoinColumn(name = "pregunta_id")
  )
  private List<Pregunta> preguntas;
  @Column(name = "activa")
  private Boolean estaActiva;

  private PublicacionInteresAdopcion() {}

  public PublicacionInteresAdopcion(Duenio interesado, List<Pregunta> preguntas) {
    if (estanCompletasLasPreguntas(preguntas).get()) {
      this.estaActiva = true;
    }
    this.interesado = Objects.requireNonNull(interesado, NOT_NULO.mensaje("interesado"));
    this.preguntas = Objects.requireNonNull(preguntas, NOT_NULO.mensaje("comodidadesMascota"));
  }

  public int getId() {
    return id;
  }

  public Duenio getInteresado() {
    return interesado;
  }

  public List<Pregunta> getComodidadesMascota() {
    return preguntas;
  }

  public Boolean getEstaActiva() {
    return estaActiva;
  }

  private AtomicBoolean estanCompletasLasPreguntas(List<Pregunta> preguntas) throws PreguntaObligatoriaNoContestadaException {
    AtomicBoolean estanCompletas = new AtomicBoolean(true);
    preguntas
        .stream()
        .forEach(pregunta -> {
          if (pregunta.getOpcionesSeleccionas().isEmpty()) {
            estanCompletas.set(false);
            throw new PreguntaObligatoriaNoContestadaException("Falta contestar la pregunta " + pregunta.getDescripcion());
          }
        });
    return estanCompletas;
  }

  public void enviarBotonDeBaja() {
    this.interesado.contactoTitular().notificar(new Notificacion(null));
  }

  public void anularPublicacion() {
    this.estaActiva = false;
  }

  public Boolean cumpleConPublicacionAdopcion(PublicacionAdopcion publicacion) {
    return cumpleConPreferencias(publicacion.getMascota().getCaracteristicasSeleccionadas())
        && cumpleConComodidades(publicacion.getPreguntas());
  }

  public Boolean cumpleConComodidades(List<Pregunta> comodidades) {
    return getPreguntasSegun(AlcancePregunta.PREGUNTA_COMODIDAD)
        .stream()
        .allMatch(preguntaCumplir ->
            comodidades
                .stream()
                .anyMatch(preguntaCumplir::tienenMismasOpciones)
        );
  }

  public Boolean cumpleConPreferencias(List<Caracteristica> caracteristicas) {
     return getPreguntasSegun(AlcancePregunta.PREGUNTA_PREFERENCIA)
        .stream()
        .allMatch(
            pregunta -> caracteristicas.stream().anyMatch(caracteristica -> caracteristica.tienenMismasOpciones(pregunta))
        );
  }

  private List<Pregunta> getPreguntasSegun(AlcancePregunta alcance) {
    return preguntas
        .stream()
        .filter(pregunta -> pregunta.getAlcancePregunta().equals(alcance))
        .collect(Collectors.toList());
  }
}
