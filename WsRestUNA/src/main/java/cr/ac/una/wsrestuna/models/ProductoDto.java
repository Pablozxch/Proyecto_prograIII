
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
public class ProductoDto
{

    private Long id;
    private String nombre;
    private String detalle;
    private byte[] foto;
    private Long costo;
    private Long cantidad;
    private String accesoRapido;
    private Long cantidadV;
    private Boolean modificado;

    public ProductoDto()
    {
        this.modificado = false;

    }

    public ProductoDto(Producto producto)
    {
        this();
        this.id = producto.getProId();
        this.nombre = producto.getProNombre();
        this.detalle = producto.getProDetalle();
        this.foto = producto.getProFoto();
        this.costo = producto.getProCosto();
        this.cantidad = producto.getProCantidad();
        this.accesoRapido = producto.getProAccesoRapido();
        this.cantidadV = producto.getProCantidadv();
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

    public String getDetalle()
    {
        return detalle;
    }

    public void setDetalle(String detalle)
    {
        this.detalle = detalle;
    }

    public byte[] getFoto()
    {
        return foto;
    }

    public void setFoto(byte[] foto)
    {
        this.foto = foto;
    }

    public Long getCosto()
    {
        return costo;
    }

    public void setCosto(Long costo)
    {
        this.costo = costo;
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

    public Long getCantidadV()
    {
        return cantidadV;
    }

    public void setCantidadV(Long cantidadV)
    {
        this.cantidadV = cantidadV;
    }

    public String getAccesoRapido()
    {
        return accesoRapido;
    }

    public void setAccesoRapido(String accesoRapido)
    {
        this.accesoRapido = accesoRapido;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ProductoDto{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", detalle=").append(detalle);
        sb.append(", foto=").append(foto);
        sb.append(", costo=").append(costo);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", accesoRapido=").append(accesoRapido);
        sb.append(", cantidadV=").append(cantidadV);
        sb.append('}');
        return sb.toString();
    }

}
