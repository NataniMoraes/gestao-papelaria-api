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
        // --- 1. Inicialização da Venda ---
        Venda venda = new Venda();
        venda.setDataVenda(LocalDateTime.now());

        // Define a forma de pagamento
        venda.setFormaPagamento(vendaRequestDTO.getFormaPagamento());

        // Define o desconto (se vier nulo, assume zero)
        BigDecimal desconto = vendaRequestDTO.getDesconto() != null ? vendaRequestDTO.getDesconto() : BigDecimal.ZERO;
        venda.setDesconto(desconto);

        // Variáveis auxiliares
        BigDecimal valorTotalItens = BigDecimal.ZERO;
        List<ItemVenda> itensParaSalvar = new ArrayList<>();

        // --- 2. Processamento do Carrinho ---
        for (ItemVendaDTO itemDTO : vendaRequestDTO.getItens()) {

            Produto produto = produtoService.buscarPorId(itemDTO.getProdutoId());

            // Verificar estoque
            if (produto.getQuantidadeEstoque() < itemDTO.getQuantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            // Criar ItemVenda
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(itemDTO.getQuantidade());
            itemVenda.setPrecoUnitario(produto.getPreco());
            itemVenda.setVenda(venda);

            itensParaSalvar.add(itemVenda);

            // Soma ao total bruto
            BigDecimal subtotal = produto.getPreco().multiply(new BigDecimal(itemDTO.getQuantidade()));
            valorTotalItens = valorTotalItens.add(subtotal);

            // Baixa no estoque
            int novoEstoque = produto.getQuantidadeEstoque() - itemDTO.getQuantidade();
            produto.setQuantidadeEstoque(novoEstoque);
        }

        // --- 3. Cálculos Finais ---

        // Define os itens na venda
        venda.setItens(itensParaSalvar);

        // Define o Valor Total (Bruto)
        venda.setValorTotal(valorTotalItens);

        // Calcula o Valor Final (Bruto - Desconto)
        BigDecimal valorFinal = valorTotalItens.subtract(desconto);

        // Garante que o valor final não seja negativo
        if (valorFinal.compareTo(BigDecimal.ZERO) < 0) {
            valorFinal = BigDecimal.ZERO;
        }
        venda.setValorFinal(valorFinal);

        // Salva tudo no banco
        return vendaRepository.save(venda);
    }

    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }
}