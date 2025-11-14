package br.com.papelaria.gestao_papelaria.service;

import br.com.papelaria.gestao_papelaria.dto.DashboardStatsDTO;
import br.com.papelaria.gestao_papelaria.repository.CategoriaRepository;
import br.com.papelaria.gestao_papelaria.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public DashboardStatsDTO getStats(){

        long produtos = produtoRepository.count();
        long categorias = categoriaRepository.count();

        return  new DashboardStatsDTO(produtos, categorias);
    }
}
