/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.models;

import java.util.*;

/**
 *
 * @author jp015
 */
public class OrdenDto
{

    private Long id;
    private Date fecha;
    private String estado;
    private EmpleadoDto empleadoDto;
    private MesaDto mesaDto;
    private Boolean modificado;

    //deberia de tener la lista de productos que se van a comprar 
    public OrdenDto()
    {
        this.modificado = false;
    }

    public OrdenDto(Orden orden)
    {
        this();
        this.id = orden.getOrdId();
        this.fecha = orden.getOrdFecha();
        this.estado = orden.getOrdEstado();
        this.empleadoDto = new EmpleadoDto(orden.getEmpId());
        this.mesaDto = new MesaDto(orden.getMesaId());

    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
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

  
}
