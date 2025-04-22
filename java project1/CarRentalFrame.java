import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CarRentalFrame extends JFrame {
    private UserAccount user;
    private LoginFrame loginFrame;
    private ArrayList<Car> cars = new ArrayList<>();
    private JTextArea outputArea;

    public CarRentalFrame(UserAccount user, LoginFrame loginFrame) {
        this.user = user;
        this.loginFrame = loginFrame;
        setTitle("Car Rental - Welcome " + user.getUsername());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cars.add(new Car("C001", "Toyota", "Camry", 60));
        cars.add(new Car("C002", "Honda", "Civic", 50));
        cars.add(new Car("C003", "Mahindra", "Thar", 150));

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JButton rentButton = new JButton("Rent a Car");
        JButton returnButton = new JButton("Return a Car");
        JButton logoutButton = new JButton("Logout");

        JPanel panel = new JPanel();
        panel.add(rentButton);
        panel.add(returnButton);
        panel.add(logoutButton);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        rentButton.addActionListener(e -> rentCar());
        returnButton.addActionListener(e -> returnCar());
        logoutButton.addActionListener(e -> logout());

        setVisible(true);
        showAvailableCars();
    }

    private void showAvailableCars() {
        outputArea.setText("Available Cars:\n");
        for (Car car : cars) {
            if (car.isAvailable()) {
                outputArea.append(car.getCarId() + " - " + car.getBrand() + " " + car.getModel() +
                        String.format(" ($%.2f/day)\n", car.calculatePrice(1)));
            }
        }
    }

    private void rentCar() {
        if (user.getRentedCarId() != null) {
            JOptionPane.showMessageDialog(this, "You already have a rented car (ID: " + user.getRentedCarId() + "). Return it first.");
            return;
        }

        String carId = JOptionPane.showInputDialog(this, "Enter Car ID to rent:");
        if (carId == null) return;

        String daysStr = JOptionPane.showInputDialog(this, "Enter number of rental days:");
        if (daysStr == null) return;

        int days;
        try {
            days = Integer.parseInt(daysStr);
            if (days <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number of days");
            return;
        }

        for (Car car : cars) {
            if (car.getCarId().equalsIgnoreCase(carId) && car.isAvailable()) {
                car.rent(days);
                user.setRentedCarId(car.getCarId());
                double price = car.calculatePrice(days);
                JOptionPane.showMessageDialog(this, "Car Rented Successfully\n" +
                        "User: " + user.getUsername() + "\n" +
                        "Car: " + car.getBrand() + " " + car.getModel() + "\n" +
                        "Days: " + days + "\n" +
                        String.format("Total Price: $%.2f", price));
                showAvailableCars();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Car not available or ID invalid");
    }

    private void returnCar() {
        if (user.getRentedCarId() == null) {
            JOptionPane.showMessageDialog(this, "You haven't rented any car yet.");
            return;
        }

        String carId = JOptionPane.showInputDialog(this, "Enter Car ID to return:");
        if (carId == null) return;

        for (Car car : cars) {
            if (car.getCarId().equalsIgnoreCase(carId) && !car.isAvailable()) {
                if (!car.getCarId().equals(user.getRentedCarId())) {
                    JOptionPane.showMessageDialog(this, "You can only return the car you rented (ID: " + user.getRentedCarId() + ").");
                    return;
                }
                int days = car.getRentedDays();
                car.returnCar();
                user.setRentedCarId(null);
                JOptionPane.showMessageDialog(this, "Car Returned Successfully\n" +
                        "User: " + user.getUsername() + "\n" +
                        "Car: " + car.getBrand() + " " + car.getModel() + "\n" +
                        "Rented Days: " + days);
                showAvailableCars();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Car is not rented or invalid ID");
    }

    private void logout() {
        dispose();
        loginFrame.setVisible(true);
    }
}