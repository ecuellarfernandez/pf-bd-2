package org.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDeBusqueda {
    private int id;
    private int estado;
    private int idAgente;
    private int idSospechoso;
    private int idInvestigacion;


    @Override
    public String toString() {
        return "{"+"id:"+id+", estado:"+estado+", idAgente:"+idAgente+", idSospechoso:"+idSospechoso+", idInvestigacion:"+idInvestigacion+"}";
    }
}
