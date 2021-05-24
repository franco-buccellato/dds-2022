package constants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

public class Constantes {
  private LocalDate FECHA_SISTEMA = LocalDate.of(2021, 5, 5);
  private static Constantes constantes;

  private Constantes(){}

  public static Constantes getConstates(){
    if(constantes == null)
      constantes = new Constantes();
    return constantes;
  }

  public LocalDate getFechaSistema() {
    return this.FECHA_SISTEMA;
  }

  public void setFechaSistema(LocalDate fecha){
    this.FECHA_SISTEMA = fecha;
  }
}
