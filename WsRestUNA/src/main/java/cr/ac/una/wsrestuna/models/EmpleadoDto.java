/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.models;

/**
 *
 * @author jp015
 */
public class EmpleadoDto
{

    private Long id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String contra;
    private byte[] foto;
    private RolDto rolDto;
    private RestauranteDto restauranteDto;
    private Boolean modificado;

    public EmpleadoDto()
    {
        this.modificado = false;
    }

    public EmpleadoDto(Empleado empleado)
    {
        this.id = empleado.getEmpId();
        this.nombre = empleado.getEmpNombre();
        this.apellido = empleado.getEmpApelllido();
        this.usuario = empleado.getEmpUsuario();
        this.contra = empleado.getEmpContra();
        this.foto = empleado.getEmpFoto();
        this.rolDto = new RolDto(empleado.getRolId());
        this.restauranteDto = new RestauranteDto(empleado.getResId());
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

    public String getApellido()
    {
        return apellido;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
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
