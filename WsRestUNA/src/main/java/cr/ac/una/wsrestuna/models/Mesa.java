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
@Table(name = "TBL_MESAS" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Mesa.findAll" , query = "SELECT m FROM Mesa m") ,
              @NamedQuery(name = "Mesa.findByMesaId" , query = "SELECT m FROM Mesa m WHERE m.mesaId = :mesaId") ,
              @NamedQuery(name = "Mesa.findByMesaNombre" , query = "SELECT m FROM Mesa m WHERE m.mesaNombre = :mesaNombre") ,
              @NamedQuery(name = "Mesa.findByMesaEstado" , query = "SELECT m FROM Mesa m WHERE m.mesaEstado = :mesaEstado") ,
              @NamedQuery(name = "Mesa.findByMesaPosx" , query = "SELECT m FROM Mesa m WHERE m.mesaPosx = :mesaPosx") ,
              @NamedQuery(name = "Mesa.findByMesaPosy" , query = "SELECT m FROM Mesa m WHERE m.mesaPosy = :mesaPosy") ,
              @NamedQuery(name = "Mesa.findByMesaVersion" , query = "SELECT m FROM Mesa m WHERE m.mesaVersion = :mesaVersion")
          })
public class Mesa implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_MESAS_MESA_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_MESAS_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_MESAS_MESA_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "MESA_ID")
    private Long mesaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 50)
    @Column(name = "MESA_NOMBRE")
    private String mesaNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 1)
    @Column(name = "MESA_ESTADO")
    private String mesaEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MESA_POSX")
    private Long mesaPosx;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MESA_POSY")
    private Long mesaPosy;
    @Basic(optional = false)
    @NotNull
    @Version
    @Column(name = "MESA_VERSION")
    private Long mesaVersion;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "mesaId" , fetch = FetchType.LAZY)
    private List<Ordenes> ordenesList;
    @JoinColumn(name = "SAL_ID" , referencedColumnName = "SAL_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Salones salId;

    public Mesa()
    {
    }

    public Mesa(Long mesaId)
    {
        this.mesaId = mesaId;
    }

    public Mesa(Long mesaId , String mesaNombre , String mesaEstado , Long mesaPosx , Long mesaPosy )
    {
        this.mesaId = mesaId;
        this.mesaNombre = mesaNombre;
        this.mesaEstado = mesaEstado;
        this.mesaPosx = mesaPosx;
        this.mesaPosy = mesaPosy;
    }

    public Long getMesaId()
    {
        return mesaId;
    }

    public void setMesaId(Long mesaId)
    {
        this.mesaId = mesaId;
    }

    public String getMesaNombre()
    {
        return mesaNombre;
    }

    public void setMesaNombre(String mesaNombre)
    {
        this.mesaNombre = mesaNombre;
    }

    public String getMesaEstado()
    {
        return mesaEstado;
    }

    public void setMesaEstado(String mesaEstado)
    {
        this.mesaEstado = mesaEstado;
    }

    public Long getMesaPosx()
    {
        return mesaPosx;
    }

    public void setMesaPosx(Long mesaPosx)
    {
        this.mesaPosx = mesaPosx;
    }

    public Long getMesaPosy()
    {
        return mesaPosy;
    }

    public void setMesaPosy(Long mesaPosy)
    {
        this.mesaPosy = mesaPosy;
    }

    public Long getMesaVersion()
    {
        return mesaVersion;
    }

    public void setMesaVersion(Long mesaVersion)
    {
        this.mesaVersion = mesaVersion;
    }

    public List<Ordenes> getOrdenesList()
    {
        return ordenesList;
    }

    public void setOrdenesList(List<Ordenes> ordenesList)
    {
        this.ordenesList = ordenesList;
    }

    public Salones getSalId()
    {
        return salId;
    }

    public void setSalId(Salones salId)
    {
        this.salId = salId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (mesaId != null ? mesaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Mesa))
        {
            return false;
        }
        Mesa other = (Mesa) object;
        if((this.mesaId == null && other.mesaId != null) || (this.mesaId != null && !this.mesaId.equals(other.mesaId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Mesa[ mesaId=" + mesaId + " ]";
    }

}
