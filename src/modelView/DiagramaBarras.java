/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelView;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import model.DatabaseHandler;

public class DiagramaBarras extends JFrame {

    public DiagramaBarras() {
        setTitle("Diagrama de Barras desde DB");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        // Obtener datos desde la base de datos
        Map<String, Double> datosDesdeDB = DatabaseHandler.obtenerDatosDesdeDB();

        // Crear el conjunto de datos para el gráfico de barras
        CategoryDataset dataset = createDataset(datosDesdeDB);

        // Crear el gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                "Precios Actuales desde DB", // Título del gráfico
                "Categorías",                 // Etiqueta del eje X
                "Precio",                     // Etiqueta del eje Y
                dataset,                      // Conjunto de datos
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Configurar la apariencia del gráfico
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);

        CategoryAxis xAxis = plot.getDomainAxis();
        xAxis.setCategoryMargin(0.2); // Margen entre las barras
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // Configurar colores de las barras a verde
        plot.getRenderer().setSeriesPaint(0, Color.GREEN);

        // Mostrar el gráfico en un panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));

        // Agregar el panel al marco
        getContentPane().add(chartPanel, BorderLayout.CENTER);
    }

    // Método para crear el conjunto de datos desde los datos de la base de datos
    private CategoryDataset createDataset(Map<String, Double> datos) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Agregar datos al conjunto de datos
        for (Map.Entry<String, Double> entry : datos.entrySet()) {
            dataset.addValue(entry.getValue(), "Precio", entry.getKey());
        }

        return dataset;
    }

    // Método para mostrar la ventana
    public void mostrar() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
    }

    public static void main(String[] args) {
        // Crear una instancia de DiagramaBarrasDesdeDB y mostrar la ventana
        new DiagramaBarras().mostrar();
    }
}
    