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
public class CategoriasDto
{

    private Long id;
    private String nombre;
    private String detalle;
    private Boolean modificado;
    private List<ProductosDto> productos;
    private List<ProductosDto> productosEliminados;

    public CategoriasDto()
    {
        this.modificado = false;
        productos = new ArrayList<>();
        productosEliminados = new ArrayList<>();
    }

    public CategoriasDto(Categorias cat)
    {
        this();
        this.id = cat.getCatId();
        this.nombre = cat.getCatNombre();
        this.detalle = cat.getCatDescripcion();

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

    public Boolean getModificado()
    {
        return modificado;
    }

    public void setModificado(Boolean modificado)
    {
        this.modificado = modificado;
    }

    public List<ProductosDto> getProductos()
    {
        return productos;
    }

    public void setProductos(List<ProductosDto> productos)
    {
        this.productos = productos;
    }

    public List<ProductosDto> getProductosEliminados()
    {
        return productosEliminados;
    }

    public void setProductosEliminados(List<ProductosDto> productosEliminados)
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
