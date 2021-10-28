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
@Table(name = "TBL_CODIGODESC" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Codigodesc.findAll" , query = "SELECT c FROM Codigodesc c") ,
              @NamedQuery(name = "Codigodesc.findByCodId" , query = "SELECT c FROM Codigodesc c WHERE c.codId = :codId") ,
              @NamedQuery(name = "Codigodesc.findByCodNombre" , query = "SELECT c FROM Codigodesc c WHERE c.codNombre = :codNombre") ,
              @NamedQuery(name = "Codigodesc.findByCodDesc" , query = "SELECT c FROM Codigodesc c WHERE c.codDesc = :codDesc") ,
              @NamedQuery(name = "Codigodesc.findByCodUrl" , query = "SELECT c FROM Codigodesc c WHERE c.codUrl = :codUrl") ,
              @NamedQuery(name = "Codigodesc.findByCodCant" , query = "SELECT c FROM Codigodesc c WHERE c.codCant = :codCant") ,
              @NamedQuery(name = "Codigodesc.findByCodVersion" , query = "SELECT c FROM Codigodesc c WHERE c.codVersion = :codVersion")
          })
public class Codigodesc implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_CODIGODESC_COD_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_CODIGODESC_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_CODIGODESC_COD_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ID")
    private Long codId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 30)
    @Column(name = "COD_NOMBRE")
    private String codNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_DESC")
    private Long codDesc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 50)
    @Column(name = "COD_URL")
    private String codUrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CANT")
    private Long codCant;
    @Basic(optional = false)
    @NotNull
    @Version
    @Column(name = "COD_VERSION")
    private Long codVersion;
    @JoinColumn(name = "RES_ID" , referencedColumnName = "RES_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurante resId;
    @OneToMany(mappedBy = "codId" , fetch = FetchType.LAZY)
    private List<Factura> facturaList;

    public Codigodesc()
    {
    }

    public Codigodesc(Long codId)
    {
        this.codId = codId;
    }

    public Codigodesc(Long codId , String codNombre , Long codDesc , String codUrl , Long codCant)
    {
        this.codId = codId;
        this.codNombre = codNombre;
        this.codDesc = codDesc;
        this.codUrl = codUrl;
        this.codCant = codCant;
    }

    public Codigodesc(CodigodescDto codigodescDto)
    {
        this.codId = codigodescDto.getId();
        actualizarCodigodesc(codigodescDto);
    }

    public void actualizarCodigodesc(CodigodescDto codigodescDto)
    {
        this.codNombre = codigodescDto.getNombre();
        this.codDesc = codigodescDto.getDesc();
        this.codUrl = codigodescDto.getUrl();
        this.codCant = codigodescDto.getCantidadusar();
        this.resId = new Restaurante(codigodescDto.getRestaurante());
    }

    public Long getCodId()
    {
        return codId;
    }

    public void setCodId(Long codId)
    {
        this.codId = codId;
    }

    public String getCodNombre()
    {
        return codNombre;
    }

    public void setCodNombre(String codNombre)
    {
        this.codNombre = codNombre;
    }

    public Long getCodDesc()
    {
        return codDesc;
    }

    public void setCodDesc(Long codDesc)
    {
        this.codDesc = codDesc;
    }

    public String getCodUrl()
    {
        return codUrl;
    }

    public void setCodUrl(String codUrl)
    {
        this.codUrl = codUrl;
    }

    public Long getCodCant()
    {
        return codCant;
    }

    public void setCodCant(Long codCant)
    {
        this.codCant = codCant;
    }

    public Long getCodVersion()
    {
        return codVersion;
    }

    public void setCodVersion(Long codVersion)
    {
        this.codVersion = codVersion;
    }

    public Restaurante getResId()
    {
        return resId;
    }

    public void setResId(Restaurante resId)
    {
        this.resId = resId;
    }

    public List<Factura> getFacturaList()
    {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList)
    {
        this.facturaList = facturaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (codId != null ? codId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Codigodesc))
        {
            return false;
        }
        Codigodesc other = (Codigodesc) object;
        if((this.codId == null && other.codId != null) || (this.codId != null && !this.codId.equals(other.codId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Codigodesc[ codId=" + codId + " ]";
    }

}
