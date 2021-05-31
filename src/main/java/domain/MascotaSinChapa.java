package domain;

import java.util.List;

public class MascotaSinChapa extends Mascota {
  public MascotaSinChapa(TipoMascota tipoMascota, String nombre, String apodo, Double edadAproximada, Sexo sexo, String descripcionFisica, List<String> fotos, List<Caracteristica> caracteristicas, SituacionMascota situacionMascota) {
    super(
        tipoMascota,
        nombre,
        apodo,
        edadAproximada,
        sexo,
        descripcionFisica,
        fotos,
        caracteristicas,
        situacionMascota
    );
  }
}
