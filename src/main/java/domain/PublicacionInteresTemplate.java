package domain;

import java.util.List;

public class PublicacionInteresTemplate extends TemplateNotificacion<String> {
  private final String contenido;

  public PublicacionInteresTemplate(String template, String contenido) {
    super(template);
    this.contenido = contenido;
  }

  @Override
  protected String generarContexto() {
    return contenido;
  }
}
