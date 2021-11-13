//package servicio.batch;
//
//import domain.repositorios.RepositorioPublicacionesAdopcion;
//import domain.repositorios.RepositorioPublicacionesInteresAdopcion;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class TareaEnviarSugerencia implements Job {
//  private final Logger log = LoggerFactory.getLogger(TareaEnviarSugerencia.class);
//
//  public void execute(JobExecutionContext jobExecutionContext) {
//    log.info("Enviando sugerencias....");
//    RepositorioPublicacionesInteresAdopcion.getRepositorioPublicaciones();
////        .enviarSugerenciasAdopcion(RepositorioPublicacionesAdopcion.getRepositorioPublicaciones());
//    log.info("Sugerencias enviadas");
//  }
//}
//
