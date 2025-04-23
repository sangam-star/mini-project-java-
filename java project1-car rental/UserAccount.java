import java.util.ArrayList;

public class UserAccount {
    private String username;
    private String password;
    private ArrayList<Car> rentedCars = new ArrayList<>();

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    //  Add rented car to list
    public void addRentedCar(Car car) {
        rentedCars.add(car);
    }

    // Remove rented car from list
    public void removeRentedCar(Car car) {
        rentedCars.remove(car);
    }

    // Get all rented cars
    public ArrayList<Car> getRentedCars() {
        return rentedCars;
    }
}
