/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_prograii;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmpleadoTemporal extends Empleado {

    private Date fechaFinContrato;

    public EmpleadoTemporal(String codigo, String nombre, Date fechaContratacion,
            double salarioBase, int horasTrabajadas, Date fechaFinContrato) {
        super(codigo, nombre, fechaContratacion, salarioBase, horasTrabajadas, null);
        this.fechaContratacion = fechaFinContrato;
  }

    public Date getFechaFinContrato(){
    return fechaFinContrato;
    }
    
    public void actualizarFechaFinContraro(Date nuevaFecha){
    this.fechaFinContrato = nuevaFecha;
    }
   
    
    @Override
    public double calcularPago(){
    Date fechaActual = new Date();
    
    if(fechaActual.after(fechaFinContrato)){
    return 0.0;
    }
    return (salarioBase/160) * horasTrabajadas;
    }

    @Override
    public String mostrarInfo(){
    return super.mostrarInfo()
             + " | Tipo: Empleado Temporal"
                + " | Fin contrato: "
                + new SimpleDateFormat("dd/MM/yyyy").format(fechaFinContrato);
    }
    
    
}
