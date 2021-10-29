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
@Table(name = "TBL_RESTAURANTE" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Restaurante.findAll" , query = "SELECT r FROM Restaurante r") ,
              @NamedQuery(name = "Restaurante.findByResId" , query = "SELECT r FROM Restaurante r WHERE r.resId = :resId") ,
              @NamedQuery(name = "Restaurante.findByResNombre" , query = "SELECT r FROM Restaurante r WHERE r.resNombre = :resNombre") ,
              @NamedQuery(name = "Restaurante.findByResDetalle" , query = "SELECT r FROM Restaurante r WHERE r.resDetalle = :resDetalle") ,
              @NamedQuery(name = "Restaurante.findByResDireccion" , query = "SELECT r FROM Restaurante r WHERE r.resDireccion = :resDireccion") ,
              @NamedQuery(name = "Restaurante.findByResCorreo" , query = "SELECT r FROM Restaurante r WHERE r.resCorreo = :resCorreo") ,
              @NamedQuery(name = "Restaurante.findByResImpv" , query = "SELECT r FROM Restaurante r WHERE r.resImpv = :resImpv") ,
              @NamedQuery(name = "Restaurante.findByResServ" , query = "SELECT r FROM Restaurante r WHERE r.resServ = :resServ") ,
              @NamedQuery(name = "Restaurante.findByResVersion" , query = "SELECT r FROM Restaurante r WHERE r.resVersion = :resVersion")
          })
public class Restaurante implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_RESTAURANTE_RES_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_RESTAURANTE_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_RESTAURANTE_RES_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "RES_ID")
    private Long resId;
    @Basic(optional = false)
    @Column(name = "RES_NOMBRE")
    private String resNombre;
    @Basic(optional = false)
    @Column(name = "RES_DETALLE")
    private String resDetalle;
    @Basic(optional = false)
    @Column(name = "RES_DIRECCION")
    private String resDireccion;
    @Basic(optional = false)
    @Column(name = "RES_CORREO")
    private String resCorreo;
    @Basic(optional = false)
    @Lob
    @Column(name = "RES_FOTO")
    private byte[] resFoto;
    @Basic(optional = false)
    @Column(name = "RES_IMPV")
    private Long resImpv;
    @Basic(optional = false)
    @Column(name = "RES_SERV")
    private Long resServ;
    @Basic(optional = false)
    @Version
    @Column(name = "RES_VERSION")
    private Long resVersion;
    @OneToMany(mappedBy = "resId" , fetch = FetchType.LAZY)
    private List<Codigodesc> codigodescList;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "resId" , fetch = FetchType.LAZY)
    private List<Salon> salonList;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "resId" , fetch = FetchType.LAZY)
    private List<Producto> productoList;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "resId" , fetch = FetchType.LAZY)
    private List<Empleado> empleadoList;
    @OneToMany(mappedBy = "resId" , fetch = FetchType.LAZY)
    private List<Categoria> categoriaList;

    public Restaurante()
    {
    }

    public Restaurante(Long resId)
    {
        this.resId = resId;
    }

    public Restaurante(Long resId , String resNombre , String resDetalle , String resDireccion , String resCorreo , byte[] resFoto , Long resImpv , Long resServ)
    {
        this.resId = resId;
        this.resNombre = resNombre;
        this.resDetalle = resDetalle;
        this.resDireccion = resDireccion;
        this.resCorreo = resCorreo;
        this.resFoto = resFoto;
        this.resImpv = resImpv;
        this.resServ = resServ;
    }

    public Restaurante(RestauranteDto restauranteDto)
    {
        this.resId = restauranteDto.getId();
        actualizarRestaurante(restauranteDto);
    }

    public void actualizarRestaurante(RestauranteDto restauranteDto)
    {
        this.resNombre = restauranteDto.getNombre();
        this.resDetalle = restauranteDto.getDetalle();
        this.resDireccion = restauranteDto.getDireccion();
        this.resCorreo = restauranteDto.getCorreo();
        this.resFoto = restauranteDto.getFoto();
        this.resImpv = restauranteDto.getImpVen();
        this.resServ = restauranteDto.getImpServ();
    }

    public Long getResId()
    {
        return resId;
    }

    public void setResId(Long resId)
    {
        this.resId = resId;
    }

    public String getResNombre()
    {
        return resNombre;
    }

    public void setResNombre(String resNombre)
    {
        this.resNombre = resNombre;
    }

    public String getResDetalle()
    {
        return resDetalle;
    }

    public void setResDetalle(String resDetalle)
    {
        this.resDetalle = resDetalle;
    }

    public String getResDireccion()
    {
        return resDireccion;
    }

    public void setResDireccion(String resDireccion)
    {
        this.resDireccion = resDireccion;
    }

    public String getResCorreo()
    {
        return resCorreo;
    }

    public void setResCorreo(String resCorreo)
    {
        this.resCorreo = resCorreo;
    }

    public byte[] getResFoto()
    {
        return resFoto;
    }

    public void setResFoto(byte[] resFoto)
    {
        this.resFoto = resFoto;
    }

    public Long getResImpv()
    {
        return resImpv;
    }

    public void setResImpv(Long resImpv)
    {
        this.resImpv = resImpv;
    }

    public Long getResServ()
    {
        return resServ;
    }

    public void setResServ(Long resServ)
    {
        this.resServ = resServ;
    }

    public Long getResVersion()
    {
        return resVersion;
    }

    public void setResVersion(Long resVersion)
    {
        this.resVersion = resVersion;
    }

    public List<Codigodesc> getCodigodescList()
    {
        return codigodescList;
    }

    public void setCodigodescList(List<Codigodesc> codigodescList)
    {
        this.codigodescList = codigodescList;
    }

    public List<Salon> getSalonList()
    {
        return salonList;
    }

    public void setSalonList(List<Salon> salonList)
    {
        this.salonList = salonList;
    }

    public List<Producto> getProductoList()
    {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList)
    {
        this.productoList = productoList;
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
        hash += (resId != null ? resId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Restaurante))
        {
            return false;
        }
        Restaurante other = (Restaurante) object;
        if((this.resId == null && other.resId != null) || (this.resId != null && !this.resId.equals(other.resId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Restaurante[ resId=" + resId + " ]";
    }

}
