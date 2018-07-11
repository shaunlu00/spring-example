package com.shaunlu.springexample.model.b;

import javax.persistence.*;

@Entity
@Table(name = "app_permission")
public class AppPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "operation", nullable = false)
    private String operation;

    @Column(name = "is_allowed")
    private Boolean isAllowed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Boolean getAllowed() {
        return isAllowed;
    }

    public void setAllowed(Boolean allowed) {
        isAllowed = allowed;
    }

    public AppPermission(){}

    public AppPermission(String userId, String operation, Boolean isAllowed) {
        this.userId = userId;
        this.operation = operation;
        this.isAllowed = isAllowed;
    }
}
