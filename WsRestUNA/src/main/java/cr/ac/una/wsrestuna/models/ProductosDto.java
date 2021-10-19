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
public class ProductosDto
{

    private Long id;
    private String nombre;
    private String detalle;
    private byte[] foto;
    private Long costo;
    private Long cantidad;
    private Boolean modificado;

    public ProductosDto()
    {
        this.modificado = false;

    }

    public ProductosDto(Productos pro)
    {
        this();
        this.id = pro.getProId();
        this.nombre = pro.getProNombre();
        this.detalle = pro.getProDetalle();
        this.foto = pro.getProFoto();
        this.costo = pro.getProCosto();
        this.cantidad = pro.getProCantidad();
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

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ProductosDto{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", detalle=").append(detalle);
        sb.append(", foto=").append(foto);
        sb.append(", costo=").append(costo);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", modificado=").append(modificado);
        sb.append('}');
        return sb.toString();
    }

}
