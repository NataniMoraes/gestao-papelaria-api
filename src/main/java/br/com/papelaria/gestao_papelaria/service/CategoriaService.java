package br.com.papelaria.gestao_papelaria.service;

import br.com.papelaria.gestao_papelaria.exception.ResourceNotFoundException;
import br.com.papelaria.gestao_papelaria.model.Categoria;
import br.com.papelaria.gestao_papelaria.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> buscarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com o id: " + id));
    }

    public Categoria criarCategoria(Categoria categoria) {
        // Podemos adicionar validações aqui no futuro (ex: não permitir nomes duplicados)
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizarCategoria(Long id, Categoria categoriaDetalhes) {
        Categoria categoria = buscarPorId(id); // Reutiliza o método para encontrar ou lançar exceção
        categoria.setNome(categoriaDetalhes.getNome());
        return categoriaRepository.save(categoria);
    }

    public void deletarCategoria(Long id) {
        Categoria categoria = buscarPorId(id); // Garante que a categoria exista antes de deletar
        categoriaRepository.delete(categoria);
    }
}
