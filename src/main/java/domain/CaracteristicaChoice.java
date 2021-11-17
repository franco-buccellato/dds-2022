package domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("C")
public class CaracteristicaChoice extends Caracteristica {
  @OneToMany//(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "caracteristica_id")
  private List<Opcion> opciones;

  // Aplica para tipos CHECKBOX, BULLET, BOOLEAN donde los constraints se resuelven en otra capa
  public CaracteristicaChoice(
      TipoCaracteristica tipoCaracteristica,
      String descripcion,
      List<Opcion> opciones
  ) {
    super(tipoCaracteristica, descripcion);
    this.opciones = opciones;
  }

  public void seleccionarOpcion(Opcion opcion, Boolean estado) {
    opcion.setSeleccionada(estado);
  }

  @Override
  public List<Opcion> getOpciones() {
    return opciones;
  }

  @Override
  public List<Opcion> getOpcionesSeleccionas() {
    return opciones
        .stream()
        .filter(Opcion::getSeleccionada)
        .collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return this.descripcion + " " + this.opciones.stream()
        .filter(Opcion::getSeleccionada)
        .map(opcion -> opcion.getDescripcion() + "\n")
        .reduce(String::concat);
  }
}
