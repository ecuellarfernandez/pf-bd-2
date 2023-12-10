package org.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Crimen {
    private String id;
    private String descripcion;
    private Date fecha;
    private Time hora;
    private String idCategoria;


    @Override
    public String toString() {
        return "{"+"id:"+id+", descripcion:"+descripcion+", fecha:"+fecha+", hora:"+hora+", idCategoria:"+idCategoria+"}";
    }
}

