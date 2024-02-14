package com.rfc.rfcecommerce.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String Name;
    private UserRole role;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;


}
