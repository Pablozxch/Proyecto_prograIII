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
public class SalonDto
{

    private SimpleStringProperty id;
    private SimpleStringProperty nombre;
    private ObjectProperty<byte[]> foto;
    private SimpleStringProperty barraMesa;
    private ObjectProperty<RestauranteDto> resid;

    private Boolean modificado;

    public SalonDto()
    {
        this.modificado = false;
        this.id = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.foto = new SimpleObjectProperty();
        this.barraMesa = new SimpleStringProperty();
        this.resid = new SimpleObjectProperty();
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

    public byte[] getFoto()
    {
        return foto.getValue();
    }

    public void setFoto(byte[] foto)
    {
        this.foto.set(foto);
    }

    public Boolean getModificado()
    {
        return modificado;
    }

    public void setModificado(Boolean modificado)
    {
        this.modificado = modificado;
    }

    public String getBarraMesa()
    {
        return barraMesa.get();
    }

    public void setBarraMesa(String barraMesa)
    {
        this.barraMesa.set(barraMesa);
    }

     public RestauranteDto getResid()
    {
        return resid.get();
    }

    public void setResid(RestauranteDto resid)
    {
        this.resid.set(resid);
    }

    @Override
    public String toString()
    {
        return "SalonDto{" + "id=" + id + ", nombre=" + nombre + ", foto=" + foto + ", modificado=" + modificado + ", resid=" + resid.toString() + ", barraMesa=" + barraMesa + '}';
    }



}
