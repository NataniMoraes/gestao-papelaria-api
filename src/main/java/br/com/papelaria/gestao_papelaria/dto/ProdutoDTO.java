package br.com.papelaria.gestao_papelaria.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String codigoBarras;
    private int quantidadeEstoque;
    private Long categoriaId;
}
