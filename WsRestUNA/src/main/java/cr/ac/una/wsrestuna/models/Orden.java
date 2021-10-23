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
@Table(name = "TBL_ORDEN" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Orden.findAll" , query = "SELECT o FROM Orden o") ,
              @NamedQuery(name = "Orden.findByOrdId" , query = "SELECT o FROM Orden o WHERE o.ordId = :ordId") ,
              @NamedQuery(name = "Orden.findByFacTotal" , query = "SELECT o FROM Orden o WHERE o.facTotal = :facTotal") ,
              @NamedQuery(name = "Orden.findByFacFecha" , query = "SELECT o FROM Orden o WHERE o.facFecha = :facFecha") ,
              @NamedQuery(name = "Orden.findByFacDesc" , query = "SELECT o FROM Orden o WHERE o.facDesc = :facDesc") ,
              @NamedQuery(name = "Orden.findByFacVersion" , query = "SELECT o FROM Orden o WHERE o.facVersion = :facVersion")
          })
public class Orden implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_ORDEN_ORD_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_ORDEN_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_ORDEN_ORD_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORD_ID")
    private Long ordId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_TOTAL")
    private Long facTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date facFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_DESC")
    private Long facDesc;
    @Basic(optional = false)
    @NotNull
    @Version
    @Column(name = "FAC_VERSION")
    private Long facVersion;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "ordId" , fetch = FetchType.LAZY)
    private List<Productosxorden> productosxordenList;
    @JoinColumn(name = "COD_ID" , referencedColumnName = "COD_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Codigodesc codId;
    @JoinColumn(name = "EMP_ID" , referencedColumnName = "EMP_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Empleado empId;
    @JoinColumn(name = "MESA_ID" , referencedColumnName = "MESA_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Mesa mesaId;

    public Orden()
    {
    }

    public Orden(Long ordId)
    {
        this.ordId = ordId;
    }

    public Orden(Long ordId , Long facTotal , Date facFecha , Long facDesc)
    {
        this.ordId = ordId;
        this.facTotal = facTotal;
        this.facFecha = facFecha;
        this.facDesc = facDesc;
    }

    public Orden(OrdenDto ordenDto)
    {
        this.ordId = ordenDto.getId();
        actualizarOrden(ordenDto);
    }

    public void actualizarOrden(OrdenDto ordenDto)
    {
        this.facTotal = ordenDto.getFacTotal();
        this.facFecha = ordenDto.getFecha();
        this.facTotal = ordenDto.getDesc();
        this.empId = new Empleado(ordenDto.getEmp());
        this.mesaId = new Mesa(ordenDto.getMesa());
    }

    public Long getOrdId()
    {
        return ordId;
    }

    public void setOrdId(Long ordId)
    {
        this.ordId = ordId;
    }

    public Long getFacTotal()
    {
        return facTotal;
    }

    public void setFacTotal(Long facTotal)
    {
        this.facTotal = facTotal;
    }

    public Date getFacFecha()
    {
        return facFecha;
    }

    public void setFacFecha(Date facFecha)
    {
        this.facFecha = facFecha;
    }

    public Long getFacDesc()
    {
        return facDesc;
    }

    public void setFacDesc(Long facDesc)
    {
        this.facDesc = facDesc;
    }

    public Long getFacVersion()
    {
        return facVersion;
    }

    public void setFacVersion(Long facVersion)
    {
        this.facVersion = facVersion;
    }

    public List<Productosxorden> getProductosxordenList()
    {
        return productosxordenList;
    }

    public void setProductosxordenList(List<Productosxorden> productosxordenList)
    {
        this.productosxordenList = productosxordenList;
    }

    public Codigodesc getCodId()
    {
        return codId;
    }

    public void setCodId(Codigodesc codId)
    {
        this.codId = codId;
    }

    public Empleado getEmpId()
    {
        return empId;
    }

    public void setEmpId(Empleado empId)
    {
        this.empId = empId;
    }

    public Mesa getMesaId()
    {
        return mesaId;
    }

    public void setMesaId(Mesa mesaId)
    {
        this.mesaId = mesaId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (ordId != null ? ordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Orden))
        {
            return false;
        }
        Orden other = (Orden) object;
        if((this.ordId == null && other.ordId != null) || (this.ordId != null && !this.ordId.equals(other.ordId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Orden[ ordId=" + ordId + " ]";
    }

}
