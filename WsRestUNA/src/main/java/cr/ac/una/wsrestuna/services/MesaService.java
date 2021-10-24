/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.services;

import java.util.logging.*;
import javax.ejb.*;
import javax.persistence.*;

/**
 *
 * @author jp015
 */
@LocalBean
@Stateless
public class MesaService
{

    private static final Logger LOG = Logger.getLogger(RestauranteService.class.getName());

    @PersistenceContext(unitName = "WsRestUNA")
    private EntityManager em;
    
}
