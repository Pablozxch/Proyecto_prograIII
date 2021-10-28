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
public class DetallexordenDto
{

    private SimpleStringProperty id;
    private SimpleStringProperty cantidad;
    private SimpleStringProperty precio;
    private ProductoDto productoDto;
    private OrdenDto ordenDto;
    private Boolean modificado;

    public DetallexordenDto()
    {
        this.id = new SimpleStringProperty();
        this.cantidad = new SimpleStringProperty();
        this.precio = new SimpleStringProperty();
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

    public Long getCantidad()
    {
        if(cantidad.get() != null && !cantidad.get().isEmpty())
        {
            return Long.valueOf(cantidad.get());
        }
        else
        {
            return null;
        }
    }

    public void setCantidad(Long cantidad)
    {
        this.cantidad.set(cantidad.toString());
    }

    public Long getPrecio()
    {
        if(precio.get() != null && !precio.get().isEmpty())
        {
            return Long.valueOf(precio.get());
        }
        else
        {
            return null;
        }
    }

    public void setPrecio(Long precio)
    {
        this.precio.set(precio.toString());
    }

    public ProductoDto getProductoDto()
    {
        return productoDto;
    }

    public void setProductoDto(ProductoDto productoDto)
    {
        this.productoDto = productoDto;
    }

    public OrdenDto getOrdenDto()
    {
        return ordenDto;
    }

    public void setOrdenDto(OrdenDto ordenDto)
    {
        this.ordenDto = ordenDto;
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
        sb.append("DetallexordenDto{id=").append(id);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", precio=").append(precio);
        sb.append(", productoDto=").append(productoDto.toString());
        sb.append(", ordenDto=").append(ordenDto.toString());
        sb.append('}');
        return sb.toString();
    }

}
