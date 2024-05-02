package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "basic_profile")
public class BasicProfile {
    @Id
    @Column(name = "profile_id", nullable = false)
    private int profileId;

    @Column(name = "birth_year")
    private int birthYear;
    @Column(name = "weight")
    private double weight;
    @Column(name = "height")
    private double height;

    @OneToOne
    @MapsId
    @JoinColumn(name = "profile_id")
    private Customer customer;

    public BasicProfile() {
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
