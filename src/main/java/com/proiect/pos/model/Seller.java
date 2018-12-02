package com.proiect.pos.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="seller")
@Getter @Setter @Data
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="seller_id")
    private int id;

    @Column(name = "username")
    @NotEmpty(message = "*Please provide an username")
    private String username;

    @Column(name="first_name")
    @NotEmpty(message="Please provide your first name!")
    private String firstName;

    @Column(name="last_name")
    @NotEmpty(message="Please provide your last name!")
    private String lastName;

    @Column(name="password")
    @Length(min=5,message = "Your password must have at least 5 characters")
    @NotEmpty(message="Please provide your password")
    private String password;

    @Column(name = "picture")
    private Serializable picture;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "seller_role", joinColumns = @JoinColumn(name = "seller_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
