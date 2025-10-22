package br.com.papelaria.gestao_papelaria.model; //indica que a classe pertence ao pacote model
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity //essa classe é uma tabela no banco de dados
@Table(name = "produtos") //criar a tabela no banco de dados
@Data //Lombok: atalho para gerar os metedos ...
public class Produto {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Geração do ID
    private Long id;

    @Column(nullable = false) //Customizar a coluna no banco de dados para que não tenha valores nulos
    private String nome;

    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque;
}