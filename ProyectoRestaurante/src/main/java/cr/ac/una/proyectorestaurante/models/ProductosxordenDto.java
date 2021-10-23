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
public class ProductosxordenDto
{

    private SimpleStringProperty id;
    private SimpleStringProperty cantidad;
    private SimpleObjectProperty<OrdenDto> idO;
    private SimpleObjectProperty<ProductoDto> idP;
    private Boolean modificado;

    public ProductosxordenDto()
    {
        this.modificado = false;
        this.id = new SimpleStringProperty();
        this.cantidad = new SimpleStringProperty();
        this.idO = new SimpleObjectProperty();
        this.idP = new SimpleObjectProperty();
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

    public OrdenDto getIdO()
    {
        return idO.get();
    }

    public void setIdO(OrdenDto idO)
    {
        this.idO.set(idO);
    }

    public ProductoDto getIdP()
    {
        return idP.get();
    }

    public void setIdP(ProductoDto idP)
    {
        this.idP.set(idP);
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
        sb.append("ProductosxordenDto{id=").append(id);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", idO=").append(idO);
        sb.append(", idP=").append(idP);
        sb.append('}');
        return sb.toString();
    }

}
