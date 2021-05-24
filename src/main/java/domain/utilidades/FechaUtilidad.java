package domain.utilidades;

import java.time.LocalDate;

public class FechaUtilidad {
    private LocalDate FECHA_SISTEMA = LocalDate.of(2021, 5, 5);
    private static FechaUtilidad fechasDeUtilidad;

    private FechaUtilidad(){}

    public static FechaUtilidad getConstates(){
        if(fechasDeUtilidad == null)
            fechasDeUtilidad = new FechaUtilidad();
        return fechasDeUtilidad;
    }

    public LocalDate getFechaSistema() {
        return this.FECHA_SISTEMA;
    }

    public void setFechaSistema(LocalDate fecha){
        this.FECHA_SISTEMA = fecha;
    }
}
