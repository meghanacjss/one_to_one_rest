package com.example.onetoonerest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue
    private int id;
    private String phoneNumber;
    private String address;
    private String gender;
    private LocalDate dob;
@OneToOne(cascade = CascadeType.ALL)
    private User user;

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", DOB=" + dob +
                '}';
    }
}
