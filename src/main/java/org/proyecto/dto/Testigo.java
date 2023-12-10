package org.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Testigo {
    private String id;
    private String ciTestigo;
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
