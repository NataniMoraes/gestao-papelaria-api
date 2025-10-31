package br.com.papelaria.gestao_papelaria.model; //indica que a classe pertence ao pacote model
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import lombok.Data;
import java.math.BigDecimal;

@Entity //essa classe é uma tabela no banco de dados
@Table(name = "produtos") //criar a tabela no banco de dados -- Por padrão seria criado a tabela Produto=nome igual a classe -- Boas práticas=definir o nome e geralmente no plural
@Data //Lombok: atalho para gerar os métodos...
public class Produto {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Geração do ID
    private Long id;


    @NotBlank(message = "O nome não pode ser vazio.")
    @Size(min = 3, max=100, message = "O nome deve ter entre 3 e 100 caracteres.")
    @Column(nullable = false) //Customizar a coluna no banco de dados para que não tenha valores nulos
    private String nome;

    private String descricao;

    @NotNull(message = "O preço não pode ser nulo.")
    @PositiveOrZero(message = "O preço deve ser um valor positivo ou zero.")
    @Column(nullable = false)
    private BigDecimal preco;

    @Column(name = "codigo_barras", unique = true)
    private String codigoBarras;


    @NotNull(message = "A quantidade em estoque não pode ser nula.")
    @PositiveOrZero(message = "A quantidade deve ser um valor positivo ou zero.")
    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false) //FK
    private Categoria categoria;
}