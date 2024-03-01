package com.example.onetoonerest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
@Id
@GeneratedValue
    private int userId;
@NotBlank(message = "name shouldn't be empty")
    private String name;
@NotBlank(message = "gender must be fill")
    private String gender;
@Email(message = "email must be valid")
    private String email;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @JoinColumn(name="up_id")
    private UserProfile userProfile;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", userProfile=" + userProfile +
                '}';
    }
}
