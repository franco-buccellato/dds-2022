package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("C")
public class CaracteristicaChoice extends Caracteristica {
  @OneToMany
  @JoinColumn(name = "caracteristica_id")
  private List<Opcion> opciones;

  // Aplica para tipos CHECKBOX, BULLET, BOOLEAN donde los constraints se resuelven en otra capa
  public CaracteristicaChoice(
      TipoCaracteristica tipoCaracteristica,
      String descripcion,
      List<Opcion> opciones,
      Set<AlcanceCaracteristica> alcanceCaracteristica
  ) {
    super(tipoCaracteristica, descripcion, alcanceCaracteristica);
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
  public List<String> getOpcionesSeleccionas() {
    return opciones
              .stream()
              .filter(Opcion::getSeleccionada)
              .map(Opcion::getDescripcion)
              .collect(Collectors.toList());
  }

  @Override
  public Boolean tienenMismasOpciones(Caracteristica caracteristica) {
    return !this.getOpcionesSeleccionas().isEmpty()
            && caracteristica.getOpcionesSeleccionas().containsAll(this.getOpcionesSeleccionas());
  }

  @Override
  public String toString() {
    return this.descripcion + " " + this.opciones.stream()
        .filter(opcion -> opcion.getSeleccionada())
        .map(opcion -> opcion.getDescripcion() + "\n")
        .reduce(String::concat);
  }
}
