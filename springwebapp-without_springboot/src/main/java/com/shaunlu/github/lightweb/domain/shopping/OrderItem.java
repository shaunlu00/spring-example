package com.shaunlu.github.lightweb.domain.shopping;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table (name = "order_item")
public class OrderItem implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Computer.class, cascade = CascadeType.DETACH)
//    @ManyToOne(targetEntity = Computer.class, cascade = CascadeType.ALL)
    @JoinColumn(name="computer_model", referencedColumnName = "model")
    private Computer computer;

    @Column (name = "number")
    private Integer number;

    @Column(name = "price", length = 10)
    private Double price;

    @Column (name = "order_id", length = 30)
    private String orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
