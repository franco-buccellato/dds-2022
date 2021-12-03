package domain.repositorios;

import com.google.zxing.NotFoundException;
import domain.Duenio;
import domain.Mascota;
import domain.exception.NoSePudoConvertirQRAJson;
import domain.exception.NoSePuedeObtenerCodigoQR;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import utilidades.QRCodeGenerator;

import javax.persistence.NoResultException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class RepositorioMascotas implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
  private static RepositorioMascotas INSTANCE = new RepositorioMascotas();

  public static RepositorioMascotas getInstace() {
    return INSTANCE;
  }

  public Mascota getById(Long id) {
    return entityManager().find(Mascota.class, id);
  }

  public Mascota getByQR(BufferedImage chapa) {
    try {
      return this.getById((Long) QRCodeGenerator.decodificarQRCode(chapa).get("id"));
    } catch (NotFoundException e) {
      throw new NoSePuedeObtenerCodigoQR(e.getMessage());
    } catch (IOException e) {
      throw new NoSePudoConvertirQRAJson(e.getMessage());
    }
  }

  public void guardarMascota(Mascota unaMascota) {
    withTransaction(() -> persist(unaMascota));
  }


  public List<Mascota> getByUsuario(Long userId) {
    try {
      return entityManager().createQuery(
              "SELECT DISTINCT m from Duenio d join d.mascotas m where d.usuario.id = :userId order by m.nombre",
              Mascota.class)
          .setParameter("userId", userId)
          .getResultList();
    } catch (NoResultException e) {
      return null;
    }
  }
}
