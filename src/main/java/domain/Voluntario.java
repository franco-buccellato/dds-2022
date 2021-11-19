package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "voluntarios")
public class Voluntario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "voluntario_id")
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
