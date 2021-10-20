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
@Table(name = "TBL_RESTAURANTES" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Restaurantes.findAll" , query = "SELECT r FROM Restaurantes r") ,
              @NamedQuery(name = "Restaurantes.findByResId" , query = "SELECT r FROM Restaurantes r WHERE r.resId = :resId") ,
              @NamedQuery(name = "Restaurantes.findByResNombre" , query = "SELECT r FROM Restaurantes r WHERE r.resNombre = :resNombre") ,
              @NamedQuery(name = "Restaurantes.findByResDetalle" , query = "SELECT r FROM Restaurantes r WHERE r.resDetalle = :resDetalle") ,
              @NamedQuery(name = "Restaurantes.findByResDireccion" , query = "SELECT r FROM Restaurantes r WHERE r.resDireccion = :resDireccion") ,
              @NamedQuery(name = "Restaurantes.findByResCorreo" , query = "SELECT r FROM Restaurantes r WHERE r.resCorreo = :resCorreo") ,
              @NamedQuery(name = "Restaurantes.findByResVersion" , query = "SELECT r FROM Restaurantes r WHERE r.resVersion = :resVersion") ,
              @NamedQuery(name = "Restaurantes.findByResVen" , query = "SELECT r FROM Restaurantes r WHERE r.resVen = :resVen") ,
              @NamedQuery(name = "Restaurantes.findByResServ" , query = "SELECT r FROM Restaurantes r WHERE r.resServ = :resServ")
          })
public class Restaurantes implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RES_ID")
    private Long resId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 50)
    @Column(name = "RES_NOMBRE")
    private String resNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 50)
    @Column(name = "RES_DETALLE")
    private String resDetalle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 50)
    @Column(name = "RES_DIRECCION")
    private String resDireccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1 , max = 50)
    @Column(name = "RES_CORREO")
    private String resCorreo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "RES_FOTO")
    private byte[] resFoto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RES_VERSION")
    private Long resVersion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RES_VEN")
    private Long resVen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RES_SERV")
    private Long resServ;
    @OneToMany(mappedBy = "resId" , fetch = FetchType.LAZY)
    private List<Codigosdesc> codigosdescList;

    public Restaurantes()
    {
    }

    public Restaurantes(Long resId)
    {
        this.resId = resId;
    }

    public Restaurantes(Long resId , String resNombre , String resDetalle , String resDireccion , String resCorreo , byte[] resFoto , Long resVen , Long resServ)
    {
        this.resId = resId;
        this.resNombre = resNombre;
        this.resDetalle = resDetalle;
        this.resDireccion = resDireccion;
        this.resCorreo = resCorreo;
        this.resFoto = resFoto;
        this.resVen = resVen;
        this.resServ = resServ;
    }

    public Restaurantes(RestaurantesDto resDto)
    {
        this.resId = resDto.getId();
        actualizarRestaurantes(resDto);
    }

    public void actualizarRestaurantes(RestaurantesDto resDto)
    {
        this.resNombre = resDto.getNombre();
        this.resDetalle = resDto.getDetalle();
        this.resDireccion = resDto.getDireccion();
        this.resCorreo = resDto.getCorreo();
        this.resFoto = resDto.getFoto();
        this.resVen = resDto.getImpVen();
        this.resServ = resDto.getImpServ();
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

    public Long getResVersion()
    {
        return resVersion;
    }

    public void setResVersion(Long resVersion)
    {
        this.resVersion = resVersion;
    }

    public Long getResVen()
    {
        return resVen;
    }

    public void setResVen(Long resVen)
    {
        this.resVen = resVen;
    }

    public Long getResServ()
    {
        return resServ;
    }

    public void setResServ(Long resServ)
    {
        this.resServ = resServ;
    }

    public List<Codigosdesc> getCodigosdescList()
    {
        return codigosdescList;
    }

    public void setCodigosdescList(List<Codigosdesc> codigosdescList)
    {
        this.codigosdescList = codigosdescList;
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
        if(!(object instanceof Restaurantes))
        {
            return false;
        }
        Restaurantes other = (Restaurantes) object;
        if((this.resId == null && other.resId != null) || (this.resId != null && !this.resId.equals(other.resId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Restaurantes[ resId=" + resId + " ]";
    }

}
