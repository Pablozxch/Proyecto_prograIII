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
public class RestauranteDto
{

    private SimpleStringProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty detalle;
    private SimpleStringProperty direccion;
    private SimpleStringProperty correo;
    private ObjectProperty<byte[]> foto;
    private SimpleStringProperty impVen;
    private SimpleStringProperty impServ;
    private Boolean modificado;

    public RestauranteDto()
    {
        this.id = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.detalle = new SimpleStringProperty();
        this.direccion = new SimpleStringProperty();
        this.correo = new SimpleStringProperty();
        this.foto = new SimpleObjectProperty();
        this.impVen = new SimpleStringProperty();
        this.impServ = new SimpleStringProperty();

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

    public String getDetalle()
    {
        return detalle.get();
    }

    public void setDetalle(String detalle)
    {
        this.detalle.set(detalle);
    }

    public String getDireccion()
    {
        return direccion.get();
    }

    public void setDireccion(String direccion)
    {
        this.direccion.set(direccion);
    }

    public String getCorreo()
    {
        return correo.get();
    }

    public void setCorreo(String correo)
    {
        this.correo.set(correo);
    }

    public byte[] getFoto()
    {
        return foto.getValue();
    }

    public void setFoto(byte[] foto)
    {
        this.foto.set(foto);
    }

    public Long getImpVen()
    {
        if(impVen.get() != null && !impVen.get().isEmpty())
        {
            return Long.valueOf(impVen.get());
        }
        else
        {
            return null;
        }
    }

    public void setImpVen(Long impVen)
    {
        this.impVen.set(impVen.toString());
    }

    public Long getImpServ()
    {
        if(impServ.get() != null && !impServ.get().isEmpty())
        {
            return Long.valueOf(impServ.get());
        }
        else
        {
            return null;
        }
    }

    public void setImpServ(Long impServ)
    {
        this.impServ.set(impServ.toString());
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
        sb.append("RestauranteDto{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", detalle=").append(detalle);
        sb.append(", direccion=").append(direccion);
        sb.append(", correo=").append(correo);
        sb.append(", foto=").append(foto);
        sb.append(", impVen=").append(impVen);
        sb.append(", impServ=").append(impServ);
        sb.append('}');
        return sb.toString();
    }

}
