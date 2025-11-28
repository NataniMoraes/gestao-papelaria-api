package br.com.papelaria.gestao_papelaria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString; // Importar
import lombok.EqualsAndHashCode; // Importar
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "itens_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venda_id", nullable = false)
    @JsonIgnore // Ignora no JSON
    @ToString.Exclude // Ignora no Log do Java (Lombok)
    @EqualsAndHashCode.Exclude // Ignora na comparação (Lombok)
    private Venda venda;

    @ManyToOne(fetch = FetchType.EAGER) // Mude para EAGER para garantir que o produto venha junto
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;
}