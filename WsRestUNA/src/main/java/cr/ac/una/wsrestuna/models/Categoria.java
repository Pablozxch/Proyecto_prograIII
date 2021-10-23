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
@Table(name = "TBL_CATEGORIA" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Categoria.findAll" , query = "SELECT c FROM Categoria c") ,
              @NamedQuery(name = "Categoria.findByCatId" , query = "SELECT c FROM Categoria c WHERE c.catId = :catId") ,
              @NamedQuery(name = "Categoria.findByCatNombre" , query = "SELECT c FROM Categoria c WHERE c.catNombre = :catNombre") ,
              @NamedQuery(name = "Categoria.findByCatDetalle" , query = "SELECT c FROM Categoria c WHERE c.catDetalle = :catDetalle") ,
              @NamedQuery(name = "Categoria.findByCatVersion" , query = "SELECT c FROM Categoria c WHERE c.catVersion = :catVersion"),
              @NamedQuery(name = "Categoria.findByNombreDetalle" , query = "SELECT e FROM Categoria e WHERE UPPER(e.catNombre) like :catNombre and UPPER(e.catDetalle) like :catDetalle" , hints = @QueryHint(name = "eclipselink.refresh" , value = "true")),

          })
public class Categoria implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_CATEGORIA_CAT_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_CATEGORIA_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_CATEGORIA_CAT_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAT_ID")
    private Long catId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 50)
    @Column(name = "CAT_NOMBRE")
    private String catNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 50)
    @Column(name = "CAT_DETALLE")
    private String catDetalle;
    @Basic(optional = false)
    @NotNull
    @Version
    @Column(name = "CAT_VERSION")
    private Long catVersion;
    @JoinTable(name = "TBL_CATEGORIAXPRODUCTO" , joinColumns =
    {
        @JoinColumn(name = "CAT_ID" , referencedColumnName = "CAT_ID")
    } , inverseJoinColumns =
    {
        @JoinColumn(name = "PROD_ID" , referencedColumnName = "PRO_ID")
    })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Producto> productoList;

    public Categoria()
    {
    }

    public Categoria(Long catId)
    {
        this.catId = catId;
    }

    public Categoria(Long catId , String catNombre , String catDetalle)
    {
        this.catId = catId;
        this.catNombre = catNombre;
        this.catDetalle = catDetalle;
    }

    public Categoria(CategoriaDto categoriaDto)
    {
        this.catId = categoriaDto.getId();
        actualizarCategoria(categoriaDto);
    }

    public void actualizarCategoria(CategoriaDto categoriaDto)
    {
        this.catNombre = categoriaDto.getNombre();
        this.catDetalle = categoriaDto.getDetalle();
    }

    public Long getCatId()
    {
        return catId;
    }

    public void setCatId(Long catId)
    {
        this.catId = catId;
    }

    public String getCatNombre()
    {
        return catNombre;
    }

    public void setCatNombre(String catNombre)
    {
        this.catNombre = catNombre;
    }

    public String getCatDetalle()
    {
        return catDetalle;
    }

    public void setCatDetalle(String catDetalle)
    {
        this.catDetalle = catDetalle;
    }

    public Long getCatVersion()
    {
        return catVersion;
    }

    public void setCatVersion(Long catVersion)
    {
        this.catVersion = catVersion;
    }

    public List<Producto> getProductoList()
    {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList)
    {
        this.productoList = productoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (catId != null ? catId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Categoria))
        {
            return false;
        }
        Categoria other = (Categoria) object;
        if((this.catId == null && other.catId != null) || (this.catId != null && !this.catId.equals(other.catId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Categoria[ catId=" + catId + " ]";
    }

}
