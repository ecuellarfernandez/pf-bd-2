package org.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Investigacion {
    private String id;
    private Date fechaInicio;
    private Date fechaFin;
    private String idAgente;
    private String idCrimen;

    @Override
    public String toString() {
        return "{"+"id:"+id+", fechaInicio:"+fechaInicio+", fechaFin:"+fechaFin+", idAgente:"+idAgente+", idCrimen:"+idCrimen+"}";
    }
}
