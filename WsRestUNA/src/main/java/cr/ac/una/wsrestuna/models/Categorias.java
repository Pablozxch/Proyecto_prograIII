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
@Table(name = "TBL_CATEGORIAS" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Categorias.findAll" , query = "SELECT c FROM Categorias c") ,
              @NamedQuery(name = "Categorias.findByCatId" , query = "SELECT c FROM Categorias c WHERE c.catId = :catId") ,
              @NamedQuery(name = "Categorias.findByCatNombre" , query = "SELECT c FROM Categorias c WHERE c.catNombre = :catNombre") ,
              @NamedQuery(name = "Categorias.findByCatDescripcion" , query = "SELECT c FROM Categorias c WHERE c.catDescripcion = :catDescripcion") ,
              @NamedQuery(name = "Categorias.findByCatVersion" , query = "SELECT c FROM Categorias c WHERE c.catVersion = :catVersion")
          })
public class Categorias implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "Entity_CAT_ID_GENERATOR" , sequenceName = "RESTUNA.Entity_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "Entity_CAT_ID_GENERATOR")
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
    @Column(name = "CAT_DESCRIPCION")
    private String catDescripcion;
    @Basic(optional = false)
    @NotNull
    @Version
    @Column(name = "CAT_VERSION")
    private Long catVersion;
    @JoinTable(name = "TBL_CATEGORIASXPRODUCTOS" , joinColumns =
    {
        @JoinColumn(name = "CAT_ID" , referencedColumnName = "CAT_ID")
    } , inverseJoinColumns =
    {
        @JoinColumn(name = "PROD_ID" , referencedColumnName = "PRO_ID")
    })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Productos> productosList;

    public Categorias()
    {
    }

    public Categorias(Long catId)
    {
        this.catId = catId;
    }

    public Categorias(Long catId , String catNombre , String catDescripcion)
    {
        this.catId = catId;
        this.catNombre = catNombre;
        this.catDescripcion = catDescripcion;
    }
    public Categorias(CategoriasDto catDto)
    {
        this.catId=catDto.getId();
        actualizarCategorias(catDto);
    }
    public void actualizarCategorias(CategoriasDto catDto)
    {
        this.catNombre=catDto.getNombre();
        this.catDescripcion=catDto.getDetalle();
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

    public String getCatDescripcion()
    {
        return catDescripcion;
    }

    public void setCatDescripcion(String catDescripcion)
    {
        this.catDescripcion = catDescripcion;
    }

    public Long getCatVersion()
    {
        return catVersion;
    }

    public void setCatVersion(Long catVersion)
    {
        this.catVersion = catVersion;
    }

    public List<Productos> getProductosList()
    {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList)
    {
        this.productosList = productosList;
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
        if(!(object instanceof Categorias))
        {
            return false;
        }
        Categorias other = (Categorias) object;
        if((this.catId == null && other.catId != null) || (this.catId != null && !this.catId.equals(other.catId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Categorias[ catId=" + catId + " ]";
    }

}
