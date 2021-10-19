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
@Table(name = "TBL_ORDENES" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Ordenes.findAll" , query = "SELECT o FROM Ordenes o") ,
              @NamedQuery(name = "Ordenes.findByOrdId" , query = "SELECT o FROM Ordenes o WHERE o.ordId = :ordId") ,
              @NamedQuery(name = "Ordenes.findByFacTotal" , query = "SELECT o FROM Ordenes o WHERE o.facTotal = :facTotal") ,
              @NamedQuery(name = "Ordenes.findByFacFecha" , query = "SELECT o FROM Ordenes o WHERE o.facFecha = :facFecha") ,
              @NamedQuery(name = "Ordenes.findByFacVersion" , query = "SELECT o FROM Ordenes o WHERE o.facVersion = :facVersion") ,
              @NamedQuery(name = "Ordenes.findByFacDesc" , query = "SELECT o FROM Ordenes o WHERE o.facDesc = :facDesc")
          })
public class Ordenes implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_ORDENES_ORD_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_ORDENES_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_ORDENES_ORD_ID_GENERATOR")
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
    @Version
    @Column(name = "FAC_VERSION")
    private Long facVersion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_DESC")
    private Long facDesc;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "ordId" , fetch = FetchType.LAZY)
    private List<Productosxorden> productosxordenList;
    @JoinColumn(name = "EMP_ID" , referencedColumnName = "EMP_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Empleados empId;
    @JoinColumn(name = "MESA_ID" , referencedColumnName = "MESA_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Mesa mesaId;

    public Ordenes()
    {
    }

    public Ordenes(Long ordId)
    {
        this.ordId = ordId;
    }

    public Ordenes(Long ordId , Long facTotal , Date facFecha , Long facVersion)
    {
        this.ordId = ordId;
        this.facTotal = facTotal;
        this.facFecha = facFecha;
        this.facVersion = facVersion;
    }

    public Ordenes(OrdenesDto ordenDto)
    {
        this.ordId = ordenDto.getId();
        actualizarOrdenes(ordenDto);
    }

    public void actualizarOrdenes(OrdenesDto ordenDto)
    {
        this.facTotal = ordenDto.getFacTotal();
        this.facFecha = ordenDto.getFecha();
        this.facTotal = ordenDto.getDesc();
        this.empId = new Empleados(ordenDto.getEmp());
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

    public Long getFacVersion()
    {
        return facVersion;
    }

    public void setFacVersion(Long facVersion)
    {
        this.facVersion = facVersion;
    }

    public Long getFacDesc()
    {
        return facDesc;
    }

    public void setFacDesc(Long facDesc)
    {
        this.facDesc = facDesc;
    }

    public List<Productosxorden> getProductosxordenList()
    {
        return productosxordenList;
    }

    public void setProductosxordenList(List<Productosxorden> productosxordenList)
    {
        this.productosxordenList = productosxordenList;
    }

    public Empleados getEmpId()
    {
        return empId;
    }

    public void setEmpId(Empleados empId)
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
        if(!(object instanceof Ordenes))
        {
            return false;
        }
        Ordenes other = (Ordenes) object;
        if((this.ordId == null && other.ordId != null) || (this.ordId != null && !this.ordId.equals(other.ordId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Ordenes[ ordId=" + ordId + " ]";
    }

}
