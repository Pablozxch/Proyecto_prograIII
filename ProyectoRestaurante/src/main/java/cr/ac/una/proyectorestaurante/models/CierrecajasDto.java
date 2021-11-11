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
public class CierrecajasDto
{

    private SimpleStringProperty id;
    private SimpleStringProperty montoInicial;
    private SimpleStringProperty montoFinal;
    private SimpleStringProperty MontoTarjeta;
    private SimpleStringProperty montoEfectivo;
    private SimpleStringProperty estado;
    private EmpleadoDto empleadoDto;
    private Boolean modificado;

    public CierrecajasDto()
    {
        this.id = new SimpleStringProperty();
        this.montoInicial = new SimpleStringProperty();
        this.montoFinal = new SimpleStringProperty();
        this.MontoTarjeta = new SimpleStringProperty();
        this.montoEfectivo = new SimpleStringProperty();
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

    public Long getMontoInicial()
    {
        if(montoInicial.get() != null && !montoInicial.get().isEmpty())
        {
            return Long.valueOf(montoInicial.get());
        }
        else
        {
            return null;
        }
    }

    public void setMontoInicial(Long montoInicial)
    {
        this.montoInicial.set(montoInicial.toString());
    }

    public Long getMontoFinal()
    {
        if(montoFinal.get() != null && !montoFinal.get().isEmpty())
        {
            return Long.valueOf(montoFinal.get());
        }
        else
        {
            return null;
        }
    }

    public void setMontoFinal(Long montoFinal)
    {
        this.montoFinal.set(montoFinal.toString());
    }

    public Long getMontoTarjeta()
    {
        if(MontoTarjeta.get() != null && !MontoTarjeta.get().isEmpty())
        {
            return Long.valueOf(MontoTarjeta.get());
        }
        else
        {
            return null;
        }
    }

    public void setMontoTarjeta(Long MontoTarjeta)
    {
        this.MontoTarjeta.set(MontoTarjeta.toString());
    }

    public Long getMontoEfectivo()
    {
        if(montoEfectivo.get() != null && !montoEfectivo.get().isEmpty())
        {
            return Long.valueOf(montoEfectivo.get());
        }
        else
        {
            return null;
        }
    }

    public void setMontoEfectivo(Long montoEfectivo)
    {
        this.montoEfectivo.set(montoEfectivo.toString());
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
        sb.append("CierrecajasDto{id=").append(id);
        sb.append(", montoInicial=").append(montoInicial);
        sb.append(", montoFinal=").append(montoFinal);
        sb.append(", MontoTarjeta=").append(MontoTarjeta);
        sb.append(", montoEfectivo=").append(montoEfectivo);
        sb.append(", estado=").append(estado);
        sb.append(", empleadoDto=").append(empleadoDto);
        sb.append(", modificado=").append(modificado);
        sb.append('}');
        return sb.toString();
    }

}
