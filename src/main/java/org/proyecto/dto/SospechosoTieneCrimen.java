package org.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SospechosoTieneCrimen{
    private String id;
    private String idCrimen;
    private String idSospechoso;


    @Override
    public String toString() {
        return "SospechosoTieneCrimen{" +
                "id=" + id +
                ", idCrimen=" + idCrimen +
                ", idSospechoso=" + idSospechoso +
                '}';
    }
}
