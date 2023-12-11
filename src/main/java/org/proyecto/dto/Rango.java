package org.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Rango {
    private int id;
    private String nombre;

    @Override
    public String toString() {
        return "Rango{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
