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
public class CierrecajasDto
{

    private Long id;
    private Long montoInicial;
    private Long montoFinal;
    private Long MontoTarjeta;
    private Long montoEfectivo;
    private String estado;
    private EmpleadoDto empleadoDto;
    private Boolean modificado;

    public CierrecajasDto()
    {
        this.modificado = false;
    }

    public CierrecajasDto(Cierrecajas cierrecajas)
    {
        this();
        this.id = cierrecajas.getCcajId();
        this.montoInicial = cierrecajas.getCcajMontoinicial();
        this.montoFinal = cierrecajas.getCcjaMontofinal();
        this.MontoTarjeta = cierrecajas.getCcajMontotarjeta();
        this.montoEfectivo = cierrecajas.getCcajMontoefectivo();
        this.estado = cierrecajas.getCcjaEstado();
        this.empleadoDto = new EmpleadoDto(cierrecajas.getEmpId());
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getMontoInicial()
    {
        return montoInicial;
    }

    public void setMontoInicial(Long montoInicial)
    {
        this.montoInicial = montoInicial;
    }

    public Long getMontoFinal()
    {
        return montoFinal;
    }

    public void setMontoFinal(Long montoFinal)
    {
        this.montoFinal = montoFinal;
    }

    public Long getMontoTarjeta()
    {
        return MontoTarjeta;
    }

    public void setMontoTarjeta(Long MontoTarjeta)
    {
        this.MontoTarjeta = MontoTarjeta;
    }

    public Long getMontoEfectivo()
    {
        return montoEfectivo;
    }

    public void setMontoEfectivo(Long montoEfectivo)
    {
        this.montoEfectivo = montoEfectivo;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public EmpleadoDto getEmpleadoDto()
    {
        return empleadoDto;
    }

    public void setEmpleadoDto(EmpleadoDto empleadoDto)
    {
        this.empleadoDto = empleadoDto;
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
        sb.append("CierrecajasDto{id=").append(id);
        sb.append(", montoInicial=").append(montoInicial);
        sb.append(", montoFinal=").append(montoFinal);
        sb.append(", MontoTarjeta=").append(MontoTarjeta);
        sb.append(", montoEfectivo=").append(montoEfectivo);
        sb.append(", estado=").append(estado);
        sb.append(", empleadoDto=").append(empleadoDto);
        sb.append(", modificado=").append(modificado);
        sb.append('}');
        return sb.toString();
    }

}
