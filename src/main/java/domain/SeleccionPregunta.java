package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.exception.SeleccionInvalidaExcepction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class SeleccionPregunta {
  private Long id;

  private Pregunta pregunta;

  private List<Opcion> selecciones;

  public SeleccionPregunta() {
  }

  public SeleccionPregunta(Pregunta pregunta, List<Opcion> selecciones) {
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
      throw new SeleccionInvalidaExcepction("La opcion seleccionada no está disponible");
    }
  }

  public Boolean esDeMismaPregunta(Pregunta seleccionada) {
    return this.getPregunta().esMismaPregunta(seleccionada);
  }

  public Boolean tieneMismasSelecciones(List<Opcion> selecciones) {
    return this.getSelecciones().stream().allMatch(
        seleccion -> selecciones.stream().anyMatch(
            seleccion1 -> seleccion1.esMismaOpcion(seleccion)
        )
    );
  }

  public Boolean esMismaPreguntaSeleccionada(SeleccionPregunta seleccionPregunta) {
    return this.esDeMismaPregunta(seleccionPregunta.getPregunta())
           && this.tieneMismasSelecciones(seleccionPregunta.getSelecciones());
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
