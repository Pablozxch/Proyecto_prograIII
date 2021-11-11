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
@Table(name = "TBL_CIERRECAJAS" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Cierrecajas.findAll" , query = "SELECT c FROM Cierrecajas c") ,
              @NamedQuery(name = "Cierrecajas.findByCcajId" , query = "SELECT c FROM Cierrecajas c WHERE c.ccajId = :ccajId") ,
              @NamedQuery(name = "Cierrecajas.findByCcajMontoinicial" , query = "SELECT c FROM Cierrecajas c WHERE c.ccajMontoinicial = :ccajMontoinicial") ,
              @NamedQuery(name = "Cierrecajas.findByCcjaMontofinal" , query = "SELECT c FROM Cierrecajas c WHERE c.ccjaMontofinal = :ccjaMontofinal") ,
              @NamedQuery(name = "Cierrecajas.findByCcajMontotarjeta" , query = "SELECT c FROM Cierrecajas c WHERE c.ccajMontotarjeta = :ccajMontotarjeta") ,
              @NamedQuery(name = "Cierrecajas.findByCcajMontoefectivo" , query = "SELECT c FROM Cierrecajas c WHERE c.ccajMontoefectivo = :ccajMontoefectivo") ,
              @NamedQuery(name = "Cierrecajas.findByCcjaEstado" , query = "SELECT c FROM Cierrecajas c WHERE c.ccjaEstado = :ccjaEstado") ,
              @NamedQuery(name = "Cierrecajas.findByCcjaVersion" , query = "SELECT c FROM Cierrecajas c WHERE c.ccjaVersion = :ccjaVersion") ,
              @NamedQuery(name = "Cierrecajas.findlast" , query = "SELECT max(c.ccajId) FROM Cierrecajas c ")
          })
//SELECT * from tbl_cierrecajas c where c.ccaj_id=(SELECT max(d.ccaj_id) from tbl_cierrecajas d )
public class Cierrecajas implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_CIERRECAJAS_CCAJ_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_CIERRECAJAS_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_CIERRECAJAS_CCAJ_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "CCAJ_ID")
    private Long ccajId;
    @Basic(optional = false)
    @Column(name = "CCAJ_MONTOINICIAL")
    private Long ccajMontoinicial;
    @Column(name = "CCJA_MONTOFINAL")
    private Long ccjaMontofinal;
    @Basic(optional = false)
    @Column(name = "CCAJ_MONTOTARJETA")
    private Long ccajMontotarjeta;
    @Basic(optional = false)
    @Column(name = "CCAJ_MONTOEFECTIVO")
    private Long ccajMontoefectivo;
    @Size(max = 1)
    @Column(name = "CCJA_ESTADO")
    private String ccjaEstado;
    @Basic(optional = false)
    @Version
    @Column(name = "CCJA_VERSION")
    private Long ccjaVersion;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "ccajId" , fetch = FetchType.LAZY)
    private List<Factura> facturaList;
    @JoinColumn(name = "EMP_ID" , referencedColumnName = "EMP_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Empleado empId;

    public Cierrecajas()
    {
    }

    public Cierrecajas(Long ccajId)
    {
        this.ccajId = ccajId;
    }

    public Cierrecajas(Long ccajId , Long ccajMontoinicial , Long ccajMontotarjeta , Long ccajMontoefectivo)
    {
        this.ccajId = ccajId;
        this.ccajMontoinicial = ccajMontoinicial;
        this.ccajMontotarjeta = ccajMontotarjeta;
        this.ccajMontoefectivo = ccajMontoefectivo;
    }

    public Cierrecajas(CierrecajasDto cierrecajasDto)
    {
        this.ccajId = cierrecajasDto.getId();
        actualizarCierreCajas(cierrecajasDto);
    }

    public void actualizarCierreCajas(CierrecajasDto cierrecajasDto)
    {
        this.ccajMontoinicial = cierrecajasDto.getMontoInicial();
        this.ccjaMontofinal = cierrecajasDto.getMontoFinal();
        this.ccajMontotarjeta = cierrecajasDto.getMontoTarjeta();
        this.ccajMontoefectivo = cierrecajasDto.getMontoEfectivo();
        this.ccjaEstado = cierrecajasDto.getEstado();
        this.empId = new Empleado(cierrecajasDto.getEmpleadoDto());
    }

    public Long getCcajId()
    {
        return ccajId;
    }

    public void setCcajId(Long ccajId)
    {
        this.ccajId = ccajId;
    }

    public Long getCcajMontoinicial()
    {
        return ccajMontoinicial;
    }

    public void setCcajMontoinicial(Long ccajMontoinicial)
    {
        this.ccajMontoinicial = ccajMontoinicial;
    }

    public Long getCcjaMontofinal()
    {
        return ccjaMontofinal;
    }

    public void setCcjaMontofinal(Long ccjaMontofinal)
    {
        this.ccjaMontofinal = ccjaMontofinal;
    }

    public Long getCcajMontotarjeta()
    {
        return ccajMontotarjeta;
    }

    public void setCcajMontotarjeta(Long ccajMontotarjeta)
    {
        this.ccajMontotarjeta = ccajMontotarjeta;
    }

    public Long getCcajMontoefectivo()
    {
        return ccajMontoefectivo;
    }

    public void setCcajMontoefectivo(Long ccajMontoefectivo)
    {
        this.ccajMontoefectivo = ccajMontoefectivo;
    }

    public String getCcjaEstado()
    {
        return ccjaEstado;
    }

    public void setCcjaEstado(String ccjaEstado)
    {
        this.ccjaEstado = ccjaEstado;
    }

    public Long getCcjaVersion()
    {
        return ccjaVersion;
    }

    public void setCcjaVersion(Long ccjaVersion)
    {
        this.ccjaVersion = ccjaVersion;
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

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (ccajId != null ? ccajId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Cierrecajas))
        {
            return false;
        }
        Cierrecajas other = (Cierrecajas) object;
        if((this.ccajId == null && other.ccajId != null) || (this.ccajId != null && !this.ccajId.equals(other.ccajId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Cierrecajas[ ccajId=" + ccajId + " ]";
    }

}
