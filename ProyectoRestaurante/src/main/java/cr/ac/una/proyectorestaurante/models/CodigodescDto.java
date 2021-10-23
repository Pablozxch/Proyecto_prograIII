/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.models;

import javafx.beans.property.*;

/**
 *
 * @author jp015
 */
public class CodigodescDto
{
    
    private SimpleStringProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty cantidad;
    private SimpleStringProperty cantidaddesc;
    private Boolean modificado;
    private SimpleStringProperty url;
    private ObjectProperty<RestauranteDto> resid;
    
    public CodigodescDto()
    {
        this.modificado = false;
        
    }
    
    public Long getId()
    {
        if(id.get() != null && !id.get().isEmpty())
        {
            return Long.valueOf(id.get());
        }
        else
        {
            return null;
        }
    }
    
    public void setId(Long id)
    {
        this.id.set(id.toString());
    }
    
    public String getNombre()
    {
        return nombre.get();
    }
    
    public void setNombre(String nombre)
    {
        this.nombre.set(nombre);
    }
    
    public Long getCantidad()
    {
        if(cantidad.get() != null && !cantidad.get().isEmpty())
        {
            return Long.valueOf(cantidad.get());
        }
        else
        {
            return null;
        }
    }
    
    public void setCantidad(Long cantidad)
    {
        this.cantidad.set(cantidad.toString());
    }
    
    public Boolean getModificado()
    {
        return modificado;
    }
    
    public void setModificado(Boolean modificado)
    {
        this.modificado = modificado;
    }
    
    public String getUrl()
    {
        return url.get();
    }
    
    public void setUrl(String url)
    {
        this.url.set(url);
    }
    
    public RestauranteDto getResid()
    {
        return resid.get();
    }
    
    public void setResid(RestauranteDto resid)
    {
        this.resid.set(resid);
    }
    
    public Long getCantidaddesc()
    {
        if(cantidaddesc.get() != null && !cantidaddesc.get().isEmpty())
        {
            return Long.valueOf(cantidaddesc.get());
        }
        else
        {
            return null;
        }
    }
    
    public void setCantidaddesc(Long cantidaddesc)
    {
        this.cantidaddesc.set(cantidaddesc.toString());
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CodigosdescDto{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", cantidaddesc=").append(cantidaddesc);
        sb.append(", url=").append(url);
        sb.append(", resid=").append(resid);
        sb.append('}');
        return sb.toString();
    }
    
}
