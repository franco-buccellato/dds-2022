package domain.templatesNotificacion;

import domain.Rescate;
import domain.TemplateNotificacion;

public class RescatistaContactaDuenioTemplate extends TemplateNotificacion<Rescate> {
  Rescate rescate;

  public RescatistaContactaDuenioTemplate(Rescate rescate) {
    super("contacto_rescatista");
    this.rescate = rescate;
  }

  @Override
  protected Rescate generarContexto() {
    return rescate;
  }
}
