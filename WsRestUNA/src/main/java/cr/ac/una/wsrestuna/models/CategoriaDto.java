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
public class CategoriaDto
{

    private Long id;
    private String nombre;
    private String detalle;
    private List<ProductoDto> productos;
    private Boolean modificado;

    public CategoriaDto()
    {
        this.modificado = false;
        productos = new ArrayList<>();
    }

    public CategoriaDto(Categoria categoria)
    {
        this();
        this.id = categoria.getCatId();
        this.nombre = categoria.getCatNombre();
        this.detalle = categoria.getCatDetalle();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getDetalle()
    {
        return detalle;
    }

    public void setDetalle(String detalle)
    {
        this.detalle = detalle;
    }

    public List<ProductoDto> getProductos()
    {
        return productos;
    }

    public void setProductos(List<ProductoDto> productos)
    {
        this.productos = productos;
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
        return "CategoriaDto{" + "id=" + id + ", nombre=" + nombre + ", detalle=" + detalle + ", productos=" + productos.toString() + '}';
    }

}
