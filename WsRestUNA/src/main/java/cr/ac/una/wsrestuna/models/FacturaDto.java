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
public class FacturaDto
{

    private Long id;
    private Long descuento;
    private String efetivoTarjeta;
    private Long subtotal;
    private Long total;
    private Long montoFinal;//con descuento

    private OrdenDto ordenDto;
    private CierrecajasDto cierrecajasDto;
    private CodigodescDto codigodescDto;
    private Boolean modificado;

    public FacturaDto()
    {
        this.modificado = false;
    }

    public FacturaDto(Factura factura)
    {
        this();
        this.id = factura.getFacId();
        this.descuento = factura.getFacDesc();
        this.efetivoTarjeta = factura.getFacEfectivotarjeta();
        this.subtotal = factura.getFacSubtotal();
        this.total = factura.getFacTotal();
        this.montoFinal = factura.getFacMontocdesc();
        this.ordenDto = new OrdenDto(factura.getOrdId());
        this.cierrecajasDto = new CierrecajasDto(factura.getCcajId());
      
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getDescuento()
    {
        return descuento;
    }

    public void setDescuento(Long descuento)
    {
        this.descuento = descuento;
    }

    public String getEfetivoTarjeta()
    {
        return efetivoTarjeta;
    }

    public void setEfetivoTarjeta(String efetivoTarjeta)
    {
        this.efetivoTarjeta = efetivoTarjeta;
    }

    public Long getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(Long subtotal)
    {
        this.subtotal = subtotal;
    }

    public Long getTotal()
    {
        return total;
    }

    public void setTotal(Long total)
    {
        this.total = total;
    }

    public Long getMontoFinal()
    {
        return montoFinal;
    }

    public void setMontoFinal(Long montoFinal)
    {
        this.montoFinal = montoFinal;
    }

    public OrdenDto getOrdenDto()
    {
        return ordenDto;
    }

    public void setOrdenDto(OrdenDto ordenDto)
    {
        this.ordenDto = ordenDto;
    }

    public CierrecajasDto getCierrecajasDto()
    {
        return cierrecajasDto;
    }

    public void setCierrecajasDto(CierrecajasDto cierrecajasDto)
    {
        this.cierrecajasDto = cierrecajasDto;
    }

    public CodigodescDto getCodigodescDto()
    {
        return codigodescDto;
    }

    public void setCodigodescDto(CodigodescDto codigodescDto)
    {
        this.codigodescDto = codigodescDto;
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
        sb.append("FacturaDto{id=").append(id);
        sb.append(", descuento=").append(descuento);
        sb.append(", efetivoTarjeta=").append(efetivoTarjeta);
        sb.append(", subtotal=").append(subtotal);
        sb.append(", total=").append(total);
        sb.append(", montoFinal=").append(montoFinal);
        sb.append(", ordenDto=").append(ordenDto);
        sb.append(", cierrecajasDto=").append(cierrecajasDto);
        sb.append(", codigodescDto=").append(codigodescDto);
        sb.append('}');
        return sb.toString();
    }

}
