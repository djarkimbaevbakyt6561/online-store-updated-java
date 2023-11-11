import java.util.ArrayList;

public class User {
    String firstName;
    String lastName;
    String email;
    String password;
    ArrayList <Product> products = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", products=" + products +
                '}';
    }
}
