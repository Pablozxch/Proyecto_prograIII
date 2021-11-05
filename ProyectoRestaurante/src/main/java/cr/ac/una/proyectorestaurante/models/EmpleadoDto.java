/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.models;

import javafx.beans.property.*;

/**
 *
 * @author jp015
 */
public class EmpleadoDto
{

    private SimpleStringProperty id;
    public SimpleStringProperty nombre;
    private SimpleStringProperty apellido;
    private SimpleStringProperty usuario;
    private SimpleStringProperty contra;
    private ObjectProperty<byte[]> foto;
    private RolDto rolDto;
    private RestauranteDto restauranteDto;
    private Boolean modificado;

    public EmpleadoDto()
    {
        this.id = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.apellido = new SimpleStringProperty();
        this.usuario = new SimpleStringProperty();
        this.contra = new SimpleStringProperty();
        this.foto = new SimpleObjectProperty();
        this.modificado = false;
    }

    public Long getId()
    {
        if(id.get() != null && !id.get().isEmpty())
        {
            return Long.valueOf(id.get());
        }
        else
        {
            return null;
        }
    }

    public void setId(Long id)
    {
        this.id.set(id.toString());
    }

    public String getNombre()
    {
        return nombre.get();
    }

    public void setNombre(String nombre)
    {
        this.nombre.set(nombre);
    }

    public String getApellido()
    {
        return apellido.get();
    }

    public void setApellido(String apellido)
    {
        this.apellido.set(apellido);
    }

    public String getUsuario()
    {
        return usuario.get();
    }

    public void setUsuario(String usuario)
    {
        this.usuario.set(usuario);
    }

    public String getContra()
    {
        return contra.get();
    }

    public void setContra(String contra)
    {
        this.contra.set(contra);
    }

    public byte[] getFoto()
    {
        return foto.getValue();
    }

    public void setFoto(byte[] foto)
    {
        this.foto.set(foto);
    }

    public RolDto getRolDto()
    {
        return rolDto;
    }

    public void setRolDto(RolDto rolDto)
    {
        this.rolDto = rolDto;
    }

    public RestauranteDto getRestauranteDto()
    {
        return restauranteDto;
    }

    public void setRestauranteDto(RestauranteDto restauranteDto)
    {
        this.restauranteDto = restauranteDto;
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
        sb.append("EmpleadoDto{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", apellido=").append(apellido);
        sb.append(", usuario=").append(usuario);
        sb.append(", contra=").append(contra);
        sb.append(", foto=").append(foto);
        sb.append(", rolDto=").append(rolDto);
        sb.append(", restauranteDto=").append(restauranteDto.toString());
        sb.append('}');
        return sb.toString();
    }

}
