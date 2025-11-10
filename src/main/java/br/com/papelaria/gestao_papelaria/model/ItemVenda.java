package br.com.papelaria.gestao_papelaria.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "itens_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- RELACIONAMENTO COM A VENDA ---
    // Muitos Itens de Venda pertencem a Uma Venda.
    @ManyToOne(fetch = FetchType.LAZY) // LAZY: só carrega a Venda do BD quando for necessário
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    // --- RELACIONAMENTO COM O PRODUTO ---
    // Muitos Itens de Venda (em diferentes vendas) podem se referir a Um Produto.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario; // Preço do produto no MOMENTO da venda

}