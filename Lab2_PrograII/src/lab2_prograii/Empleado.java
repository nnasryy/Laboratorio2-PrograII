/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_prograii;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 *
 * @author nasry
 */
public abstract class Empleado {
  protected String codigo;
    protected String nombre;
    protected Calendar fechaContratacion;
    protected double salarioBase;
    protected int horasTrabajadas;

    public Empleado(String codigo, String nombre, Calendar fecha, double salario, int horasTrabajadas, String foto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaContratacion = fecha;
        this.salarioBase = salario;
        this.horasTrabajadas = 0;
    }

    public void registrarHoras(int horas) {
        this.horasTrabajadas = horas;
    }

    public abstract double calcularPago();

    public String mostrarInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "ID: " + codigo + " | Nombre: " + nombre + " | Fecha: " + sdf.format(fechaContratacion.getTime());
    }
}
