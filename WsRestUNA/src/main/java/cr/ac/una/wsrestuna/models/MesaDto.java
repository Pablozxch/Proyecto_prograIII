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
    private Long posX;
    private Long posY;
    private SalonDto salonDto;
    private Boolean modificado;

    public MesaDto()
    {
        this.modificado = false;
    }

    public MesaDto(Mesa mesa)
    {
        this.id = mesa.getMesaId();
        this.nombre = mesa.getMesaNombre();
        this.estado = mesa.getMesaEstado();
        this.posX = mesa.getMesaPosx();
        this.posY = mesa.getMesaPosy();
        this.salonDto = new SalonDto(mesa.getSalId());
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

    public Long getPosX()
    {
        return posX;
    }

    public void setPosX(Long posX)
    {
        this.posX = posX;
    }

    public Long getPosY()
    {
        return posY;
    }

    public void setPosY(Long posY)
    {
        this.posY = posY;
    }

    public SalonDto getSalonDto()
    {
        return salonDto;
    }

    public void setSalonDto(SalonDto salonDto)
    {
        this.salonDto = salonDto;
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
        sb.append("MesaDto{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", estado=").append(estado);
        sb.append(", posX=").append(posX);
        sb.append(", posY=").append(posY);
        sb.append(", salonDto=").append(salonDto.toString());
        sb.append('}');
        return sb.toString();
    }

}
