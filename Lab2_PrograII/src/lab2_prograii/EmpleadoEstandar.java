/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_prograii;

import java.util.Calendar;

/**
 *
 * @author hermi
 */
public class EmpleadoEstandar extends Empleado{

    public EmpleadoEstandar(String codigo, String nombre, Calendar fecha, double salario, int horasTrabajadas) {
        super(codigo, nombre, fecha, salario, horasTrabajadas);
    }
    

    @Override
    public double calcularPago() {
        return (salarioBase / 160) * horasTrabajadas;
    }
    
    @Override
    public String mostrarInfo() {
        return super.mostrarInfo() + " | Tipo: Estándar";
    }
    
    
}
