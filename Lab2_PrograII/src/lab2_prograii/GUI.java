/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_prograii;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class GUI extends JFrame{
    
    private Empresa miEmpresa; 

    private JTextField txtCodigo, txtNombre, txtSalario, txtTasa;
    private JComboBox<String> cbTipo;
    private JTextArea areaReporte;
    private JButton btnRegistrar, btnReporte, btnPagar, btnHoras, btnVenta, btnLimpiar, btnBuscar;

    public GUI() {
        miEmpresa = new Empresa();
        configurarVentana();
        inicializarComponentes();
        configurarEventos();
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestión de Empleados");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelForm.add(txtCodigo);

        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Salario Base:"));
        txtSalario = new JTextField();
        panelForm.add(txtSalario);

        panelForm.add(new JLabel("Tipo Empleado:"));
        cbTipo = new JComboBox<>(new String[]{"Estándar", "Ventas", "Temporal"});
        panelForm.add(cbTipo);

        panelForm.add(new JLabel("Tasa Comisión (Solo Ventas):"));
        txtTasa = new JTextField("0.05");
        panelForm.add(txtTasa);

        add(panelForm, BorderLayout.NORTH);

        // --- Panel de Botones (Este) ---
        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 5, 5));
        btnRegistrar = new JButton("Registrar Empleado");
        btnPagar = new JButton("Ver Pago Individual");
        btnReporte = new JButton("Generar Reporte");
        btnLimpiar = new JButton("Limpiar Campos");
        btnBuscar = new JButton("Buscar Empleado");
        btnHoras = new JButton("Registro Horas");
        btnVenta = new JButton("Registro Venta");
        
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnPagar);
        panelBotones.add(btnReporte);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnHoras);
        panelBotones.add(btnVenta);
        
        add(panelBotones, BorderLayout.EAST);

        areaReporte = new JTextArea();
        areaReporte.setEditable(false);
        areaReporte.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(areaReporte), BorderLayout.CENTER);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> {
    try {
        String cod = txtCodigo.getText().trim();
        String nom = txtNombre.getText().trim();
        String salText = txtSalario.getText().trim();
        String tasaText = txtTasa.getText().trim();

        if (cod.isEmpty() || nom.isEmpty() || salText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Código, Nombre y Salario son obligatorios.");
            return;
        }

        double sal = Double.parseDouble(salText);
        String tipo = (String) cbTipo.getSelectedItem();
        Calendar hoy = Calendar.getInstance();

        Empleado nuevo = null;

        if (tipo.equals("Ventas")) {
            double tasa = tasaText.isEmpty() ? 0.05 : Double.parseDouble(tasaText);
            nuevo = new EmpleadoVentas(cod, nom, hoy, sal, 0,tasa);
            
        } else if (tipo.equals("Temporal")) {
            Calendar fin = Calendar.getInstance();
            fin.add(Calendar.MONTH, 6); 
            nuevo = new EmpleadoTemporal(cod, nom, hoy, sal, 0,fin);
            
        } else {
            nuevo = new EmpleadoEstandar(cod, nom, hoy, sal, 0);
        }

        if (miEmpresa.registrarEmpleado(nuevo)) {
            JOptionPane.showMessageDialog(this, " Empleado " + tipo + " registrado.");
        } else {
            JOptionPane.showMessageDialog(this, "El código " + cod + " ya esta en uso.");
        }

            } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Salario y Tasa deben ser valores numericos.");
            } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Ocurrio un error: " + ex.getMessage());
            }
        });

        btnReporte.addActionListener(e -> {
            areaReporte.setText(miEmpresa.generarReporte());
        });
        
        btnLimpiar.addActionListener(e -> {
         txtCodigo.setText("");
         txtNombre.setText("");
         txtSalario.setText("");
         txtTasa.setText("");
         cbTipo.setSelectedIndex(0); 
         txtCodigo.requestFocus();
        });

        btnPagar.addActionListener(e -> {
            String cod = txtCodigo.getText();
            double pago = miEmpresa.obtenerPagoMensual(cod);
            if (pago > 0) {
                JOptionPane.showMessageDialog(this, "El pago para " + cod + " es: Lps. " + pago);
            } else {
                JOptionPane.showMessageDialog(this, "Empleado no encontrado o contrato vencido.");
            }
        });
        
        btnHoras.addActionListener(e -> {
        String cod = txtCodigo.getText();
        String h = JOptionPane.showInputDialog("Ingrese horas trabajadas:");
        if (h != null) {
        miEmpresa.registrarHoras(cod, Integer.parseInt(h));
        JOptionPane.showMessageDialog(this, "Horas registradas.");
            }
        });
        
        btnVenta.addActionListener(e -> {
        String cod = txtCodigo.getText();
        String v = JOptionPane.showInputDialog("Ingrese monto de la venta:");
        if (v != null) {
        if (miEmpresa.registrarVenta(cod, Double.parseDouble(v))) {
            JOptionPane.showMessageDialog(this, "Venta registrada con éxito.");
        } else {
            JOptionPane.showMessageDialog(this, "El empleado no es de ventas.");
        }
            }
        });
    }
    

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtSalario.setText("");
        txtTasa.setText("0.05");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }
    
}
