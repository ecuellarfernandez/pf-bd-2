package org.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {
    private int id;
    private String nombre;

    @Override
    public String toString() {
        return "{"+"id="+id+", nombre:"+nombre+"}";
    }
}
