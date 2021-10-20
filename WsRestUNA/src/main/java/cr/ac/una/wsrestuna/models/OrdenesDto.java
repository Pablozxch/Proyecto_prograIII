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
public class OrdenesDto
{

    private Long id;
    private Long facTotal;
    private Date fecha;
    private Long desc;
    private EmpleadosDto emp;
    private MesaDto mesa;
    private Boolean modificado;

    public OrdenesDto()
    {
        this.modificado = false;
    }

    public OrdenesDto(Ordenes orden)
    {
        this.id = orden.getOrdId();
        this.facTotal = orden.getFacTotal();
        this.fecha = orden.getFacFecha();
        this.desc = orden.getFacDesc();
        this.emp = new EmpleadosDto(orden.getEmpId());
        this.mesa = new MesaDto(orden.getMesaId());
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getFacTotal()
    {
        return facTotal;
    }

    public void setFacTotal(Long facTotal)
    {
        this.facTotal = facTotal;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    public Long getDesc()
    {
        return desc;
    }

    public void setDesc(Long desc)
    {
        this.desc = desc;
    }

    public EmpleadosDto getEmp()
    {
        return emp;
    }

    public void setEmp(EmpleadosDto emp)
    {
        this.emp = emp;
    }

    public MesaDto getMesa()
    {
        return mesa;
    }

    public void setMesa(MesaDto mesa)
    {
        this.mesa = mesa;
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
