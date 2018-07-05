package com.shaunlu.github.lightweb.domain.shopping;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_computer")
public class Computer implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column (name = "model", unique = true, length = 50)
    private String model;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
