package br.com.papelaria.gestao_papelaria.controller;

import br.com.papelaria.gestao_papelaria.dto.VendaRequestDTO;
import br.com.papelaria.gestao_papelaria.model.Venda;
import br.com.papelaria.gestao_papelaria.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<Venda> realizarVenda(@RequestBody VendaRequestDTO vendaRequestDTO) {
        try {
            Venda novaVenda = vendaService.realizarVenda(vendaRequestDTO);
            return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping // GET /vendas
    public ResponseEntity<List<Venda>> listarVendas() {
        List<Venda> vendas = vendaService.listarTodas(); // Precisamos criar este método no Service
        return ResponseEntity.ok(vendas);
    }


}