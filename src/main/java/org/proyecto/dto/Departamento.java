package org.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departamento {
    private int id;
    private String nombre;
    private String descripcion;
    private int idAdministador;

    @Override
    public String toString() {
        return "{"+"id="+id+", nombre:"+nombre+", descripcion:"+descripcion+", idAdministador:"+idAdministador+"}";
    }
}
