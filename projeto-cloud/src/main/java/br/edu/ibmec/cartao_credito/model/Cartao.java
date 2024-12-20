package br.edu.ibmec.cartao_credito.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Boolean ativo;

    @Column
    private double limite;

    @Column(unique = true)
    private String numero;

    @OneToMany(mappedBy = "cartao")
    @JsonManagedReference
    private List<Transacao> transacoes;
}