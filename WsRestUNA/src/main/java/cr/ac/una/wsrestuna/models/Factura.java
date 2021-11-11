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
@Table(name = "TBL_FACTURA" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Factura.findAll" , query = "SELECT f FROM Factura f") ,
              @NamedQuery(name = "Factura.findByFacId" , query = "SELECT f FROM Factura f WHERE f.facId = :facId") ,
              @NamedQuery(name = "Factura.findByFacDesc" , query = "SELECT f FROM Factura f WHERE f.facDesc = :facDesc") ,
              @NamedQuery(name = "Factura.findByFacEfectivotarjeta" , query = "SELECT f FROM Factura f WHERE f.facEfectivotarjeta = :facEfectivotarjeta") ,
              @NamedQuery(name = "Factura.findByFacSubtotal" , query = "SELECT f FROM Factura f WHERE f.facSubtotal = :facSubtotal") ,
              @NamedQuery(name = "Factura.findByFacTotal" , query = "SELECT f FROM Factura f WHERE f.facTotal = :facTotal") ,
              @NamedQuery(name = "Factura.findByFacMontocdesc" , query = "SELECT f FROM Factura f WHERE f.facMontocdesc = :facMontocdesc") ,
              @NamedQuery(name = "Factura.findByFacVersion" , query = "SELECT f FROM Factura f WHERE f.facVersion = :facVersion") ,
              @NamedQuery(name = "Factura.findlast" , query = "SELECT max(c.facId) FROM Factura c ")
          })
public class Factura implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_FACTURA_FAC_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_FACTURA_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_FACTURA_FAC_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "FAC_ID")
    private Long facId;
    @Basic(optional = false)
    @Column(name = "FAC_DESC")
    private Long facDesc;
    @Basic(optional = false)
    @Size(min = 1 , max = 1)
    @Column(name = "FAC_EFECTIVOTARJETA")
    private String facEfectivotarjeta;
    @Basic(optional = false)
    @Column(name = "FAC_SUBTOTAL")
    private Long facSubtotal;
    @Basic(optional = false)
    @Column(name = "FAC_TOTAL")
    private Long facTotal;
    @Basic(optional = false)
    @Column(name = "FAC_MONTOCDESC")
    private Long facMontocdesc;
    @Basic(optional = false)
    @Version
    @Column(name = "FAC_VERSION")
    private Long facVersion;
    @JoinColumn(name = "CCAJ_ID" , referencedColumnName = "CCAJ_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Cierrecajas ccajId;
    @JoinColumn(name = "COD_ID" , referencedColumnName = "COD_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Codigodesc codId;
    @JoinColumn(name = "ORD_ID" , referencedColumnName = "ORD_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Orden ordId;

    public Factura()
    {
    }

    public Factura(Long facId)
    {
        this.facId = facId;
    }

    public Factura(Long facId , Long facDesc , String facEfectivotarjeta , Long facSubtotal , Long facTotal , Long facMontocdesc)
    {
        this.facId = facId;
        this.facDesc = facDesc;
        this.facEfectivotarjeta = facEfectivotarjeta;
        this.facSubtotal = facSubtotal;
        this.facTotal = facTotal;
        this.facMontocdesc = facMontocdesc;
    }

    public Factura(FacturaDto facturaDto)
    {
        this.facId = facturaDto.getId();
        actualizarFactura(facturaDto);
    }

    public void actualizarFactura(FacturaDto facturaDto)
    {
        this.facDesc = facturaDto.getDescuento();
        this.facEfectivotarjeta = facturaDto.getEfetivoTarjeta();
        this.facSubtotal = facturaDto.getSubtotal();
        this.facTotal = facturaDto.getTotal();
        this.facMontocdesc = facturaDto.getMontoFinal();
        this.ordId = new Orden(facturaDto.getOrdenDto());
        this.ccajId = new Cierrecajas(facturaDto.getCierrecajasDto());

    }

    public Long getFacId()
    {
        return facId;
    }

    public void setFacId(Long facId)
    {
        this.facId = facId;
    }

    public Long getFacDesc()
    {
        return facDesc;
    }

    public void setFacDesc(Long facDesc)
    {
        this.facDesc = facDesc;
    }

    public String getFacEfectivotarjeta()
    {
        return facEfectivotarjeta;
    }

    public void setFacEfectivotarjeta(String facEfectivotarjeta)
    {
        this.facEfectivotarjeta = facEfectivotarjeta;
    }

    public Long getFacSubtotal()
    {
        return facSubtotal;
    }

    public void setFacSubtotal(Long facSubtotal)
    {
        this.facSubtotal = facSubtotal;
    }

    public Long getFacTotal()
    {
        return facTotal;
    }

    public void setFacTotal(Long facTotal)
    {
        this.facTotal = facTotal;
    }

    public Long getFacMontocdesc()
    {
        return facMontocdesc;
    }

    public void setFacMontocdesc(Long facMontocdesc)
    {
        this.facMontocdesc = facMontocdesc;
    }

    public Long getFacVersion()
    {
        return facVersion;
    }

    public void setFacVersion(Long facVersion)
    {
        this.facVersion = facVersion;
    }

    public Cierrecajas getCcajId()
    {
        return ccajId;
    }

    public void setCcajId(Cierrecajas ccajId)
    {
        this.ccajId = ccajId;
    }

    public Codigodesc getCodId()
    {
        return codId;
    }

    public void setCodId(Codigodesc codId)
    {
        this.codId = codId;
    }

    public Orden getOrdId()
    {
        return ordId;
    }

    public void setOrdId(Orden ordId)
    {
        this.ordId = ordId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (facId != null ? facId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Factura))
        {
            return false;
        }
        Factura other = (Factura) object;
        if((this.facId == null && other.facId != null) || (this.facId != null && !this.facId.equals(other.facId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Factura[ facId=" + facId + " ]";
    }

}
