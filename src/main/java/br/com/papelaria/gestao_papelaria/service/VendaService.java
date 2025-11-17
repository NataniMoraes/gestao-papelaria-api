package br.com.papelaria.gestao_papelaria.service;

import br.com.papelaria.gestao_papelaria.dto.ItemVendaDTO;
import br.com.papelaria.gestao_papelaria.dto.VendaRequestDTO;
import br.com.papelaria.gestao_papelaria.model.ItemVenda;
import br.com.papelaria.gestao_papelaria.model.Produto;
import br.com.papelaria.gestao_papelaria.model.Venda;
import br.com.papelaria.gestao_papelaria.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoService produtoService;

    @Transactional
    public Venda realizarVenda(VendaRequestDTO vendaRequestDTO) {
        // ---Nova venda---
        Venda venda = new Venda();
        venda.setDataVenda(LocalDateTime.now());
        BigDecimal valorTotal = BigDecimal.ZERO;
        List<ItemVenda> itensParaSalvar = new ArrayList<>();

        //Carrinho
        for (ItemVendaDTO itemDTO : vendaRequestDTO.getItens()) {

            Produto produto = produtoService.buscarPorId(itemDTO.getProdutoId());

            //Verificar estoque
            if (produto.getQuantidadeEstoque() < itemDTO.getQuantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            //Criar ItemVenda
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(itemDTO.getQuantidade());
            itemVenda.setPrecoUnitario(produto.getPreco());
            itemVenda.setVenda(venda);

            itensParaSalvar.add(itemVenda);

            BigDecimal subtotal = produto.getPreco().multiply(new BigDecimal(itemDTO.getQuantidade()));
            valorTotal = valorTotal.add(subtotal);

            //Baixa no estoque
            int novoEstoque = produto.getQuantidadeEstoque() - itemDTO.getQuantidade();
            produto.setQuantidadeEstoque(novoEstoque);
        }

        venda.setValorTotal(valorTotal);
        venda.setItens(itensParaSalvar);

        return vendaRepository.save(venda);
    }

    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }
}