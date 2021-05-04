package MascotaEncontrada;

import domain.mascota.Mascota;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static constants.Mensajes.NOT_NULO;

public class MascotaEncontrada {

    public List<Image> fotos;
    public String descripcion;
    public String lugarEncuentro;
    public LocalDateTime fecha;

    public MascotaEncontrada(List<Image> fotos, String descripcion, String lugarEncuentro, LocalDateTime fecha){
        this.fotos = fotos;
        this.descripcion = Objects.requireNonNull(descripcion, NOT_NULO.mensaje("descripcion"));
        this.lugarEncuentro = Objects.requireNonNull(lugarEncuentro, NOT_NULO.mensaje("lugarEncuentro"));
        this.fecha = Objects.requireNonNull(fecha, NOT_NULO.mensaje("fecha"));
    }

    /*Getters & Setters*/
    public List<Image> getFotos(){ return fotos; }
    public void addFoto(Image foto){ this.fotos.add(foto); }
    public String getDescripcion(){ return this.descripcion; }
    public String getLugarEncuentro(){ return this.lugarEncuentro; }
    public LocalDateTime getFecha(){ return this.fecha; }

}
