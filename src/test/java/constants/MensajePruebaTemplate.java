package constants;

import domain.TemplateNotificacion;

public class MensajePruebaTemplate extends TemplateNotificacion<String> {
  private String mensaje;

  public MensajePruebaTemplate(String mensaje) {
    super("template_test");
    this.mensaje = mensaje;
  }

  @Override
  protected String generarContexto() {
    return mensaje;
  }


}
