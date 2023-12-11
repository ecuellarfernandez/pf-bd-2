package org.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.proyecto.dao.SospechosoDao;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sospechoso {
    private int id;
    private int ciSospechoso;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;


    @Override
    public String toString() {
        return "Sospechoso{" +
                "id=" + id +
                ", ciSospechoso=" + ciSospechoso +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                '}';
    }
}
