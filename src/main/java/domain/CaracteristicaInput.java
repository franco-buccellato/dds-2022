package domain;

import javax.persistence.*;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.*;

@Entity
@DiscriminatorValue("C")
public class CaracteristicaInput extends Caracteristica {
  @OneToOne
  private Opcion input;

  // Aplica para tipos TEXTO y NUMERO donde los constraints van a ser resueltos en otra capa
  public CaracteristicaInput(
      TipoCaracteristica tipoCaracteristica,
      String descripcion,
      Opcion input,
      Set<AlcanceCaracteristica> alcanceCaracteristica
  ) {
    super(tipoCaracteristica, descripcion, alcanceCaracteristica);
    this.input = Objects.requireNonNull(input, NOT_NULO.mensaje("input"));
  }

  public void setInput(Opcion input) {
    this.input = input;
  }

  @Override
  public List<Opcion> getOpciones() {
    return Arrays.asList(input);
  }

  @Override
  public List<String> getOpcionesSeleccionas() {
    if (this.input.getDescripcion() != "") {
      return Arrays.asList(input.getDescripcion());
    }
    return Collections.emptyList();
  }

  @Override
  public Boolean tienenMismasOpciones(Caracteristica caracteristica) {
    return null;
  }

  @Override
  public String toString() {
    return this.descripcion + ": " + this.input.getDescripcion();
  }
}
