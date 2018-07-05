package com.shaunlu.github.lightweb.domain.shopping;

import com.shaunlu.github.lightweb.config.database.AbstractAuditingEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sales_order")
public class Order extends AbstractAuditingEntity{

    @Id
    private String id;

    @OneToMany(targetEntity = OrderItem.class, cascade = CascadeType.ALL, mappedBy = "orderId", fetch = FetchType.LAZY)
//    @OneToMany(targetEntity = OrderItem.class, cascade = CascadeType.DETACH, mappedBy = "orderId", fetch = FetchType.LAZY)
    private Set<OrderItem> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }
}
