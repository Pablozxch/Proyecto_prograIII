/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.models;

import java.io.*;
import java.math.*;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 *
 * @author jp015
 */
@Entity
@Table(name = "TBL_PRODUCTOSXORDEN" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Productosxorden.findAll" , query = "SELECT p FROM Productosxorden p") ,
              @NamedQuery(name = "Productosxorden.findByPxoId" , query = "SELECT p FROM Productosxorden p WHERE p.pxoId = :pxoId") ,
              @NamedQuery(name = "Productosxorden.findByPxoCantidad" , query = "SELECT p FROM Productosxorden p WHERE p.pxoCantidad = :pxoCantidad") ,
              @NamedQuery(name = "Productosxorden.findByPxoVersion" , query = "SELECT p FROM Productosxorden p WHERE p.pxoVersion = :pxoVersion")
          })
public class Productosxorden implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_PRODUCTOSXORDEN_PXO_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_PRODUCTOSXORDEN_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_PRODUCTOSXORDEN_PXO_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PXO_ID")
    private Long pxoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PXO_CANTIDAD")
    private Long pxoCantidad;
    @Basic(optional = false)
    @NotNull
    @Version
    @Column(name = "PXO_VERSION")
    private Long pxoVersion;
    @JoinColumn(name = "ORD_ID" , referencedColumnName = "ORD_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Ordenes ordId;
    @JoinColumn(name = "PRO_ID" , referencedColumnName = "PRO_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Productos proId;

    public Productosxorden()
    {
    }

    public Productosxorden(Long pxoId)
    {
        this.pxoId = pxoId;
    }

    public Productosxorden(Long pxoId , Long pxoCantidad )
    {
        this.pxoId = pxoId;
        this.pxoCantidad = pxoCantidad;
    }

    public Long getPxoId()
    {
        return pxoId;
    }

    public void setPxoId(Long pxoId)
    {
        this.pxoId = pxoId;
    }

    public Long getPxoCantidad()
    {
        return pxoCantidad;
    }

    public void setPxoCantidad(Long pxoCantidad)
    {
        this.pxoCantidad = pxoCantidad;
    }

    public Long getPxoVersion()
    {
        return pxoVersion;
    }

    public void setPxoVersion(Long pxoVersion)
    {
        this.pxoVersion = pxoVersion;
    }

    public Ordenes getOrdId()
    {
        return ordId;
    }

    public void setOrdId(Ordenes ordId)
    {
        this.ordId = ordId;
    }

    public Productos getProId()
    {
        return proId;
    }

    public void setProId(Productos proId)
    {
        this.proId = proId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pxoId != null ? pxoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Productosxorden))
        {
            return false;
        }
        Productosxorden other = (Productosxorden) object;
        if((this.pxoId == null && other.pxoId != null) || (this.pxoId != null && !this.pxoId.equals(other.pxoId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Productosxorden[ pxoId=" + pxoId + " ]";
    }

}
