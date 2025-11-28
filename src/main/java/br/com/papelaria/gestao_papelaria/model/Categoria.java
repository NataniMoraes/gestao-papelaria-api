package br.com.papelaria.gestao_papelaria.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categorias")

public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco de dados irá gerar o ID automaticamente
    private Long id;
    private String nome;
}