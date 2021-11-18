/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import cr.ac.una.proyectorestaurante.models.*;
import javafx.scene.control.*;
import javafx.scene.shape.*;

/**
 *
 * @author jp015
 */
class IMload
{

    private Circle circle;
    private long posx;
    private long posy;
    private String nombre;
    private MesaDto mesaDto;
    private TextField textField;

    public IMload(Circle circle , long posx , long posy , String nombre , MesaDto mesaDtom, TextField textField)
    {
        this.circle = circle;
        this.posx = posx;
        this.posy = posy;
        this.nombre = nombre;
        this.mesaDto = mesaDtom;
        this.textField=textField;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public Circle getCircle()
    {
        return circle;
    }

    public void setCircle(Circle circle)
    {
        this.circle = circle;
    }

    public long getPosx()
    {
        return posx;
    }

    public void setPosx(long posx)
    {
        this.posx = posx;
    }

    public long getPosy()
    {
        return posy;
    }

    public void setPosy(long posy)
    {
        this.posy = posy;
    }

    public MesaDto getMesaDto()
    {
        return mesaDto;
    }

    public void setMesaDto(MesaDto mesaDto)
    {
        this.mesaDto = mesaDto;
    }

    public TextField getTextField()
    {
        return textField;
    }

    public void setTextField(TextField textField)
    {
        this.textField = textField;
    }



}
