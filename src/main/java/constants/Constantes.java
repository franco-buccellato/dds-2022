package constants;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Constantes {
  private static final LocalDateTime FECHA_SISTEMA = LocalDateTime.of(2021, 5, 5, 0, 0);
  private static Constantes constantes;

  private Constantes(){}

  public static Constantes getConstates(){
    if(constantes == null)
      constantes = new Constantes();
    return constantes;
  }

  public LocalDateTime getFechaSistema() {
    return this.FECHA_SISTEMA;
  }
}
