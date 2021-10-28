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
    private String nombrecorto;
    private String detalle;
    private byte[] foto;
    private Long costo;
    private Long cantidad;
    private String accesoRapido;
    private Long cantidadV;
    private Boolean modificado;
    private RestauranteDto restauranteDto;

    public ProductoDto()
    {
        this.modificado = false;
    }

    public ProductoDto(Producto producto)
    {
        this();
        this.id = producto.getProId();
        this.nombre = producto.getProNombre();
        this.nombrecorto = producto.getProNombrecorto();
        this.detalle = producto.getProDetalle();
        this.foto = producto.getProFoto();
        this.costo = producto.getProCosto();
        this.cantidad = producto.getProCantidad();
        this.accesoRapido = producto.getProAccesoRapido();
        this.cantidadV = producto.getProCantidadv();
        this.restauranteDto = new RestauranteDto(producto.getResId());
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

    public String getNombrecorto()
    {
        return nombrecorto;
    }

    public void setNombrecorto(String nombrecorto)
    {
        this.nombrecorto = nombrecorto;
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

    public String getAccesoRapido()
    {
        return accesoRapido;
    }

    public void setAccesoRapido(String accesoRapido)
    {
        this.accesoRapido = accesoRapido;
    }

    public Long getCantidadV()
    {
        return cantidadV;
    }

    public void setCantidadV(Long cantidadV)
    {
        this.cantidadV = cantidadV;
    }

    public Boolean getModificado()
    {
        return modificado;
    }

    public void setModificado(Boolean modificado)
    {
        this.modificado = modificado;
    }

    public RestauranteDto getRestauranteDto()
    {
        return restauranteDto;
    }

    public void setRestauranteDto(RestauranteDto restauranteDto)
    {
        this.restauranteDto = restauranteDto;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ProductoDto{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", nombrecorto=").append(nombrecorto);
        sb.append(", detalle=").append(detalle);
        sb.append(", foto=").append(foto);
        sb.append(", costo=").append(costo);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", accesoRapido=").append(accesoRapido);
        sb.append(", cantidadV=").append(cantidadV);
        sb.append(", restauranteDto=").append(restauranteDto.toString());
        sb.append('}');
        return sb.toString();
    }

}
