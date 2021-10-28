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
              @NamedQuery(name = "Orden.findByOrdFecha" , query = "SELECT o FROM Orden o WHERE o.ordFecha = :ordFecha") ,
              @NamedQuery(name = "Orden.findByOrdEstado" , query = "SELECT o FROM Orden o WHERE o.ordEstado = :ordEstado") ,
              @NamedQuery(name = "Orden.findByOrdVersion" , query = "SELECT o FROM Orden o WHERE o.ordVersion = :ordVersion")
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
    @Column(name = "ORD_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ordFecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 1)
    @Column(name = "ORD_ESTADO")
    private String ordEstado;
    @Basic(optional = false)
    @NotNull
    @Version
    @Column(name = "ORD_VERSION")
    private Long ordVersion;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "ordId" , fetch = FetchType.LAZY)
    private List<Detallexorden> detallexordenList;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "ordId" , fetch = FetchType.LAZY)
    private List<Factura> facturaList;
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

    public Orden(Long ordId , Date ordFecha , String ordEstado)
    {
        this.ordId = ordId;
        this.ordFecha = ordFecha;
        this.ordEstado = ordEstado;
    }

    public Orden(OrdenDto ordenDto)
    {
        this.ordId = ordenDto.getId();
        actualizarOrden(ordenDto);
    }

    public void actualizarOrden(OrdenDto ordenDto)
    {
        this.ordFecha = ordenDto.getFecha();
        this.ordEstado = ordenDto.getEstado();
        this.mesaId = new Mesa(ordenDto.getMesaDto());
        this.empId = new Empleado(ordenDto.getEmpleadoDto());

    }

    public Long getOrdId()
    {
        return ordId;
    }

    public void setOrdId(Long ordId)
    {
        this.ordId = ordId;
    }

    public Date getOrdFecha()
    {
        return ordFecha;
    }

    public void setOrdFecha(Date ordFecha)
    {
        this.ordFecha = ordFecha;
    }

    public String getOrdEstado()
    {
        return ordEstado;
    }

    public void setOrdEstado(String ordEstado)
    {
        this.ordEstado = ordEstado;
    }

    public Long getOrdVersion()
    {
        return ordVersion;
    }

    public void setOrdVersion(Long ordVersion)
    {
        this.ordVersion = ordVersion;
    }

    public List<Detallexorden> getDetallexordenList()
    {
        return detallexordenList;
    }

    public void setDetallexordenList(List<Detallexorden> detallexordenList)
    {
        this.detallexordenList = detallexordenList;
    }

    public List<Factura> getFacturaList()
    {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList)
    {
        this.facturaList = facturaList;
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
