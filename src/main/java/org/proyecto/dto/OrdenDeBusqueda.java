package org.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDeBusqueda {
    private String id;
    private String estado;
    private String idAgente;
    private String idSospechoso;
    private String idInvestigacion;


    @Override
    public String toString() {
        return "{"+"id:"+id+", estado:"+estado+", idAgente:"+idAgente+", idSospechoso:"+idSospechoso+", idInvestigacion:"+idInvestigacion+"}";
    }
}
