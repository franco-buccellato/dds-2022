package domain.MascotaEncontrada;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import constants.Constantes;
import domain.MascotaEncontrada.*;

public class RepositorioMascotaEncontrada {
  private List<MascotaEncontrada> mascotasEncontradas;
  private static RepositorioMascotaEncontrada repositorioMascotaEncontrada;

  private RepositorioMascotaEncontrada(){}

  public static RepositorioMascotaEncontrada getRepositorio(){
    if(repositorioMascotaEncontrada  == null)
      repositorioMascotaEncontrada = new RepositorioMascotaEncontrada();
    return repositorioMascotaEncontrada;
  }

  private List<MascotaEncontrada> mascotasEncontradasEntre(LocalDateTime fechaInicio, LocalDateTime fechaFin){
    return this.mascotasEncontradas.stream().filter(mascotaEncontrada -> mascotaEncontrada.encontradaEntre (fechaInicio, fechaFin)).collect(Collectors.toList());
  }

  public List<MascotaEncontrada> ultimasEncontradasEn10Dias(){
    return this.mascotasEncontradasEntre(Constantes.getConstates().getFechaSistema().minusDays(10), Constantes.getConstates().getFechaSistema());
  }

  public void setMascotasEncontradas(List<MascotaEncontrada> mascotasEncontradas){
    this.mascotasEncontradas = mascotasEncontradas;
  }
}
