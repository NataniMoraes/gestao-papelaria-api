package br.com.papelaria.gestao_papelaria.dto;

// Para o Dash da versão web

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor

public class DashboardStatsDTO {
    private long totalProdutos;
    private long totalCategorias;
    private BigDecimal totalCategoria;
}
