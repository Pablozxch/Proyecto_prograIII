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
public class ProductoDto
{

    private SimpleStringProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty nombrecorto;
    private SimpleStringProperty detalle;
    private ObjectProperty<byte[]> foto;
    private SimpleStringProperty costo;
    private SimpleStringProperty cantidad;
    private SimpleStringProperty accesoRapido;
    private SimpleStringProperty cantidadV;
    private Boolean modificado;
    private RestauranteDto restauranteDto;

    public ProductoDto()
    {
        this.id = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.nombrecorto = new SimpleStringProperty();
        this.detalle = new SimpleStringProperty();
        this.foto = new SimpleObjectProperty();
        this.costo = new SimpleStringProperty();
        this.cantidad = new SimpleStringProperty();
        this.accesoRapido = new SimpleStringProperty();
        this.cantidadV = new SimpleStringProperty();
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

    public String getNombre()
    {
        return nombre.get();
    }

    public void setNombre(String nombre)
    {
        this.nombre.set(nombre);
    }

    public String getNombrecorto()
    {
        return nombrecorto.get();
    }

    public void setNombrecorto(String nombrecorto)
    {
        this.nombrecorto.set(nombrecorto);
    }

    public String getDetalle()
    {
        return detalle.get();
    }

    public void setDetalle(String detalle)
    {
        this.detalle.set(detalle);
    }

    public byte[] getFoto()
    {
        return foto.get();
    }

    public void setFoto(byte[] foto)
    {
        this.foto.setValue(foto);
    }

    public Long getCosto()
    {
        if(costo.get() != null && !costo.get().isEmpty())
        {
            return Long.valueOf(costo.get());
        }
        else
        {
            return null;
        }
    }

    public void setCosto(Long costo)
    {
        this.costo.set(costo.toString());
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

    public String getAccesoRapido()
    {
        return accesoRapido.get();
    }

    public void setAccesoRapido(String accesoRapido)
    {
        this.accesoRapido.set(accesoRapido);
    }

    public Long getCantidadV()
    {
        if(cantidadV.get() != null && !cantidadV.get().isEmpty())
        {
            return Long.valueOf(cantidadV.get());
        }
        else
        {
            return null;
        }
    }

    public void setCantidadV(Long cantidadV)
    {
        this.cantidadV.set(cantidadV.toString());
    }

    public Boolean getModificado()
    {
        return modificado;
    }

    public void setModificado(Boolean modificado)
    {
        this.modificado = modificado;
    }

    public RestauranteDto getRestauranteDto()
    {
        return restauranteDto;
    }

    public void setRestauranteDto(RestauranteDto restauranteDto)
    {
        this.restauranteDto = restauranteDto;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ProductoDto{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", nombrecorto=").append(nombrecorto);
        sb.append(", detalle=").append(detalle);
        sb.append(", foto=").append(foto);
        sb.append(", costo=").append(costo);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", accesoRapido=").append(accesoRapido);
        sb.append(", cantidadV=").append(cantidadV);
        sb.append(", restauranteDto=").append(restauranteDto.toString());
        sb.append('}');
        return sb.toString();
    }

}
