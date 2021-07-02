package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class Caracteristica {
  protected TipoCaracteristica tipoCaracteristica;
  protected String descripcion;
  protected List<Opcion> opciones;
  //protected Boolean obligatoria;
  protected Set<AlcanceCaracteristica> alcanceCaracteristica;

  public Caracteristica(TipoCaracteristica tipoCaracteristica,
      String descripcion, Set<AlcanceCaracteristica> alcanceCaracteristica
  ) {
    this.tipoCaracteristica = Objects.requireNonNull(
        tipoCaracteristica,
        NOT_NULO.mensaje("tipoCaracteristica")
    );
    this.descripcion = descripcion;
    this.opciones = new ArrayList<>();
    this.alcanceCaracteristica = Objects.requireNonNull(
            alcanceCaracteristica, NOT_NULO.mensaje("alcanceCaracteristica"
            ));
  }

  public TipoCaracteristica getTipoCaracteristica() {
    return tipoCaracteristica;
  }

  public String getDescripcion() {
    return descripcion;
  }

  /*
  public Boolean getObligatoria() {
    return obligatoria;
  }
*/

  public Set<AlcanceCaracteristica> getAlcanceCaracteristica() {
    return this.alcanceCaracteristica;
  }

  public abstract List<Opcion> getOpciones();

  public abstract List<String> getOpcionesSeleccionas();

  public abstract Boolean tienenMismasOpciones(Caracteristica caracteristica);
}
