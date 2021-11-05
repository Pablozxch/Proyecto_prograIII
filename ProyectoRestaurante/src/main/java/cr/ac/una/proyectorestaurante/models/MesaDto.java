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
    public SimpleStringProperty nombre;
    private SimpleStringProperty estado;
    private SimpleStringProperty posX;
    private SimpleStringProperty posY;
    private SalonDto salonDto;
    private Boolean modificado;

    public MesaDto()
    {
        this.id = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.estado = new SimpleStringProperty();
        this.posX = new SimpleStringProperty();
        this.posY = new SimpleStringProperty();
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

    public String getEstado()
    {
        return estado.get();
    }

    public void setEstado(String estado)
    {
        this.estado.set(estado);
    }

    public Long getPosX()
    {
        if(posX.get() != null && !posX.get().isEmpty())
        {
            return Long.valueOf(posX.get());
        }
        else
        {
            return null;
        }
    }

    public void setPosX(Long posX)
    {
        this.posX.set(posX.toString());
    }

    public Long getPosY()
    {
        if(posY.get() != null && !posY.get().isEmpty())
        {
            return Long.valueOf(posY.get());
        }
        else
        {
            return null;
        }
    }

    public void setPosY(Long posY)
    {
        this.posY.set(posY.toString());
    }

    public SalonDto getSalonDto()
    {
        return salonDto;
    }

    public void setSalonDto(SalonDto salonDto)
    {
        this.salonDto = salonDto;
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
        sb.append("MesaDto{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", estado=").append(estado);
        sb.append(", posX=").append(posX);
        sb.append(", posY=").append(posY);
        sb.append(", salonDto=").append(salonDto.toString());
        sb.append('}');
        return sb.toString();
    }

}
