package ru.apolyakov.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
/*
 * employee: trùng với tên bảng csdl
 */
@Table(name = "employee")
public class Employee implements Serializable {

    /**
     *  UUID-sequence
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 30)
    @NotNull
    @Pattern(regexp = "[a-zA-ZА-Яа-я0-9_\\s]+", message = "Your name must contain valid characters! (a-z, A-Z, А-Я, а-я, 0-9, space)")
    private String name;

    @Column(name = "phone")
    @Size(max = 15)
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    private String phone;

    @Column(name = "email")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Incorrect email!")
    private String email;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "employee", orphanRemoval = true)
    private List<ServiceCall> serviceCalls = new ArrayList<>();

    public Employee() {
        super();
    }

    public Employee(int id, String name, String phone) {
        super();
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public List<ServiceCall> getServiceCalls() {
        return serviceCalls;
    }

    public void setServiceCalls(List<ServiceCall> serviceCalls) {
        this.serviceCalls = serviceCalls;
    }
}