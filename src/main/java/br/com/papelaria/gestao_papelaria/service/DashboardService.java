package br.com.papelaria.gestao_papelaria.service;

import br.com.papelaria.gestao_papelaria.dto.DashboardStatsDTO;
import br.com.papelaria.gestao_papelaria.model.Venda;
import br.com.papelaria.gestao_papelaria.repository.CategoriaRepository;
import br.com.papelaria.gestao_papelaria.repository.ProdutoRepository;
import br.com.papelaria.gestao_papelaria.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {
    @Autowired private ProdutoRepository produtoRepository;
    @Autowired private CategoriaRepository categoriaRepository;
    @Autowired private VendaRepository vendaRepository;

    public DashboardStatsDTO getStats(){
        long produtos = produtoRepository.count();
        long categorias = categoriaRepository.count();

        //Calcular o total vendido
        List<Venda> todasVendas = vendaRepository.findAll();
        BigDecimal totalVendas = todasVendas.stream()
                .map(Venda::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return  new DashboardStatsDTO(produtos, categorias, totalVendas);
    }
}
