package br.com.papelaria.gestao_papelaria.dto;

import lombok.Data;

import java.util.List;

@Data
public class VendaRequestDTO {
    private List<ItemVendaDTO> itens;
}
