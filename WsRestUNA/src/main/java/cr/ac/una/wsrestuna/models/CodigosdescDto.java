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
public class CodigosdescDto
{

    private Long id;
    private String nombre;
    private Long cantidad;
    private Long cantidaddesc;
    private Boolean modificado;
    private String url;
    private RestaurantesDto resid;

    public CodigosdescDto()
    {
        this.modificado = false;
    }

    public CodigosdescDto(Codigosdesc cod)
    {
        this();
        this.id = cod.getCodId();
        this.nombre = cod.getCodNombre();
        this.cantidad = cod.getCodCant();
        this.url = cod.getCodUrl();
        this.cantidaddesc=cod.getCodCantdesc();
        this.resid = new RestaurantesDto(cod.getResId());
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

    public Long getCantidad()
    {
        return cantidad;
    }

    public void setCantidad(Long cantidad)
    {
        this.cantidad = cantidad;
    }

    public Boolean getModificado()
    {
        return modificado;
    }

    public void setModificado(Boolean modificado)
    {
        this.modificado = modificado;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public RestaurantesDto getResid()
    {
        return resid;
    }

    public void setResid(RestaurantesDto resid)
    {
        this.resid = resid;
    }
    public Long getCantidaddesc()
    {
        return cantidaddesc;
    }

    public void setCantidaddesc(Long cantidaddesc)
    {
        this.cantidaddesc = cantidaddesc;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CodigosdescDto{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", cantidaddesc=").append(cantidaddesc);
        sb.append(", url=").append(url);
        sb.append(", resid=").append(resid);
        sb.append('}');
        return sb.toString();
    }

}
