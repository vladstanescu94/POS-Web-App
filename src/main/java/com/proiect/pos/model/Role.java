package com.proiect.pos.model;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    private int id;

    private String role;
}
