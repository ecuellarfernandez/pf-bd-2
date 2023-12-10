package org.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departamento {
    private String id;
    private String nombre;
    private String descripcion;
    private String idAdministador;

    @Override
    public String toString() {
        return "{"+"id="+id+", nombre:"+nombre+", descripcion:"+descripcion+", idAdministador:"+idAdministador+"}";
    }
}
