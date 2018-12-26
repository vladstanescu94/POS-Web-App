package com.proiect.pos.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Entity
@Getter
@Setter
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "*Please provide an username")
    private String username;

    @NotEmpty(message = "Please provide your first name!")
    private String firstName;

    @NotEmpty(message = "Please provide your last name!")
    private String lastName;

    @Length(min = 5, message = "Your password must have at least 5 characters")
    @NotEmpty(message = "Please provide your password")
    private String password;

    private String picture;

    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "seller_role", joinColumns = @JoinColumn(name = "seller_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
