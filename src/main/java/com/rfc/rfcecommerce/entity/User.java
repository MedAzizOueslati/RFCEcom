package com.rfc.rfcecommerce.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "users")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String Name;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;


}
