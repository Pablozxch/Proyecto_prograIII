/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.models;

import java.io.*;
import java.math.*;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 *
 * @author jp015
 */
@Entity
@Table(name = "TBL_PRODUCTO" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Producto.findAll" , query = "SELECT p FROM Producto p") ,
              @NamedQuery(name = "Producto.findByProId" , query = "SELECT p FROM Producto p WHERE p.proId = :proId") ,
              @NamedQuery(name = "Producto.findByProNombre" , query = "SELECT p FROM Producto p WHERE p.proNombre = :proNombre") ,
              @NamedQuery(name = "Producto.findByProNombrecorto" , query = "SELECT p FROM Producto p WHERE p.proNombrecorto = :proNombrecorto") ,
              @NamedQuery(name = "Producto.findByProDetalle" , query = "SELECT p FROM Producto p WHERE p.proDetalle = :proDetalle") ,
              @NamedQuery(name = "Producto.findByProCosto" , query = "SELECT p FROM Producto p WHERE p.proCosto = :proCosto") ,
              @NamedQuery(name = "Producto.findByProCantidad" , query = "SELECT p FROM Producto p WHERE p.proCantidad = :proCantidad") ,
              @NamedQuery(name = "Producto.findByProAccesoRapido" , query = "SELECT p FROM Producto p WHERE p.proAccesoRapido = :proAccesoRapido") ,
              @NamedQuery(name = "Producto.findByProCantidadv" , query = "SELECT p FROM Producto p WHERE p.proCantidadv = :proCantidadv") ,
              @NamedQuery(name = "Producto.findByProVersion" , query = "SELECT p FROM Producto p WHERE p.proVersion = :proVersion")
          })
public class Producto implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_PRODUCTO_PRO_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_PRODUCTO_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_PRODUCTO_PRO_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "PRO_ID")
    private Long proId;
    @Basic(optional = false)
    @Column(name = "PRO_NOMBRE")
    private String proNombre;
    @Basic(optional = false)
    @Column(name = "PRO_NOMBRECORTO")
    private String proNombrecorto;
    @Basic(optional = false)
    @Column(name = "PRO_DETALLE")
    private String proDetalle;
    @Basic(optional = false)
    @Lob
    @Column(name = "PRO_FOTO")
    private byte[] proFoto;
    @Basic(optional = false)
    @Column(name = "PRO_COSTO")
    private Long proCosto;
    @Basic(optional = false)
    @Column(name = "PRO_CANTIDAD")
    private Long proCantidad;
    @Basic(optional = false)
    @Column(name = "PRO_ACCESO_RAPIDO")
    private String proAccesoRapido;
    @Basic(optional = false)
    @Column(name = "PRO_CANTIDADV")
    private Long proCantidadv;
    @Basic(optional = false)
    @Version
    @Column(name = "PRO_VERSION")
    private Long proVersion;
    @ManyToMany(mappedBy = "productoList" , fetch = FetchType.LAZY)
    private List<Categoria> categoriaList;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "proId" , fetch = FetchType.LAZY)
    private List<Detallexorden> detallexordenList;
    @JoinColumn(name = "RES_ID" , referencedColumnName = "RES_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Restaurante resId;

    public Producto()
    {
    }

    public Producto(Long proId)
    {
        this.proId = proId;
    }

    public Producto(Long proId , String proNombre , String proNombrecorto , String proDetalle , byte[] proFoto , Long proCosto , Long proCantidad , String proAccesoRapido , Long proCantidadv)
    {
        this.proId = proId;
        this.proNombre = proNombre;
        this.proNombrecorto = proNombrecorto;
        this.proDetalle = proDetalle;
        this.proFoto = proFoto;
        this.proCosto = proCosto;
        this.proCantidad = proCantidad;
        this.proAccesoRapido = proAccesoRapido;
        this.proCantidadv = proCantidadv;
    }

    public Producto(ProductoDto productoDto)
    {
        this.proId = productoDto.getId();
        actualizarProducto(productoDto);
    }

    public void actualizarProducto(ProductoDto productoDto)
    {
        this.proNombre = productoDto.getNombre();
        this.proNombrecorto = productoDto.getNombrecorto();
        this.proDetalle = productoDto.getDetalle();
        this.proFoto = productoDto.getFoto();
        this.proCosto = productoDto.getCosto();
        this.proCantidad = productoDto.getCantidad();
        this.proAccesoRapido = productoDto.getAccesoRapido();
        this.proCantidadv = productoDto.getCantidadV();
    }

    public Long getProId()
    {
        return proId;
    }

    public void setProId(Long proId)
    {
        this.proId = proId;
    }

    public String getProNombre()
    {
        return proNombre;
    }

    public void setProNombre(String proNombre)
    {
        this.proNombre = proNombre;
    }

    public String getProNombrecorto()
    {
        return proNombrecorto;
    }

    public void setProNombrecorto(String proNombrecorto)
    {
        this.proNombrecorto = proNombrecorto;
    }

    public String getProDetalle()
    {
        return proDetalle;
    }

    public void setProDetalle(String proDetalle)
    {
        this.proDetalle = proDetalle;
    }

    public byte[] getProFoto()
    {
        return proFoto;
    }

    public void setProFoto(byte[] proFoto)
    {
        this.proFoto = proFoto;
    }

    public Long getProCosto()
    {
        return proCosto;
    }

    public void setProCosto(Long proCosto)
    {
        this.proCosto = proCosto;
    }

    public Long getProCantidad()
    {
        return proCantidad;
    }

    public void setProCantidad(Long proCantidad)
    {
        this.proCantidad = proCantidad;
    }

    public String getProAccesoRapido()
    {
        return proAccesoRapido;
    }

    public void setProAccesoRapido(String proAccesoRapido)
    {
        this.proAccesoRapido = proAccesoRapido;
    }

    public Long getProCantidadv()
    {
        return proCantidadv;
    }

    public void setProCantidadv(Long proCantidadv)
    {
        this.proCantidadv = proCantidadv;
    }

    public Long getProVersion()
    {
        return proVersion;
    }

    public void setProVersion(Long proVersion)
    {
        this.proVersion = proVersion;
    }

    public List<Categoria> getCategoriaList()
    {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList)
    {
        this.categoriaList = categoriaList;
    }

    public List<Detallexorden> getDetallexordenList()
    {
        return detallexordenList;
    }

    public void setDetallexordenList(List<Detallexorden> detallexordenList)
    {
        this.detallexordenList = detallexordenList;
    }

    public Restaurante getResId()
    {
        return resId;
    }

    public void setResId(Restaurante resId)
    {
        this.resId = resId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (proId != null ? proId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Producto))
        {
            return false;
        }
        Producto other = (Producto) object;
        if((this.proId == null && other.proId != null) || (this.proId != null && !this.proId.equals(other.proId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Producto[ proId=" + proId + " ]";
    }

}
