/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.models;

/**
 *
 * @author jp015
 */
public class RestauranteDto
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

    public RestauranteDto()
    {
        this.modificado = false;
    }

    public RestauranteDto(Restaurante restaurante)
    {
        this();
        this.id = restaurante.getResId();
        this.nombre = restaurante.getResNombre();
        this.detalle = restaurante.getResDetalle();
        this.direccion = restaurante.getResDireccion();
        this.correo = restaurante.getResCorreo();
        this.foto = restaurante.getResFoto();
        this.impVen = restaurante.getResImpv();
        this.impServ = restaurante.getResServ();
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
