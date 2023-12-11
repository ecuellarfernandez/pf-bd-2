package org.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Agente {
    private int ci;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int idDepartamento;
    private int idRango;
    private int idEquipo;


    @Override
    public String toString() {
        return "{" +
                "id=" + ci  +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", idDepartamento=" + idDepartamento +
                ", idRango=" + idRango +
                ", idEquipo=" + idEquipo +
                '}';
    }
}
