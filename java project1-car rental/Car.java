public class Car {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;
    private int rentedDays;
    private UserAccount rentedBy; // NEW: to track who rented the car

    public Car(String carId, String brand, String model, double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
        this.rentedDays = 0;
        this.rentedBy = null;
    }

    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    // Updated to accept UserAccount
    public void rent(int days, UserAccount user) {
        isAvailable = false;
        rentedDays = days;
        rentedBy = user;
    }

    public void returnCar() {
        isAvailable = true;
        rentedDays = 0;
        rentedBy = null;
    }

    public int getRentedDays() {
        return rentedDays;
    }

    public UserAccount getRentedBy() {
        return rentedBy;
    }
}
