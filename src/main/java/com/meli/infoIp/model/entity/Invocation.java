package com.meli.infoIp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "invocations")
@NoArgsConstructor
public class Invocation {

    @Id
    @Column(name = "pais")
    private String country;
    @Column(name = "distancia")
    private Double distance;
    @Column(name = "invocaciones")
    private Integer invocations;

    public Invocation(String country, Double distance){
        this.country = country;
        this.distance = distance;
        this.invocations = 1;
    }

    public void addInvocation(){
        this.invocations = this.invocations +1;
    }


}
