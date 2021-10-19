/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.controllers;

import cr.ac.una.wsrestuna.utils.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author jp015
 */
@Secure
@Path("/ProductoOrdenController")
public class ProductoOrdenController
{
        @Path("/ping")
    public Response ping()
    {
        return Response
                  .ok("ping")
                  .build();
    }
}
