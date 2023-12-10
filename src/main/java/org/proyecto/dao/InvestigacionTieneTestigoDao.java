package org.proyecto.dao;

import org.proyecto.dto.InvestigacionTieneTestigo;

import java.util.ArrayList;

public abstract class InvestigacionTieneTestigoDao {
    public abstract void insert(InvestigacionTieneTestigo obj) throws Exception;
    public abstract void update(InvestigacionTieneTestigo obj) throws Exception;
    public abstract void delete(String id) throws Exception;
    public abstract ArrayList<InvestigacionTieneTestigo> getList() throws Exception;
    public abstract org.proyecto.dto.InvestigacionTieneTestigo get(String id) throws Exception;
}
