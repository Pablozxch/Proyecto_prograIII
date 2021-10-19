/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.models;

import java.util.*;

/**
 *
 * @author jp015
 */
public class EmpleadosDto
{

    private Long id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String contra;
    private byte[] foto;
    private RolDto rol;
    private Boolean modificado;
    
    public EmpleadosDto()
    {
        this.modificado=false;
    }
    public EmpleadosDto(Empleados emp)
    {
        this();
        this.id=emp.getEmpId();
        this.nombre=emp.getEmpNombre();
        this.apellido=emp.getEmpApelllido();
        this.usuario=emp.getEmpUsuario();
        this.contra=emp.getEmpContra();
        this.rol = new RolDto(emp.getRolId());
    }

    public String getApellido()
    {
        return apellido;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getContra()
    {
        return contra;
    }

    public void setContra(String contra)
    {
        this.contra = contra;
    }

    public byte[] getFoto()
    {
        return foto;
    }

    public void setFoto(byte[] foto)
    {
        this.foto = foto;
    }

    public RolDto getRol()
    {
        return rol;
    }

    public void setRol(RolDto rol)
    {
        this.rol = rol;
    }

    public Boolean getModificado()
    {
        return modificado;
    }

    public void setModificado(Boolean modificado)
    {
        this.modificado = modificado;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("EmpleadosDto{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", apellido=").append(apellido);
        sb.append(", usuario=").append(usuario);
        sb.append(", contra=").append(contra);
        sb.append(", foto=").append(foto);
        sb.append(", rol=").append(rol);
        sb.append('}');
        return sb.toString();
    }
    
}
