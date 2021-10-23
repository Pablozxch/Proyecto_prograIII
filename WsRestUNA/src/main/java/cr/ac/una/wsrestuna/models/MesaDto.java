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
public class MesaDto
{

    private Long id;
    private String nombre;
    private String estado;
    private Long posx;
    private Long posy;
    private SalonDto salon;
    private Boolean modificado;

    public MesaDto()
    {
        this.modificado = false;
    }

    public MesaDto(Mesa mesa)
    {
        this();
        this.id=mesa.getMesaId();
        this.nombre=mesa.getMesaNombre();
        this.estado=mesa.getMesaEstado();
        this.posx=mesa.getMesaPosx();
        this.posy=mesa.getMesaPosy();
        this.salon=new SalonDto(mesa.getSalId());
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

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public Long getPosx()
    {
        return posx;
    }

    public void setPosx(Long posx)
    {
        this.posx = posx;
    }

    public Long getPosy()
    {
        return posy;
    }

    public void setPosy(Long posy)
    {
        this.posy = posy;
    }

    public SalonDto getSalon()
    {
        return salon;
    }

    public void setSalon(SalonDto salon)
    {
        this.salon = salon;
    }

    public Boolean getModificado()
    {
        return modificado;
    }

    public void setModificado(Boolean modificado)
    {
        this.modificado = modificado;
    }
    
}
