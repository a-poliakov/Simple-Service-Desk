package ru.apolyakov.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "servicecall")
public class ServiceCall {
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
    private String name;

    @Size(min = 2, max = 255)
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "priority", nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;


    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;

    public ServiceCall() {
        super();
    }

    public ServiceCall(int id, String name, String description, Priority priority, Employee employee) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
