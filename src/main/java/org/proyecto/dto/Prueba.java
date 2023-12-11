package org.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Prueba {
    private int id;
    private String descripcion;
    private int idCrimen;

    @Override
    public String toString() {
        return "Prueba{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", idCrimen=" + idCrimen +
                '}';
    }
}
