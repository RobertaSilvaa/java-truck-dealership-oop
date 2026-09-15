import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TruckYard yard = new TruckYard();

        // Create the window
        JFrame window = new JFrame("Truck Dealership");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel(new String[] { "ID", "Model" }, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tablePane = new JScrollPane(table);
        tablePane.setPreferredSize(new Dimension(300, 0));
        window.add(tablePane, BorderLayout.WEST);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane textScrollPane = new JScrollPane(textArea);
        window.add(textScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));

        // Add a truck
        JButton addButton = new JButton("Add Truck");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = { "Flatbed Truck", "Car Carrier" };
                String type = (String) JOptionPane.showInputDialog(window,
                        "Choose the truck type:",
                        "Add Truck",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (type != null) {
                    try {
                        Truck truck;
                        double price = Double
                                .parseDouble(JOptionPane.showInputDialog(window, "Enter the truck price:"));
                        int year = Integer.parseInt(JOptionPane.showInputDialog(window, "Enter the truck year:"));
                        String model = JOptionPane.showInputDialog(window, "Enter the truck model:");

                        if (type.equals("Flatbed Truck")) {
                            double platformLength = Double.parseDouble(
                                    JOptionPane.showInputDialog(window, "Enter the platform length (in meters):"));
                            truck = new FlatbedTruck(price, year, model, platformLength);
                        } else {
                            int capacity = Integer.parseInt(JOptionPane.showInputDialog(window,
                                    "Enter the car carrier capacity (number of cars): "));
                            truck = new CarCarrier(price, year, model, capacity);
                        }
                        yard.addTruck(truck);
                        textArea.append("Added: " + truck.getModel() + "\n");

                        tableModel.addRow(new Object[] { truck.getId(), truck.getModel() }); // Update the table
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(window, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Remove a truck
        JButton removeButton = new JButton("Remove Truck");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (yard.getTrucks().isEmpty()) {
                        throw new TruckYardException("The truck yard is empty!");
                    }
                    String idStr = JOptionPane.showInputDialog(window, "Enter the ID of the truck to remove:");
                    if (idStr != null) {
                        int id = Integer.parseInt(idStr);

                        // Check whether the ID exists
                        boolean exists = false;
                        for (Truck truck : yard.getTrucks()) {
                            if (truck.getId() == id) {
                                exists = true;
                                break;
                            }
                        }

                        if (!exists) {
                            JOptionPane.showMessageDialog(window, "Truck with ID " + id + " not found.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            yard.removeTruck(id);
                            textArea.append("Removed: ID " + id + "\n");

                            List<Truck> trucks = yard.getTrucks();
                            tableModel.setRowCount(0);

                            for (Truck truck : trucks) {
                                tableModel.addRow(new Object[] { truck.getId(), truck.getModel() }); // Update the table
                            }
                        }
                    }
                } catch (TruckYardException ex) {
                    JOptionPane.showMessageDialog(window, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(window, "Invalid ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // View truck information
        JButton viewButton = new JButton("View Information");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(""); // Clear the text area
                List<Truck> trucks = yard.getTrucks();
                for (Truck truck : trucks) {
                    textArea.append(truck.getInfo());
                }
            }
        });

        // Load sample data
        JButton sampleDataButton = new JButton("Load Sample Data");
        sampleDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 3 car carriers
                    for (int i = 1; i <= 3; i++) {
                        Truck carCarrier = new CarCarrier(100000.0, 2022, "Car Carrier Model " + i, 15 + i);
                        yard.addTruck(carCarrier);
                        tableModel.addRow(new Object[] { carCarrier.getId(), carCarrier.getModel() });
                        textArea.append("Added: " + carCarrier.getModel() + "\n");
                    }

                    // 2 flatbed trucks
                    for (int i = 1; i <= 2; i++) {
                        Truck flatbedTruck = new FlatbedTruck(150000.0, 2021, "Flatbed Truck Model " + i, 12 + i);
                        yard.addTruck(flatbedTruck);
                        tableModel.addRow(new Object[] { flatbedTruck.getId(), flatbedTruck.getModel() });
                        textArea.append("Added: " + flatbedTruck.getModel() + "\n");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(window, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(sampleDataButton);

        window.add(buttonPanel, BorderLayout.SOUTH);
        window.setVisible(true);
    }
}
