package servicio;

import com.sun.jersey.api.client.ClientResponse;
import domain.HogarTransito;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import servicio.exception.LimiteOffsetException;

public class HogarTransitoServicio implements BuscadorHogar {

  @Override
  public HogarTransito buscarHogares(int offset)
      throws NoSuchAlgorithmException, KeyManagementException {
    RefugioDdsApi refugioApi = new RefugioDdsApi();
    ClientResponse responseRefugios = refugioApi.getHogares(offset);
    if (responseRefugios.getStatus() == 400) {
      throw new LimiteOffsetException(
          "El campo offset es obligatorio y debe ser mayor o igual a 1"
              + " o excede el limite de paginas");
    }
    return responseRefugios.getEntity(HogarTransito.class);
  }

  public List<HogarTransito> hogaresDisponibles() throws KeyManagementException, NoSuchAlgorithmException {
    int offset = 1;
    int total;
    List<HogarTransito> listaTemporal = Arrays.asList();
    do {
      HogarTransito response = buscarHogares(offset);
      total = response.getTotal();
      listaTemporal = Stream.concat(listaTemporal.stream(), response.getHogares().stream()).collect(Collectors.toList());
      offset += 1;
    } while ((offset * 10) <= total);
    return listaTemporal;
  }
}
// TODO exception management
// TODO mensaje