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
public class RestaurantesDto
{

    private Long id;
    private String nombre;
    private String detalle;
    private String direccion;
    private String correo;
    private byte[] foto;
    private Long impVen;
    private Long impServ;
    private Boolean modificado;
    private List<CodigosdescDto> codigos;//DUDA
    private List<CodigosdescDto> codigosEliminados;

    public RestaurantesDto()
    {
        this.modificado = false;
        codigos = new ArrayList<>();
        codigosEliminados = new ArrayList<>();
    }

    public RestaurantesDto(Restaurantes res)
    {
        this();
        this.id = res.getResId();
        this.nombre = res.getResNombre();
        this.detalle = res.getResDetalle();
        this.direccion = res.getResDireccion();
        this.correo = res.getResCorreo();
        this.foto = res.getResFoto();
        this.impVen = res.getResVen();
        this.impServ = res.getResServ();
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

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public byte[] getFoto()
    {
        return foto;
    }

    public void setFoto(byte[] foto)
    {
        this.foto = foto;
    }

    public Long getImpVen()
    {
        return impVen;
    }

    public void setImpVen(Long impVen)
    {
        this.impVen = impVen;
    }

    public Long getImpServ()
    {
        return impServ;
    }

    public void setImpServ(Long impServ)
    {
        this.impServ = impServ;
    }

    public Boolean getModificado()
    {
        return modificado;
    }

    public void setModificado(Boolean modificado)
    {
        this.modificado = modificado;
    }

    public List<CodigosdescDto> getCodigos()
    {
        return codigos;
    }

    public void setCodigos(List<CodigosdescDto> codigos)
    {
        this.codigos = codigos;
    }

    public List<CodigosdescDto> getCodigosEliminados()
    {
        return codigosEliminados;
    }

    public void setCodigosEliminados(List<CodigosdescDto> codigosEliminados)
    {
        this.codigosEliminados = codigosEliminados;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("RestaurantesDto{id=").append(id);
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
