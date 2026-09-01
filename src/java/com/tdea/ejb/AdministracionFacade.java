/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tdea.ejb;

import com.tdea.Administracion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ESTUDIANTE
 */
@Stateless
public class AdministracionFacade extends AbstractFacade<Administracion> {

    @PersistenceContext(unitName = "ppiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministracionFacade() {
        super(Administracion.class);
    }
    
}
