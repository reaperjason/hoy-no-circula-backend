package com.conecta.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "carro")

public class Car implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "placa", unique = true)
    private String placa;

    @Column(name = "color")
    private String color;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "chasis", unique = true)
    private String chasis;

    @CreationTimestamp
    @Column(name = "fecha_registro")
    private Date fecha_registro;

}
