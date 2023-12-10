package org.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestigacionTieneTestigo {
    private String id;
    private String idInvestigacion;
    private String idTestigo;
    private String declaracion;
    private Date fecha;

    @Override
    public String toString() {
        return "{" +
            " id=" + getId() +
            ", idInvestigacion='" + getIdInvestigacion() + "'" +
            ", idTestigo='" + getIdTestigo() + "'" +
            ", declaracion='" + getDeclaracion() + "'" +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
}
