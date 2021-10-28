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
@Table(name = "TBL_ROL" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Rol.findAll" , query = "SELECT r FROM Rol r") ,
              @NamedQuery(name = "Rol.findByRolId" , query = "SELECT r FROM Rol r WHERE r.rolId = :rolId") ,
              @NamedQuery(name = "Rol.findByRolNombre" , query = "SELECT r FROM Rol r WHERE r.rolNombre = :rolNombre") ,
              @NamedQuery(name = "Rol.findByRolVersion" , query = "SELECT r FROM Rol r WHERE r.rolVersion = :rolVersion")
          })
public class Rol implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_ROL_ROL_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_ROL_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_ROL_ROL_ID1_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROL_ID")
    private Long rolId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 30)
    @Column(name = "ROL_NOMBRE")
    private String rolNombre;
    @Basic(optional = false)
    @NotNull
    @Version
    @Column(name = "ROL_VERSION")
    private Long rolVersion;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "rolId" , fetch = FetchType.LAZY)
    private List<Empleado> empleadoList;

    public Rol()
    {
    }

    public Rol(Long rolId)
    {
        this.rolId = rolId;
    }

    public Rol(Long rolId , String rolNombre)
    {
        this.rolId = rolId;
        this.rolNombre = rolNombre;
    }

    public Rol(RolDto rolDto)
    {
        this.rolId=rolDto.getId();
        actualizarRol(rolDto);
    }

    public void actualizarRol(RolDto rolDto)
    {
        this.rolNombre = rolDto.getNombre();
    }

    public Long getRolId()
    {
        return rolId;
    }

    public void setRolId(Long rolId)
    {
        this.rolId = rolId;
    }

    public String getRolNombre()
    {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre)
    {
        this.rolNombre = rolNombre;
    }

    public Long getRolVersion()
    {
        return rolVersion;
    }

    public void setRolVersion(Long rolVersion)
    {
        this.rolVersion = rolVersion;
    }

    public List<Empleado> getEmpleadoList()
    {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList)
    {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (rolId != null ? rolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Rol))
        {
            return false;
        }
        Rol other = (Rol) object;
        if((this.rolId == null && other.rolId != null) || (this.rolId != null && !this.rolId.equals(other.rolId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Rol[ rolId=" + rolId + " ]";
    }

}
