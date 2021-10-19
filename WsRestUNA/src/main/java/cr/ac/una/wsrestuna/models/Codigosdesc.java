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
@Table(name = "TBL_CODIGOSDESC" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Codigosdesc.findAll" , query = "SELECT c FROM Codigosdesc c") ,
              @NamedQuery(name = "Codigosdesc.findByCodId" , query = "SELECT c FROM Codigosdesc c WHERE c.codId = :codId") ,
              @NamedQuery(name = "Codigosdesc.findByCodNombre" , query = "SELECT c FROM Codigosdesc c WHERE c.codNombre = :codNombre") ,
              @NamedQuery(name = "Codigosdesc.findByCodCant" , query = "SELECT c FROM Codigosdesc c WHERE c.codCant = :codCant") ,
              @NamedQuery(name = "Codigosdesc.findByCodVersion" , query = "SELECT c FROM Codigosdesc c WHERE c.codVersion = :codVersion") ,
              @NamedQuery(name = "Codigosdesc.findByCodUrl" , query = "SELECT c FROM Codigosdesc c WHERE c.codUrl = :codUrl")
          })
public class Codigosdesc implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_CODIGOSDESC_COD_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_CODIGOSDESC_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_CODIGOSDESC_COD_ID_GENERATOR")
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
    @Column(name = "COD_CANT")
    private Long codCant;
    @Basic(optional = false)
    @NotNull
    @Version
    @Column(name = "COD_VERSION")
    private Long codVersion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 50)
    @Column(name = "COD_URL")
    private String codUrl;
    @JoinColumn(name = "RES_ID" , referencedColumnName = "RES_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurantes resId;

    public Codigosdesc()
    {
    }

    public Codigosdesc(Long codId)
    {
        this.codId = codId;
    }

    public Codigosdesc(Long codId , String codNombre , Long codCant , String codUrl)
    {
        this.codId = codId;
        this.codNombre = codNombre;
        this.codCant = codCant;
        this.codUrl = codUrl;
    }
    public Codigosdesc(CodigosdescDto codDto)
    {
        this.codId=codDto.getId();
        this.codNombre=codDto.getNombre();
        this.codCant=codDto.getCantidad();
        this.codUrl=codDto.getUrl();
        this.resId=new Restaurantes(codDto.getResid());
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

    public String getCodUrl()
    {
        return codUrl;
    }

    public void setCodUrl(String codUrl)
    {
        this.codUrl = codUrl;
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
        hash += (codId != null ? codId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Codigosdesc))
        {
            return false;
        }
        Codigosdesc other = (Codigosdesc) object;
        if((this.codId == null && other.codId != null) || (this.codId != null && !this.codId.equals(other.codId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Codigosdesc[ codId=" + codId + " ]";
    }

}
