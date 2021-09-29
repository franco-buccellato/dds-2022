package domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static domain.exception.Mensajes.NOT_NULO;

public class Pregunta {
  private String descripcion;
  private List<Opcion> opciones;
  private AlcancePregunta alcancePregunta;

  private Pregunta() {}

  public Pregunta(String descripcion, List<Opcion> opciones, AlcancePregunta alcancePregunta) {
    this.descripcion = Objects.requireNonNull(descripcion, NOT_NULO.mensaje("descripcion"));
    this.opciones = Objects.requireNonNull(opciones, NOT_NULO.mensaje("opciones"));
    this.alcancePregunta = Objects.requireNonNull(alcancePregunta, NOT_NULO.mensaje("alcancePregunta"));
  }

  public String getDescripcion() {
    return descripcion;
  }

  public List<Opcion> getOpciones() {
    return opciones;
  }

  public void seleccionarOpcion(Opcion opcion, Boolean estado) {
    opcion.setSeleccionada(estado);
  }

  public Boolean tienenMismasOpciones(Pregunta pregunta) {
    return !this.getOpcionesSeleccionas().isEmpty()
        && this.equals(pregunta)
        && pregunta.getOpcionesSeleccionas().containsAll(this.getOpcionesSeleccionas());
  }

  public AlcancePregunta getAlcancePregunta() {
    return alcancePregunta;
  }

  public List<Opcion> getOpcionesSeleccionas() {
    return opciones
        .stream()
        .filter(Opcion::getSeleccionada)
        .collect(Collectors.toList());
  }
}
