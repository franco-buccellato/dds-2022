package Rescatista;

import java.time.LocalDate;
import java.util.Objects;
import constants.TipoIdentificacion;
import static constants.Mensajes.NOT_NULO;

public class Rescatista {
    public String nombre;
    public String apellido;
    public TipoIdentificacion tipoIdentificacion;
    public String numeroIdentificacion;
    public LocalDate fechaNacimiento;

    public Rescatista(String nombre, String apellido, TipoIdentificacion tipoIdentificacion, String numeroIdentificacion, LocalDate fechaNacimiento){
        this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
        this.apellido = Objects.requireNonNull(apellido, NOT_NULO.mensaje("apellido"));
        this.tipoIdentificacion = Objects.requireNonNull(tipoIdentificacion, NOT_NULO.mensaje("tipoIdentificacion"));
        this.numeroIdentificacion = Objects.requireNonNull(numeroIdentificacion, NOT_NULO.mensaje("numeroIdentificacion"));
        this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento, NOT_NULO.mensaje("fechaNacimiento"));
    }

    /*Getters & Setters*/
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public TipoIdentificacion getTipoIdentificacion() { return tipoIdentificacion; }
    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

}
