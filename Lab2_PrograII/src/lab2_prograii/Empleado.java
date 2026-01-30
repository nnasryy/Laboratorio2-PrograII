/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_prograii;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nasry
 */
public abstract class Empleado {
    
    //Atributos
    protected String codigo;
    protected String nombre;
    protected Date fechaContratacion;
    protected double salarioBase;
    protected int horasTrabajadas;
    protected String rutaFoto;

    //Atributos
    public Empleado(String codigo, String nombre, Date fecha, double salario, int horasTrabajadas, String foto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaContratacion = fecha;
        this.salarioBase = salario;
        this.horasTrabajadas = 0;
        this.rutaFoto = foto;
    }
    
    //setter
    public void registrarHoras(int horas){
        this.horasTrabajadas=horas;
    }
    
    public abstract double calcularPago();
    
    public String mostarInfo(){
        return "ID: " + codigo + " | Nombre: " + nombre + " | Fecha: " + 
                new SimpleDateFormat("dd/MM/yyyy").format(fechaContratacion);
    }
    
    
}
