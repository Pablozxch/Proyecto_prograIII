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
@Table(name = "TBL_PRODUCTOS" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Productos.findAll" , query = "SELECT p FROM Productos p") ,
              @NamedQuery(name = "Productos.findByProId" , query = "SELECT p FROM Productos p WHERE p.proId = :proId") ,
              @NamedQuery(name = "Productos.findByProNombre" , query = "SELECT p FROM Productos p WHERE p.proNombre = :proNombre") ,
              @NamedQuery(name = "Productos.findByProDetalle" , query = "SELECT p FROM Productos p WHERE p.proDetalle = :proDetalle") ,
              @NamedQuery(name = "Productos.findByProCosto" , query = "SELECT p FROM Productos p WHERE p.proCosto = :proCosto") ,
              @NamedQuery(name = "Productos.findByProCantidad" , query = "SELECT p FROM Productos p WHERE p.proCantidad = :proCantidad") ,
              @NamedQuery(name = "Productos.findByProVersion" , query = "SELECT p FROM Productos p WHERE p.proVersion = :proVersion")
          })
public class Productos implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_PRODUCTOS_PRO_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_PRODUCTOS_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_PRODUCTOS_PRO_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRO_ID")
    private Long proId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 50)
    @Column(name = "PRO_NOMBRE")
    private String proNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 50)
    @Column(name = "PRO_DETALLE")
    private String proDetalle;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "PRO_FOTO")
    private byte[] proFoto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRO_COSTO")
    private Long proCosto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRO_CANTIDAD")
    private Long proCantidad;
    @Basic(optional = false)
    @NotNull
    @Version
    @Column(name = "PRO_VERSION")
    private Long proVersion;
    @ManyToMany(mappedBy = "productosList" , fetch = FetchType.LAZY)
    private List<Categorias> categoriasList;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "proId" , fetch = FetchType.LAZY)
    private List<Productosxorden> productosxordenList;
    @JoinColumn(name = "RES_ID" , referencedColumnName = "RES_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Restaurantes resId;

    public Productos()
    {
    }

    public Productos(Long proId)
    {
        this.proId = proId;
    }

    public Productos(Long proId , String proNombre , String proDetalle , byte[] proFoto , Long proCosto , Long proCantidad)
    {
        this.proId = proId;
        this.proNombre = proNombre;
        this.proDetalle = proDetalle;
        this.proFoto = proFoto;
        this.proCosto = proCosto;
        this.proCantidad = proCantidad;
    }

    public Productos(ProductosDto proDto)
    {
        this.proId = proDto.getId();
        actualizarProducto(proDto);
    }

    public void actualizarProducto(ProductosDto proDto)
    {
        this.proNombre = proDto.getNombre();
        this.proDetalle = proDto.getDetalle();
        this.proFoto=proDto.getFoto();
        this.proCosto=proDto.getCosto();
        this.proCantidad=proDto.getCantidad();
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

    public Long getProVersion()
    {
        return proVersion;
    }

    public void setProVersion(Long proVersion)
    {
        this.proVersion = proVersion;
    }

    public List<Categorias> getCategoriasList()
    {
        return categoriasList;
    }

    public void setCategoriasList(List<Categorias> categoriasList)
    {
        this.categoriasList = categoriasList;
    }

    public List<Productosxorden> getProductosxordenList()
    {
        return productosxordenList;
    }

    public void setProductosxordenList(List<Productosxorden> productosxordenList)
    {
        this.productosxordenList = productosxordenList;
    }

    public Restaurantes getResId()
    {
        return resId;
    }

    public void setResId(Restaurantes resId)
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
        if(!(object instanceof Productos))
        {
            return false;
        }
        Productos other = (Productos) object;
        if((this.proId == null && other.proId != null) || (this.proId != null && !this.proId.equals(other.proId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Productos[ proId=" + proId + " ]";
    }

}
