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
public class MesaDto
{

    private SimpleStringProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty estado;
    private SimpleStringProperty posx;
    private SimpleStringProperty posy;
    private SimpleObjectProperty<SalonDto> salon;
    private Boolean modificado;

    public MesaDto()
    {
        this.modificado = false;
        this.id = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.estado = new SimpleStringProperty();
        this.posx = new SimpleStringProperty();
        this.posy = new SimpleStringProperty();
        this.salon = new SimpleObjectProperty();
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

    public String getEstado()
    {
        return estado.get();
    }

    public void setEstado(String estado)
    {
        this.estado.set(estado);
    }

    public Long getPosx()
    {
        if(posx.get() != null && !posx.get().isEmpty())
        {
            return Long.valueOf(posx.get());
        }
        else
        {
            return null;
        }
    }

    public void setPosx(Long posx)
    {
        this.posx.set(posx.toString());
    }

    public Long getPosy()
    {
        if(posy.get() != null && !posy.get().isEmpty())
        {
            return Long.valueOf(posy.get());
        }
        else
        {
            return null;
        }
    }

    public void setPosy(Long posy)
    {
        this.posy.set(posy.toString());
    }

    public SalonDto getSalon()
    {
        return salon.get();
    }

    public void setSalon(SalonDto salon)
    {
        this.salon.set(salon);
    }

    public Boolean getModificado()
    {
        return modificado;
    }

    public void setModificado(Boolean modificado)
    {
        this.modificado = modificado;
    }

}
