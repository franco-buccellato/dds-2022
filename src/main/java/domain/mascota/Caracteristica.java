package domain.mascota;

public interface Caracteristica {
  TipoCaracteristica getTipoCaracteristica();
  String getDescripcion();
  void addOpcion(Object opcion);
  Object getOpciones();
  Object getSeleccionada();
}
