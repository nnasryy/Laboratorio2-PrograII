/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_prograii;

import java.util.Calendar;

public class EmpleadoVentas extends Empleado {
    
    private double[] ventasMensuales;
    private double tasaComision;

    public EmpleadoVentas(String codigo, String nombre, Calendar fechaContratacion, double salarioBase, int horasTrabajadas, double tasaComision) {
        super(codigo, nombre, fechaContratacion, salarioBase, horasTrabajadas);
        this.tasaComision = tasaComision;
        this.ventasMensuales = new double[12];
    }

    public void registrarVenta(double monto) {
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        ventasMensuales[mesActual] += monto;
    }

    public double calcularComisionMesActual() {
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        return ventasMensuales[mesActual] * tasaComision;
    }

    @Override
    public double calcularPago() {
        double salarioProporcional = (salarioBase / 160) * horasTrabajadas;
        return salarioProporcional + calcularComisionMesActual();
    }

    public double calcularVentasAnuales() {
        double total = 0;
        for (double venta : ventasMensuales) {
            total += venta;
        }
        return total;
    }

    @Override
    public String mostrarInfo() {
        return super.mostrarInfo()
                + " | Tipo: Empleado Ventas"
                + " | Ventas Anuales: L "
                + String.format("%.2f", calcularVentasAnuales());
    }
}
