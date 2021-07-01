package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.repositorios.RepositorioAsociaciones;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Asociacion {
  private String nombre;
  private Ubicacion ubicacion;
  private List<Caracteristica> preguntasAdopcion;

  public Asociacion(String nombre, Ubicacion ubicacion) {
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.ubicacion = Objects.requireNonNull(ubicacion, NOT_NULO.mensaje("ubicacion"));
    this.preguntasAdopcion = Collections.emptyList();
    RepositorioAsociaciones.getRepositorioAsociaciones().addAsociacion(this);
  }

  public String getNombre() {
    return nombre;
  }

  public Ubicacion getUbicacion() {
    return ubicacion;
  }

  public List<Caracteristica> getPreguntasAdopcion() {
    return preguntasAdopcion;
  }

  public void setPreguntasAdopcion(List<Caracteristica> preguntasAdopcion) {
    this.preguntasAdopcion = preguntasAdopcion;
  }

  public void addPreguntasAdopcion(Caracteristica caracteristica) {
    this.preguntasAdopcion.add(caracteristica);
  }
}
