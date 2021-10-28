/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.models;

import java.util.*;
import javafx.beans.property.*;

/**
 *
 * @author jp015
 */
public class OrdenDto
{

    private SimpleStringProperty id;
    private ObjectProperty<Date> fecha;
    private SimpleStringProperty estado;
    private EmpleadoDto empleadoDto;
    private MesaDto mesaDto;
    private Boolean modificado;

    public OrdenDto()
    {
        this.id = new SimpleStringProperty();
        this.fecha = new SimpleObjectProperty<>();
        this.estado = new SimpleStringProperty();
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

    public Date getFecha()
    {
        return fecha.getValue();
    }

    public void setFecha(Date fecha)
    {
        this.fecha.setValue(fecha);
    }

    public String getEstado()
    {
        return estado.get();
    }

    public void setEstado(String estado)
    {
        this.estado.set(estado);
    }

    public EmpleadoDto getEmpleadoDto()
    {
        return empleadoDto;
    }

    public void setEmpleadoDto(EmpleadoDto empleadoDto)
    {
        this.empleadoDto = empleadoDto;
    }

    public MesaDto getMesaDto()
    {
        return mesaDto;
    }

    public void setMesaDto(MesaDto mesaDto)
    {
        this.mesaDto = mesaDto;
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
        sb.append("OrdenDto{id=").append(id);
        sb.append(", fecha=").append(fecha);
        sb.append(", estado=").append(estado);
        sb.append(", empleadoDto=").append(empleadoDto.toString());
        sb.append(", mesaDto=").append(mesaDto.toString());
        sb.append(", modificado=").append(modificado);
        sb.append('}');
        return sb.toString();
    }

}
