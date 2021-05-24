package domain.mascota;

public interface Caracteristica {
  TipoCaracteristica getTipoCaracteristica();
  void addOpcion(Object opcion);
  Object getOpciones();
}
