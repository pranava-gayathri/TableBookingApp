package com.pranavagayathri.TableBookingApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name = "email_unique",columnNames = "email_id"))
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotEmpty(message = "User name cannot be empty")
    @Size(min = 3, max = 50, message = "User name must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "User name can only contain letters and spaces")
    @Column
    private String userName;

    @Column(name = "email_id",nullable = false)
    @Email(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Email should be a valid Gmail address")
    private String email;

    @Column
    @Size(min = 5, message = "Password size must be greater than 5 characters")
    private String password;


    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String phone;

    @Column
    @Size(min = 5,max = 40)
    @NotEmpty(message = "Address shouldn't be empty")
    private String userAddress;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Reservation> reservations;




}
