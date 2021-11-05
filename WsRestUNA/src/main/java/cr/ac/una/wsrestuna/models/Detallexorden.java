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
@Table(name = "TBL_DETALLEXORDEN" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Detallexorden.findAll" , query = "SELECT d FROM Detallexorden d") ,
              @NamedQuery(name = "Detallexorden.findByDxoId" , query = "SELECT d FROM Detallexorden d WHERE d.dxoId = :dxoId") ,
              @NamedQuery(name = "Detallexorden.findByDxoCantidad" , query = "SELECT d FROM Detallexorden d WHERE d.dxoCantidad = :dxoCantidad") ,
              @NamedQuery(name = "Detallexorden.findByDxoPrecioc" , query = "SELECT d FROM Detallexorden d WHERE d.dxoPrecioc = :dxoPrecioc") ,
              @NamedQuery(name = "Detallexorden.findByDxoVersion" , query = "SELECT d FROM Detallexorden d WHERE d.dxoVersion = :dxoVersion")
          })
public class Detallexorden implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_DETALLEXORDEN_DXO_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_DETALLEXORDEN_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_DETALLEXORDEN_DXO_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "DXO_ID")
    private Long dxoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DXO_CANTIDAD")
    private Long dxoCantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DXO_PRECIOC")
    private Long dxoPrecioc;
    @Basic(optional = false)
    @NotNull
    @Version
    @Column(name = "DXO_VERSION")
    private Long dxoVersion;
    @JoinColumn(name = "ORD_ID" , referencedColumnName = "ORD_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Orden ordId;
    @JoinColumn(name = "PRO_ID" , referencedColumnName = "PRO_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Producto proId;

    public Detallexorden()
    {
    }

    public Detallexorden(Long dxoId)
    {
        this.dxoId = dxoId;
    }

    public Detallexorden(Long dxoId , Long dxoCantidad , Long dxoPrecioc)
    {
        this.dxoId = dxoId;
        this.dxoCantidad = dxoCantidad;
        this.dxoPrecioc = dxoPrecioc;
    }

    public Detallexorden(DetallexordenDto detallexordenDto)
    {
        this.dxoId = detallexordenDto.getId();
        actualizarDetallexorden(detallexordenDto);
    }

    public void actualizarDetallexorden(DetallexordenDto detallexordenDto)
    {
        this.dxoCantidad = detallexordenDto.getCantidad();
        this.dxoPrecioc = detallexordenDto.getPrecio();
        this.proId = new Producto(detallexordenDto.getProductoDto());
    }

    public Long getDxoId()
    {
        return dxoId;
    }

    public void setDxoId(Long dxoId)
    {
        this.dxoId = dxoId;
    }

    public Long getDxoCantidad()
    {
        return dxoCantidad;
    }

    public void setDxoCantidad(Long dxoCantidad)
    {
        this.dxoCantidad = dxoCantidad;
    }

    public Long getDxoPrecioc()
    {
        return dxoPrecioc;
    }

    public void setDxoPrecioc(Long dxoPrecioc)
    {
        this.dxoPrecioc = dxoPrecioc;
    }

    public Long getDxoVersion()
    {
        return dxoVersion;
    }

    public void setDxoVersion(Long dxoVersion)
    {
        this.dxoVersion = dxoVersion;
    }

    public Orden getOrdId()
    {
        return ordId;
    }

    public void setOrdId(Orden ordId)
    {
        this.ordId = ordId;
    }

    public Producto getProId()
    {
        return proId;
    }

    public void setProId(Producto proId)
    {
        this.proId = proId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (dxoId != null ? dxoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Detallexorden))
        {
            return false;
        }
        Detallexorden other = (Detallexorden) object;
        if((this.dxoId == null && other.dxoId != null) || (this.dxoId != null && !this.dxoId.equals(other.dxoId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Detallexorden[ dxoId=" + dxoId + " ]";
    }

}
