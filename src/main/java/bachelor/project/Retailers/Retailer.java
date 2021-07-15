package bachelor.project.Retailers;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.UUID;


@Document
public class Retailer {
    @Id
    private String id;
    @Field
    private String username;
    @Field
    private String password;
    @Field
    private int passwordTrials;
    @Field
    private LocalDate passExpired;

    @Field
    private String address;
    @Field
    private String city;

    public Retailer(
                     String username,
                     String password,
                     int passwordTrials,
                     LocalDate passExpired,
                     String address, String city) {

        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.passwordTrials = passwordTrials;
        this.passExpired = passExpired;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getPasswordTrials() {
        return passwordTrials;
    }

    public void setPasswordTrials(int passwordTrials) {
        this.passwordTrials = passwordTrials;
    }

    public LocalDate getPassExpired() {
        return passExpired;
    }

    public void setPassExpired(LocalDate passExpired) {
        this.passExpired = passExpired;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Retailer{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
