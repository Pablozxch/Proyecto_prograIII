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
@Table(name = "TBL_SALON" , catalog = "" , schema = "RESTUNA")
@NamedQueries(
          {
              @NamedQuery(name = "Salon.findAll" , query = "SELECT s FROM Salon s") ,
              @NamedQuery(name = "Salon.findBySalId" , query = "SELECT s FROM Salon s WHERE s.salId = :salId") ,
              @NamedQuery(name = "Salon.findBySalNombre" , query = "SELECT s FROM Salon s WHERE s.salNombre = :salNombre") ,
              @NamedQuery(name = "Salon.findBySalBarraomesa" , query = "SELECT s FROM Salon s WHERE s.salBarraomesa = :salBarraomesa") ,
              @NamedQuery(name = "Salon.findBySalVersion" , query = "SELECT s FROM Salon s WHERE s.salVersion = :salVersion")
          })
public class Salon implements Serializable
{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TBL_SALON_SAL_ID_GENERATOR" , sequenceName = "RESTUNA.TBL_SALON_SEQ01" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TBL_SALON_SAL_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "SAL_ID")
    private Long salId;
    @Basic(optional = false)
    @Size(min = 1 , max = 50)
    @Column(name = "SAL_NOMBRE")
    private String salNombre;
    @Basic(optional = false)
    @Lob
    @Column(name = "SAL_IMAGEN")
    private byte[] salImagen;
    @Basic(optional = false)
    @Size(min = 1 , max = 1)
    @Column(name = "SAL_BARRAOMESA")
    private String salBarraomesa;
    @Basic(optional = false)
    @Version
    @Column(name = "SAL_VERSION")
    private Long salVersion;
    @JoinColumn(name = "RES_ID" , referencedColumnName = "RES_ID")
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Restaurante resId;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "salId" , fetch = FetchType.LAZY)
    private List<Mesa> mesaList;

    public Salon()
    {
    }

    public Salon(Long salId)
    {
        this.salId = salId;
    }

    public Salon(Long salId , String salNombre , byte[] salImagen , String salBarraomesa)
    {
        this.salId = salId;
        this.salNombre = salNombre;
        this.salImagen = salImagen;
        this.salBarraomesa = salBarraomesa;
    }

    public Salon(SalonDto salonDto)
    {
        this.salId = salonDto.getId();
        actualizarSalon(salonDto);
    }

    public void actualizarSalon(SalonDto salonDto)
    {
        this.salNombre = salonDto.getNombre();
        this.salImagen = salonDto.getFoto();
        this.salBarraomesa = salonDto.getBarraMesa();
        this.resId = new Restaurante(salonDto.getRestauranteDto());
    }

    public Long getSalId()
    {
        return salId;
    }

    public void setSalId(Long salId)
    {
        this.salId = salId;
    }

    public String getSalNombre()
    {
        return salNombre;
    }

    public void setSalNombre(String salNombre)
    {
        this.salNombre = salNombre;
    }

    public byte[] getSalImagen()
    {
        return salImagen;
    }

    public void setSalImagen(byte[] salImagen)
    {
        this.salImagen = salImagen;
    }

    public String getSalBarraomesa()
    {
        return salBarraomesa;
    }

    public void setSalBarraomesa(String salBarraomesa)
    {
        this.salBarraomesa = salBarraomesa;
    }

    public Long getSalVersion()
    {
        return salVersion;
    }

    public void setSalVersion(Long salVersion)
    {
        this.salVersion = salVersion;
    }

    public Restaurante getResId()
    {
        return resId;
    }

    public void setResId(Restaurante resId)
    {
        this.resId = resId;
    }

    public List<Mesa> getMesaList()
    {
        return mesaList;
    }

    public void setMesaList(List<Mesa> mesaList)
    {
        this.mesaList = mesaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (salId != null ? salId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Salon))
        {
            return false;
        }
        Salon other = (Salon) object;
        if((this.salId == null && other.salId != null) || (this.salId != null && !this.salId.equals(other.salId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cr.ac.una.wsrestuna.models.Salon[ salId=" + salId + " ]";
    }

}
