package br.com.papelaria.gestao_papelaria.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VendaRequestDTO {
    private List<ItemVendaDTO> itens;

    private String formaPagamento;
    private BigDecimal desconto;
}
