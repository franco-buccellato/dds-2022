package rescatista;

import java.time.LocalDate;
import java.util.Objects;
import constants.TipoIdentificacion;
import domain.mascotaEncontrada.MascotaEncontrada;
import domain.usuario.Contacto;

import static constants.Mensajes.NOT_NULO;

public class Rescatista {
    private String nombre;
    private String apellido;
    private TipoIdentificacion tipoIdentificacion;
    private String numeroIdentificacion;
    private LocalDate fechaNacimiento;
    private MascotaEncontrada mascotaEncontrada;
    private Contacto contacto;

    public Rescatista(String nombre, String apellido, TipoIdentificacion tipoIdentificacion, String numeroIdentificacion,
                      LocalDate fechaNacimiento, MascotaEncontrada mascotaEncontrada, Contacto contacto){
        this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
        this.apellido = Objects.requireNonNull(apellido, NOT_NULO.mensaje("apellido"));
        this.tipoIdentificacion =  Objects.requireNonNull(tipoIdentificacion, NOT_NULO.mensaje("tipoIdentificacion"));
        this.numeroIdentificacion = Objects.requireNonNull(numeroIdentificacion, NOT_NULO.mensaje("numeroIdentificacion"));
        this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento, NOT_NULO.mensaje("fechaNacimiento"));
        this.mascotaEncontrada = Objects.requireNonNull(mascotaEncontrada, NOT_NULO.mensaje("mascotaEncontrada"));
        this.contacto = Objects.requireNonNull(contacto, NOT_NULO.mensaje("contacto"));
    }

    /*Getters & Setters*/
    String getNombre() { return nombre; }
    String getApellido() { return apellido; }
    TipoIdentificacion getTipoIdentificacion() { return tipoIdentificacion; }
    String getNumeroIdentificacion() { return numeroIdentificacion; }
    LocalDate getFechaNacimiento() { return fechaNacimiento; }
    MascotaEncontrada getMascotaEncontrada() { return mascotaEncontrada; }
    Contacto getContacto() { return contacto; }

}
