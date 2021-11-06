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
public class DetallexordenDto
{

    private Long id;
    private Long cantidad;
    private Long precio;
    private ProductoDto productoDto;
    private Boolean modificado;
    private OrdenDto ordenId;

    public DetallexordenDto()
    {
        this.modificado = false;
    }

    public DetallexordenDto(Detallexorden detallexorden)
    {
        this();
        this.id = detallexorden.getDxoId();
        this.cantidad = detallexorden.getDxoCantidad();
        this.precio = detallexorden.getDxoPrecioc();
        this.productoDto = new ProductoDto(detallexorden.getProId());
        this.ordenId = new OrdenDto(detallexorden.getOrdId());

    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getCantidad()
    {
        return cantidad;
    }

    public void setCantidad(Long cantidad)
    {
        this.cantidad = cantidad;
    }

    public Long getPrecio()
    {
        return precio;
    }

    public void setPrecio(Long precio)
    {
        this.precio = precio;
    }

    public ProductoDto getProductoDto()
    {
        return productoDto;
    }

    public void setProductoDto(ProductoDto productoDto)
    {
        this.productoDto = productoDto;
    }

    public Boolean getModificado()
    {
        return modificado;
    }

    public void setModificado(Boolean modificado)
    {
        this.modificado = modificado;
    }

    public OrdenDto getOrdenId()
    {
        return ordenId;
    }

    public void setOrdenId(OrdenDto ordenId)
    {
        this.ordenId = ordenId;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("DetallexordenDto{id=").append(id);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", precio=").append(precio);
        sb.append(", productoDto=").append(productoDto);
        sb.append(", ordenId=").append(ordenId);
        sb.append('}');
        return sb.toString();
    }
    


}
