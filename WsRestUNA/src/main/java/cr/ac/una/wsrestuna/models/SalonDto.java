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
public class SalonDto
{
    
    private Long id;
    private String nombre;
    private byte[] foto;
    private Boolean modificado;
    private String barraMesa;
    private RestauranteDto resid;
    
    public SalonDto()
    {
        this.modificado = false;
    }
    
    public SalonDto(Salon salon)
    {
        this();
        this.id = salon.getSalId();
        this.nombre = salon.getSalNombre();
        this.foto = salon.getSalImagen();
        this.barraMesa = salon.getSalBarraomesa();
        this.resid = new RestauranteDto(salon.getResId());
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
    
    public byte[] getFoto()
    {
        return foto;
    }
    
    public void setFoto(byte[] foto)
    {
        this.foto = foto;
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
        return barraMesa;
    }
    
    public void setBarraMesa(String barraMesa)
    {
        this.barraMesa = barraMesa;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SalonesDto{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", barraMesa=").append(barraMesa);
        sb.append(", restaurante=").append(resid.toString());
        sb.append('}');
        return sb.toString();
    }
    
    public RestauranteDto getResid()
    {
        return resid;
    }
    
    public void setResid(RestauranteDto resid)
    {
        this.resid = resid;
    }
    
}
