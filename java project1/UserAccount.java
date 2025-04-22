public class UserAccount {
    private String username;
    private String password;
    private String rentedCarId; // Track the rented car ID

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.rentedCarId = null;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public String getRentedCarId() {
        return rentedCarId;
    }

    public void setRentedCarId(String carId) {
        this.rentedCarId = carId;
    }
}