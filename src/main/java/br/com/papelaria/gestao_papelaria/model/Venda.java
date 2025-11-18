package br.com.papelaria.gestao_papelaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_venda", nullable = false)
    private LocalDateTime dataVenda;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "desconto")
    private BigDecimal desconto;

    @Column(name = "valor_final")
    private BigDecimal valorFinal; // Valor Total - Desconto

    @Column(name = "forma_pagamento")
    private String formaPagamento;


    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ItemVenda> itens = new ArrayList<>();
}