package servicio;

import com.sun.jersey.api.client.ClientResponse;
import domain.HogarTransito;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import servicio.exception.LimiteOffsetException;

public class HogarTransitoServicio implements BuscadorHogar {

  @Override
  public List<HogarTransito> buscarHogares(int offset)
      throws NoSuchAlgorithmException, KeyManagementException {
    RefugioDdsApi refugioApi = new RefugioDdsApi();
    ClientResponse responseRefugios = refugioApi.getHogares(offset);
    if (responseRefugios.getStatus() == 400) {
      throw new LimiteOffsetException(
          "El campo offset es obligatorio y debe ser mayor o igual a 1"
              + " o excede el limite de paginas");
    }
    HogarTransito hogar = responseRefugios.getEntity(HogarTransito.class);
    
    return hogar.getHogares();
  }
}
