/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_prograii;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author hermi
 */
public class Empresa {
    
    private HashMap<String, Empleado> mapaEmpleados;

    public Empresa() {
        this.mapaEmpleados = new HashMap<>();
    }

    public boolean registrarEmpleado(Empleado nuevo) {
        if (buscarEmpleadoCodigo(nuevo.getCodigo()) != null) {
            return false; 
        }
        
        mapaEmpleados.put(nuevo.getCodigo(), nuevo);
        return true;
    }

    public List<Empleado> obtenerListaParaTabla() {
        return new ArrayList<>(mapaEmpleados.values());
    }
    
    public void registrarHoras(String codigo, int horas) {
        Empleado e = buscarEmpleadoCodigo(codigo);
        if (e != null) {
            e.registrarHoras(horas);
        }
    }
    
    public boolean registrarVenta(String codigo, double monto) {
        Empleado e = buscarEmpleadoCodigo(codigo);
        if (e instanceof EmpleadoVentas) {
           ((EmpleadoVentas) e).registrarVenta(monto);
            return true;
        }
        return false;
    }
    
    public boolean actualizarFinContrato(String codigo, Calendar nuevaFecha) {
        Empleado e = buscarEmpleadoCodigo(codigo);
        if (e instanceof EmpleadoTemporal) {
            ((EmpleadoTemporal) e).actualizarFechaFinContraro(nuevaFecha);
            return true;
        }
        return false;
    }
    
    public double obtenerPagoMensual(String codigo) {
    Empleado e = buscarEmpleadoCodigo(codigo);
    if (e != null) {
        return e.calcularPago(); 
        }
    return 0.0;
    }
    
    public String generarReporte() {
    String reporte = " Reporte de los empleados \n";
    
    int estandar = 0, temporal = 0, ventas = 0;

    for (Empleado e : mapaEmpleados.values()) {
        reporte = reporte + e.mostrarInfo() + " | Pago: Lps." + e.calcularPago() + "\n";
        
        if (e instanceof EmpleadoVentas) {
            ventas++;
        } else if (e instanceof EmpleadoTemporal) {
            temporal++;
        } else {
            estandar++;
        }
    }

    reporte = reporte + "\nTotales por tipos de empleados: \n";
    reporte = reporte + "Estándar: " + estandar + "\n";
    reporte = reporte + "Temporales: " + temporal + "\n";
    reporte = reporte + "Ventas: " + ventas + "\n";

    return reporte;
    }
    
    public Empleado buscarEmpleadoCodigo(String codigo) {
        return mapaEmpleados.get(codigo);
    }

   
}
