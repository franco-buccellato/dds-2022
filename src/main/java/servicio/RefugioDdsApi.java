package servicio;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import servicio.exception.ApiNoDisponibleException;

public class RefugioDdsApi {

  public ClientResponse getHogares(int offset) {
    ClientConfig config = new DefaultClientConfig();
    config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
    config.getProperties()
        .put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
            new HTTPSProperties(
                NoopHostnameVerifier.INSTANCE,
                getInsecureSslContext()));

    try {
      return Client.create(config)
          .resource("https://api.refugiosdds.com.ar/")
          .path("api/hogares")
          .queryParam("offset", Integer.toString(offset))
          .accept(MediaType.APPLICATION_JSON)
          .header(HttpHeaders.AUTHORIZATION,
              "Bearer " + "C7QpRnbRbX24ZRhg3sv3msoriMb5C6eYA9sinv1ET8GZ84FaqwTAWPgBKEps")
          .get(ClientResponse.class);
    } catch (ClientHandlerException ex) {
      throw new ApiNoDisponibleException("Api RefugioDDS no se encuentra disponible");
    }
  }

  protected static SSLContext getInsecureSslContext() {
    try {
      final TrustManager[] trustAllCerts = new TrustManager[]{
          new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
              return null;
            }

            public void checkClientTrusted(
                final java.security.cert.X509Certificate[] arg0, final String arg1) {
            }

            public void checkServerTrusted(
                final java.security.cert.X509Certificate[] arg0, final String arg1) {
            }
          }
      };

      final SSLContext sslcontext = SSLContext.getInstance("SSL");
      sslcontext.init(null, trustAllCerts,
          new java.security.SecureRandom());
      return sslcontext;
    } catch (KeyManagementException ex) {
      throw new ApiNoDisponibleException("Api RefugioDDS no se encuentra disponible");
    } catch (NoSuchAlgorithmException ex) {
      throw new ApiNoDisponibleException("Api RefugioDDS no se encuentra disponible");
    }
  }
}