import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CarRentalFrame extends JFrame {
    private UserAccount user;
    private LoginFrame loginFrame;
    private ArrayList<UserAccount> allUsers;
    private ArrayList<Car> cars = new ArrayList<>();
    private JTextArea outputArea;

    public CarRentalFrame(UserAccount user, LoginFrame loginFrame, ArrayList<UserAccount> allUsers) {
        this.user = user;
        this.loginFrame = loginFrame;
        this.allUsers = allUsers;

        setTitle("Car Rental - Welcome " + user.getUsername());
        setSize(700, 500);
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
        JButton viewRentedButton = new JButton("View My Rented Cars");
        JButton viewAllRented = new JButton("View All Rented Cars");
        JButton logoutButton = new JButton("Logout");

        JPanel panel = new JPanel();
        panel.add(rentButton);
        panel.add(returnButton);
        panel.add(viewRentedButton);
        panel.add(viewAllRented);
        panel.add(logoutButton);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        //

        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rentCar();
            }
        });
        
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnCar();
            }
        });
        
        viewRentedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMyRentedCars();
            }
        });
        
        viewAllRented.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAllRentedCars();
            }
        });
        
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        

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
        // Show the list of available cars
        StringBuilder availableCarsList = new StringBuilder("Available Cars:\n");
        for (Car car : cars) {
            if (car.isAvailable()) {
                availableCarsList.append(car.getCarId() + " - " + car.getBrand() + " " + car.getModel() + 
                    String.format(" ($%.2f/day)\n", car.calculatePrice(1)));
            }
        }
    
        // If no cars are available, inform the user
        if (availableCarsList.length() == "Available Cars:\n".length()) {
            JOptionPane.showMessageDialog(this, "No cars are available for rent.");
            return;
        }
    
        // Show the list and ask for the car ID
        String carId = JOptionPane.showInputDialog(this, availableCarsList.toString() + "Enter Car ID to rent:");
    
        if (carId == null) return;
    
        // Ask for the rental duration
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
    
        // Rent the car if available
        for (Car car : cars) {
            if (car.getCarId().equalsIgnoreCase(carId) && car.isAvailable()) {
                car.rent(days, user);
                user.addRentedCar(car);
                double price = car.calculatePrice(days);
                JOptionPane.showMessageDialog(this, "Car rented successfully!\nTotal Price: $" + price);
                showAvailableCars();
                return;
            }
        }
    
        // If the car ID is invalid or the car is not available
        JOptionPane.showMessageDialog(this, "Car not available or ID invalid");
    }
    
    private void returnCar() {
        if (user.getRentedCars().isEmpty()) {
            JOptionPane.showMessageDialog(this, "You haven't rented any cars yet!");
            return;
        }
    
        StringBuilder rentedList = new StringBuilder("Your Rented Cars:\n");
        for (Car car : user.getRentedCars()) {
            rentedList.append(car.getCarId()).append(" - ").append(car.getBrand()).append(" ").append(car.getModel()).append("\n");
        }
    
        String carId = JOptionPane.showInputDialog(this, rentedList + "\nEnter Car ID to return:");
        if (carId == null) return;
    
        for (Car car : user.getRentedCars()) {
            if (car.getCarId().equalsIgnoreCase(carId)) {
                car.returnCar();
                user.removeRentedCar(car);
                JOptionPane.showMessageDialog(this, "Car returned successfully!");
                showAvailableCars();
                return;
            }
        }
    
        JOptionPane.showMessageDialog(this, "Car is not rented or invalid ID");
    }
    

    private void showMyRentedCars() {
        if (user.getRentedCars().isEmpty()) {
            JOptionPane.showMessageDialog(this, "You haven't rented any cars yet!");
            return;
        }
    
        outputArea.setText("Your Rented Cars:\n");
        for (Car car : user.getRentedCars()) {
            outputArea.append(car.getCarId() + " - " + car.getBrand() + " " + car.getModel() + "\n");
        }
    }
    
    
    private void showAllRentedCars() {
        boolean anyRented = false;
        outputArea.setText("All Rented Cars:\n");
    
        for (UserAccount u : allUsers) {
            for (Car car : u.getRentedCars()) {
                outputArea.append("User: " + u.getUsername() + " | Car: " + car.getCarId() + " - " + car.getBrand() + " " + car.getModel() + "\n");
                anyRented = true;
            }
        }
    
        if (!anyRented) {
            JOptionPane.showMessageDialog(this, "No cars are currently rented by any user.");
            outputArea.setText(""); // clear the area since there's nothing to show
        }
    }
    
    

    private void logout() {
        dispose();
        loginFrame.setVisible(true);
    }
}