package domain;

public class CaracteristicaInput extends Caracteristica{
  private String texto;

  // Aplica para tipos TEXTO y NUMERO donde los constraints van a ser resueltos en otra capa
  public CaracteristicaInput(TipoCaracteristica tipoCaracteristica, String descripcion, Boolean obligatoria) {
    super(tipoCaracteristica, descripcion, obligatoria);
    this.texto = "";
  }
  @Override
  public String getOpciones() {
    return texto;
  }

  public void addOpcion(String texto) { this.texto = texto; }
}
