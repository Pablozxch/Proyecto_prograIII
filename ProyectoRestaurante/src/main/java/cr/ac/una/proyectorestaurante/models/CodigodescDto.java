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

    public SimpleStringProperty id;
    public SimpleStringProperty nombre;
    public SimpleStringProperty desc;
    public SimpleStringProperty url;
    public SimpleStringProperty cantidadusar;//cantidad dispon para su suso
    private RestauranteDto restaurante;
    private Boolean modificado;

    public CodigodescDto()
    {
        this.id = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.desc = new SimpleStringProperty();
        this.url = new SimpleStringProperty();
        this.cantidadusar = new SimpleStringProperty();
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

    public Long getDesc()
    {
        if(desc.get() != null && !desc.get().isEmpty())
        {
            return Long.valueOf(desc.get());
        }
        else
        {
            return null;
        }
    }

    public void setDesc(Long desc)
    {
        this.desc.set(desc.toString());
    }

    public String getUrl()
    {
        return url.get();
    }

    public void setUrl(String url)
    {
        this.url.set(url);
    }

    public Long getCantidadusar()
    {
        if(cantidadusar.get() != null && !cantidadusar.get().isEmpty())
        {
            return Long.valueOf(cantidadusar.get());
        }
        else
        {
            return null;
        }
    }

    public void setCantidadusar(Long cantidadusar)
    {
        this.cantidadusar.set(cantidadusar.toString());
    }

    public RestauranteDto getRestaurante()
    {
        return restaurante;
    }

    public void setRestaurante(RestauranteDto restaurante)
    {
        this.restaurante = restaurante;
    }

    public Boolean getModificado()
    {
        return modificado;
    }

    public void setModificado(Boolean modificado)
    {
        this.modificado = modificado;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CodigodescDto{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", desc=").append(desc);
        sb.append(", url=").append(url);
        sb.append(", cantidadusar=").append(cantidadusar);
        sb.append(", restaurante=").append(restaurante.toString());
        sb.append('}');
        return sb.toString();
    }

}
