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
public class CodigodescDto
{

    private Long id;
    private String nombre;
    private Long desc;
    private String url;
    private Long cantidadusar;
    private RestauranteDto restaurante;
    private Boolean modificado;

    public CodigodescDto()
    {
        this.modificado = false;
    }

    public CodigodescDto(Codigodesc codigodesc)
    {
        this();
        this.id = codigodesc.getCodId();
        this.nombre = codigodesc.getCodNombre();
        this.desc = codigodesc.getCodDesc();
        this.url = codigodesc.getCodUrl();
        this.cantidadusar = codigodesc.getCodCant();
        this.restaurante = new RestauranteDto(codigodesc.getResId());
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

    public Long getDesc()
    {
        return desc;
    }

    public void setDesc(Long desc)
    {
        this.desc = desc;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Long getCantidadusar()
    {
        return cantidadusar;
    }

    public void setCantidadusar(Long cantidadusar)
    {
        this.cantidadusar = cantidadusar;
    }

    public RestauranteDto getRestaurante()
    {
        return restaurante;
    }

    public void setRestaurante(RestauranteDto restaurante)
    {
        this.restaurante = restaurante;
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
        sb.append("CodigodescDto{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", desc=").append(desc);
        sb.append(", url=").append(url);
        sb.append(", cantidadusar=").append(cantidadusar);
        sb.append(", restaurante=").append(restaurante.toString());
        sb.append('}');
        return sb.toString();
    }

}
