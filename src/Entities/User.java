package Entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String full_name;
    private String email;
    private Integer id;
    private List<CarbonConsumption> carbonConsumption;

    public User(String full_name, String email, Integer id) {
        this.full_name = full_name;
        this.email = email;
        this.id = id;
        carbonConsumption = new ArrayList<CarbonConsumption>();
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public List<CarbonConsumption> getCarbonConsumption() {
        return carbonConsumption;
    }

    public void setCarbonConsumption(List<CarbonConsumption> carbonConsumption) {
        this.carbonConsumption = carbonConsumption;
    }

    @Override
    public String toString() {
        String printed = "==> Email: " + getEmail() + "\n==> FullName: " + getFull_name() + "\n==> ID: " + getId() + "\n--------------------> \n";
        return printed;
    }
}
