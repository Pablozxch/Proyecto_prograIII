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
    private SimpleStringProperty facTotal;
    private ObjectProperty<Date> fecha;
    private SimpleStringProperty desc;
    private SimpleObjectProperty<EmpleadoDto> emp;
    private SimpleObjectProperty<MesaDto> mesa;
    private Boolean modificado;

    public OrdenDto()
    {
        this.modificado = false;
        this.id = new SimpleStringProperty();
        this.facTotal = new SimpleStringProperty();
        this.fecha = new SimpleObjectProperty();
        this.id = new SimpleStringProperty();
        this.emp = new SimpleObjectProperty();
        this.mesa = new SimpleObjectProperty();

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

    public Long getFacTotal()
    {
        if(facTotal.get() != null && !facTotal.get().isEmpty())
        {
            return Long.valueOf(facTotal.get());
        }
        else
        {
            return null;
        }
    }

    public void setFacTotal(Long facTotal)
    {
        this.facTotal.set(facTotal.toString());
    }

    public Date getFecha()
    {
        return fecha.getValue();
    }

    public void setFecha(Date fecha)
    {
        this.fecha.setValue(fecha);
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

    public EmpleadoDto getEmp()
    {
        return emp.get();
    }

    public void setEmp(EmpleadoDto emp)
    {
        this.emp.set(emp);
    }

    public MesaDto getMesa()
    {
        return mesa.get();
    }

    public void setMesa(MesaDto mesa)
    {
        this.mesa.set(mesa);
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
        sb.append("OrdenesDto{id=").append(id);
        sb.append(", facTotal=").append(facTotal);
        sb.append(", fecha=").append(fecha);
        sb.append(", desc=").append(desc);
        sb.append(", emp=").append(emp);
        sb.append(", mesa=").append(mesa);
        sb.append(", modificado=").append(modificado);
        sb.append('}');
        return sb.toString();
    }

}
