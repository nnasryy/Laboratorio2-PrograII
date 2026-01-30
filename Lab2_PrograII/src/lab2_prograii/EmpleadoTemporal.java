/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_prograii;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class EmpleadoTemporal extends Empleado {

    private Calendar fechaFinContrato;

    public EmpleadoTemporal(String codigo, String nombre, Calendar fechaContratacion,
            double salarioBase, int horasTrabajadas, Calendar fechaFinContrato) {
        super(codigo, nombre, fechaContratacion, salarioBase, horasTrabajadas, null);
        this.fechaFinContrato = fechaFinContrato;
    }

    public Calendar getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void actualizarFechaFinContraro(Calendar nuevaFecha) {
        this.fechaFinContrato = nuevaFecha;
    }

    @Override
    public double calcularPago() {
        Calendar fechaActual = Calendar.getInstance();

        if (fechaActual.after(fechaFinContrato)) {
            return 0.0;
        }
        return (salarioBase / 160) * horasTrabajadas;
    }

    @Override
    public String mostrarInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return super.mostrarInfo()
                + " | Tipo: Empleado Temporal"
                + " | Fin contrato: "
                + sdf.format(fechaFinContrato.getTime());
    }
}