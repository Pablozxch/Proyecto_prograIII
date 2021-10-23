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
public class ProductosxordenDto
{

    private Long id;
    private Long cantidad;
    private OrdenDto idO;
    private ProductoDto idP;
    private Boolean modificado;

    public ProductosxordenDto()
    {
        this.modificado = false;
    }

    public ProductosxordenDto(Productosxorden productoxorden)
    {
        this.id = productoxorden.getPxoId();
        this.cantidad = productoxorden.getPxoCantidad();
        this.idO = new OrdenDto(productoxorden.getOrdId());
        this.idP = new ProductoDto(productoxorden.getProId());
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

    public OrdenDto getIdO()
    {
        return idO;
    }

    public void setIdO(OrdenDto idO)
    {
        this.idO = idO;
    }

    public ProductoDto getIdP()
    {
        return idP;
    }

    public void setIdP(ProductoDto idP)
    {
        this.idP = idP;
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
        sb.append("ProductosxordenDto{id=").append(id);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", idO=").append(idO);
        sb.append(", idP=").append(idP);
        sb.append('}');
        return sb.toString();
    }

}
