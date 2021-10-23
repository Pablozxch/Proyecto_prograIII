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
public class CategoriaDto
{

    private SimpleStringProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty detalle;
    private Boolean modificado;
    private List<ProductoDto> productos;
    private List<ProductoDto> productosEliminados;

    public CategoriaDto()
    {
        this.modificado = false;
        this.id=new SimpleStringProperty();
        this.nombre=new SimpleStringProperty();
        this.detalle=new SimpleStringProperty();
        productos = new ArrayList<>();
        productosEliminados = new ArrayList<>();
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

    public String getDetalle()
    {
        return detalle.get();
    }

    public void setDetalle(String detalle)
    {
        this.detalle.set(detalle);
    }

    public Boolean getModificado()
    {
        return modificado;
    }

    public void setModificado(Boolean modificado)
    {
        this.modificado = modificado;
    }

    public List<ProductoDto> getProductos()
    {
        return productos;
    }

    public void setProductos(List<ProductoDto> productos)
    {
        this.productos = productos;
    }

    public List<ProductoDto> getProductosEliminados()
    {
        return productosEliminados;
    }

    public void setProductosEliminados(List<ProductoDto> productosEliminados)
    {
        this.productosEliminados = productosEliminados;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CategoriasDto{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", detalle=").append(detalle);
        sb.append(", modificado=").append(modificado);
        sb.append('}');
        return sb.toString();
    }

}
