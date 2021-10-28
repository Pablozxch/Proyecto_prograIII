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
public class FacturaDto
{

    private SimpleStringProperty id;
    private SimpleStringProperty descuento;
    private SimpleStringProperty efetivoTarjeta;
    private SimpleStringProperty subtotal;
    private SimpleStringProperty total;
    private SimpleStringProperty montoFinal;//con descuento

    private OrdenDto ordenDto;
    private CierrecajasDto cierrecajasDto;
    private CodigodescDto codigodescDto;
    private Boolean modificado;

    public FacturaDto()
    {
        this.id = new SimpleStringProperty();
        this.descuento = new SimpleStringProperty();
        this.efetivoTarjeta = new SimpleStringProperty();
        this.subtotal = new SimpleStringProperty();
        this.total = new SimpleStringProperty();
        this.montoFinal = new SimpleStringProperty();
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

    public Long getDescuento()
    {
        if(descuento.get() != null && !descuento.get().isEmpty())
        {
            return Long.valueOf(descuento.get());
        }
        else
        {
            return null;
        }
    }

    public void setDescuento(Long descuento)
    {
        this.descuento.set(descuento.toString());
    }

    public String getEfetivoTarjeta()
    {
        return efetivoTarjeta.get();
    }

    public void setEfetivoTarjeta(String efetivoTarjeta)
    {
        this.efetivoTarjeta.set(efetivoTarjeta);
    }

    public Long getSubtotal()
    {
        if(subtotal.get() != null && !subtotal.get().isEmpty())
        {
            return Long.valueOf(subtotal.get());
        }
        else
        {
            return null;
        }
    }

    public void setSubtotal(Long subtotal)
    {
        this.subtotal.set(subtotal.toString());
    }

    public Long getTotal()
    {
        if(total.get() != null && !total.get().isEmpty())
        {
            return Long.valueOf(total.get());
        }
        else
        {
            return null;
        }
    }

    public void setTotal(Long total)
    {
        this.total.set(total.toString());
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

    public OrdenDto getOrdenDto()
    {
        return ordenDto;
    }

    public void setOrdenDto(OrdenDto ordenDto)
    {
        this.ordenDto = ordenDto;
    }

    public CierrecajasDto getCierrecajasDto()
    {
        return cierrecajasDto;
    }

    public void setCierrecajasDto(CierrecajasDto cierrecajasDto)
    {
        this.cierrecajasDto = cierrecajasDto;
    }

    public CodigodescDto getCodigodescDto()
    {
        return codigodescDto;
    }

    public void setCodigodescDto(CodigodescDto codigodescDto)
    {
        this.codigodescDto = codigodescDto;
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
        sb.append("FacturaDto{id=").append(id);
        sb.append(", descuento=").append(descuento);
        sb.append(", efetivoTarjeta=").append(efetivoTarjeta);
        sb.append(", subtotal=").append(subtotal);
        sb.append(", total=").append(total);
        sb.append(", montoFinal=").append(montoFinal);
        sb.append(", ordenDto=").append(ordenDto);
        sb.append(", cierrecajasDto=").append(cierrecajasDto);
        sb.append(", codigodescDto=").append(codigodescDto);
        sb.append('}');
        return sb.toString();
    }

}
