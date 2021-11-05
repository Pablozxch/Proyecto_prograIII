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
    private List<DetallexordenDto> detallexordenDtos;

    //deberia de tener la lista de productos que se van a comprar 
    public OrdenDto()
    {
        this.modificado = false;
        this.detallexordenDtos = new ArrayList<>();
    }

    public OrdenDto(Orden orden)
    {
        this();
        this.id = orden.getOrdId();
        this.fecha = orden.getOrdFecha();
        this.estado = orden.getOrdEstado();
        this.empleadoDto = new EmpleadoDto(orden.getEmpId());
        this.mesaDto = new MesaDto(orden.getMesaId());
        List<DetallexordenDto> list = new ArrayList<>();
        orden.getDetallexordenList().forEach(t ->
        {
            list.add(new DetallexordenDto(t));
        });
        this.detallexordenDtos = list;

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

    public List<DetallexordenDto> getDetallexordenDtos()
    {
        return detallexordenDtos;
    }

    public void setDetallexordenDtos(List<DetallexordenDto> detallexordenDtos)
    {
        this.detallexordenDtos = detallexordenDtos;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("OrdenDto{id=").append(id);
        sb.append(", fecha=").append(fecha);
        sb.append(", estado=").append(estado);
        sb.append(", empleadoDto=").append(empleadoDto);
        sb.append(", mesaDto=").append(mesaDto);
        sb.append(", detallexordenDtos=").append(detallexordenDtos.toString());
        sb.append('}');
        return sb.toString();
    }

  
}
