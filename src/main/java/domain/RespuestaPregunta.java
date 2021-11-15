package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.exception.SeleccionInvalidaException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class RespuestaPregunta {
  private Long id;

  private Pregunta pregunta;

  private List<Opcion> selecciones;

  public RespuestaPregunta() {
  }

  public RespuestaPregunta(Pregunta pregunta, List<Opcion> selecciones) {
    this.pregunta = Objects.requireNonNull(pregunta, NOT_NULO.mensaje("pregunta"));
    this.selecciones = Objects.requireNonNull(selecciones, NOT_NULO.mensaje("selecciones"));
    this.chequearSeleccionesValidas(pregunta, selecciones);
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Pregunta getPregunta() {
    return pregunta;
  }

  public List<Opcion> getSelecciones() {
    return selecciones;
  }

  public void setSelecciones(List<Opcion> selecciones) {
    this.chequearSeleccionesValidas(this.getPregunta(), selecciones);
    this.selecciones = selecciones;
  }

  public void addSeleccion(Opcion seleccion) {
    this.chequearSeleccionesValidas(this.getPregunta(), Collections.singletonList(seleccion));
    this.getSelecciones().add(seleccion);
  }

  public void chequearSeleccionesValidas(Pregunta pregunta, List<Opcion> selecciones) {
    boolean esValido = pregunta.sonSeleccionesValidas(selecciones);

    if (!esValido) {
      throw new SeleccionInvalidaException("La opcion seleccionada no está disponible");
    }
  }

  public boolean estaEnRespuestasPosibles(Pregunta pregunta) {
    return pregunta.incluyeAlgunaSeleccion(this.getSelecciones());
  }

  public Boolean esDeMismaPregunta(Pregunta seleccionada) {
    return this.getPregunta().esMismaPregunta(seleccionada);
  }

  public Boolean tieneMismasSelecciones(List<Opcion> selecciones) {
    Pregunta pregunta = this.getPregunta();

    return this.getSelecciones().stream().anyMatch(
        seleccionPropia -> selecciones.stream().anyMatch(
            seleccionAjena -> pregunta.sonMismasSelecciones(seleccionPropia, seleccionAjena)
        )
    );
  }

  public Boolean esMismaPreguntaSeleccionada(RespuestaPregunta respuestaPregunta) {
    return this.esDeMismaPregunta(respuestaPregunta.getPregunta())
           && this.tieneMismasSelecciones(respuestaPregunta.getSelecciones());
  }

  public Boolean esSeleccionDeTamanio() {
    List<String> tamanios = Arrays.asList("Chico", "Mediano", "Grande");

    return this.getSelecciones().stream().anyMatch(
        seleccion -> tamanios.stream().anyMatch(
            tamanio -> tamanio.equals(seleccion.getDescripcion())
        )
    );
  }
}
