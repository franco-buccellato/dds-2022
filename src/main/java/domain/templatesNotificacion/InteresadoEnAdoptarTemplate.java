package domain.templatesNotificacion;

import domain.Duenio;
import domain.TemplateNotificacion;

public class InteresadoEnAdoptarTemplate extends TemplateNotificacion<Duenio> {
  private Duenio adoptante;

  public InteresadoEnAdoptarTemplate(Duenio adoptante) {
    super("interesado_en_adoptar");
    this.adoptante = adoptante;
  }

  @Override
  protected Duenio generarContexto() {
    return this.adoptante;
  }
}
