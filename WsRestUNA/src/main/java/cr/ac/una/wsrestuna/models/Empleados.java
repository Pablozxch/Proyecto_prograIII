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
@Table(name = "TBL_EMPLEADOS" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Empleados.findAll" , query = "SELECT e FROM Empleados e") ,
              @NamedQuery(name = "Empleados.findByEmpId" , query = "SELECT e FROM Empleados e WHERE e.empId = :empId") ,
              @NamedQuery(name = "Empleados.findByEmpNombre" , query = "SELECT e FROM Empleados e WHERE e.empNombre = :empNombre") ,
              @NamedQuery(name = "Empleados.findByEmpUsuario" , query = "SELECT e FROM Empleados e WHERE e.empUsuario = :empUsuario") ,
              @NamedQuery(name = "Empleados.findByEmpContra" , query = "SELECT e FROM Empleados e WHERE e.empContra = :empContra") ,
              @NamedQuery(name = "Empleados.findByEmpApelllido" , query = "SELECT e FROM Empleados e WHERE e.empApelllido = :empApelllido") ,
              @NamedQuery(name = "Empleados.findByEmpVersion" , query = "SELECT e FROM Empleados e WHERE e.empVersion = :empVersion")
          })
public class Empleados implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_EMPLEADOS_EMP_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_EMPLEADOS_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_EMPLEADOS_EMP_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMP_ID")
    private Long empId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 50)
    @Column(name = "EMP_NOMBRE")
    private String empNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 50)
    @Column(name = "EMP_USUARIO")
    private String empUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 50)
    @Column(name = "EMP_CONTRA")
    private String empContra;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "EMP_FOTO")
    private byte[] empFoto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 50)
    @Column(name = "EMP_APELLLIDO")
    private String empApelllido;
    @Basic(optional = false)
    @NotNull
    @Version
    @Column(name = "EMP__VERSION")
    private Long empVersion;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "empId" , fetch = FetchType.LAZY)
    private List<Ordenes> ordenesList;
    @JoinColumn(name = "ROL_ID" , referencedColumnName = "ROL_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Rol rolId;
    @JoinColumn(name = "RES_ID" , referencedColumnName = "RES_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Restaurantes resId;

    public Empleados()
    {
    }

    public Empleados(Long empId)
    {
        this.empId = empId;
    }

    public Empleados(Long empId , String empNombre , String empUsuario , String empContra , byte[] empFoto , String empApelllido)
    {
        this.empId = empId;
        this.empNombre = empNombre;
        this.empUsuario = empUsuario;
        this.empContra = empContra;
        this.empFoto = empFoto;
        this.empApelllido = empApelllido;
    }

    public Empleados(EmpleadosDto empDto)
    {
        this.empId = empDto.getId();
        actualizarEmpleados(empDto);
    }

    public void actualizarEmpleados(EmpleadosDto empDto)
    {
        this.empNombre = empDto.getNombre();
        this.empUsuario = empDto.getUsuario();
        this.empContra = empDto.getContra();
        this.empFoto = empDto.getFoto();
        this.empApelllido = empDto.getApellido();
        this.rolId=new Rol(empDto.getRol());
    }

    public Long getEmpId()
    {
        return empId;
    }

    public void setEmpId(Long empId)
    {
        this.empId = empId;
    }

    public String getEmpNombre()
    {
        return empNombre;
    }

    public void setEmpNombre(String empNombre)
    {
        this.empNombre = empNombre;
    }

    public String getEmpUsuario()
    {
        return empUsuario;
    }

    public void setEmpUsuario(String empUsuario)
    {
        this.empUsuario = empUsuario;
    }

    public String getEmpContra()
    {
        return empContra;
    }

    public void setEmpContra(String empContra)
    {
        this.empContra = empContra;
    }

    public byte[] getEmpFoto()
    {
        return empFoto;
    }

    public void setEmpFoto(byte[] empFoto)
    {
        this.empFoto = empFoto;
    }

    public String getEmpApelllido()
    {
        return empApelllido;
    }

    public void setEmpApelllido(String empApelllido)
    {
        this.empApelllido = empApelllido;
    }

    public Long getEmpVersion()
    {
        return empVersion;
    }

    public void setEmpVersion(Long empVersion)
    {
        this.empVersion = empVersion;
    }

    public List<Ordenes> getOrdenesList()
    {
        return ordenesList;
    }

    public void setOrdenesList(List<Ordenes> ordenesList)
    {
        this.ordenesList = ordenesList;
    }

    public Rol getRolId()
    {
        return rolId;
    }

    public void setRolId(Rol rolId)
    {
        this.rolId = rolId;
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
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Empleados))
        {
            return false;
        }
        Empleados other = (Empleados) object;
        if((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Empleados[ empId=" + empId + " ]";
    }

}
