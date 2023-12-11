package org.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Testigo {
    private int id;
    private int ciTestigo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    @Override
    public String toString() {
        return "Testigo{" +
                "id=" + id +
                ", ciTestigo=" + ciTestigo +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                '}';
    }
}
