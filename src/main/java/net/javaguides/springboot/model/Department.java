package net.javaguides.springboot.model;


import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@DataAmount
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    List<Employee> employees;
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }

}