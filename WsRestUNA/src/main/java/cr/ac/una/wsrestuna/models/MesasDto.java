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
public class MesasDto
{

    private Long id;
    private String nombre;
    private String estado;
    private Long posx;
    private Long posy;
    private SalonesDto salon;
    private Boolean modificado;

    public MesasDto()
    {
        this.modificado = false;
    }

    public MesasDto(Mesas mes)
    {
        this();
        this.id=mes.getMesaId();
        this.nombre=mes.getMesaNombre();
        this.estado=mes.getMesaEstado();
        this.posx=mes.getMesaPosx();
        this.posy=mes.getMesaPosy();
        this.salon=new SalonesDto(mes.getSalId());
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

    public SalonesDto getSalon()
    {
        return salon;
    }

    public void setSalon(SalonesDto salon)
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
