package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Voluntarios")
public class Voluntario {
  @Id
  @Column(name = "voluntario_id")
  @GeneratedValue
  Long id;
  @OneToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;
  @ManyToOne
  @JoinColumn(name = "asociacion_id")
  private Asociacion asociacion;

  public Voluntario() {
  }

  public Voluntario(Usuario usuario, Asociacion asociacion) {
    this.usuario = usuario;
    this.asociacion = asociacion;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public Asociacion getAsociacion() {
    return asociacion;
  }

  public void aprobarPublicacion(PublicacionRescate publicacionRescate) {
    publicacionRescate.aprobar();
  }

  public void rechazarPublicacion(PublicacionRescate publicacionRescate) {
    publicacionRescate.rechazar();
  }

}
