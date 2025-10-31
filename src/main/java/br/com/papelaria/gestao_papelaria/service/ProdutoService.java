//SERVICE: O service é o cérebro da apliacação, é a parte que orquestre as operações.

package br.com.papelaria.gestao_papelaria.service;
import br.com.papelaria.gestao_papelaria.dto.ProdutoDTO;
import br.com.papelaria.gestao_papelaria.exception.ResourceNotFoundException;
import br.com.papelaria.gestao_papelaria.model.Categoria;
import br.com.papelaria.gestao_papelaria.model.Produto;
import br.com.papelaria.gestao_papelaria.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service //Marcar a classe como um componente de serviço
public class ProdutoService {

    @Autowired //Injeção de depedência
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    public List<Produto> listarTodos(){
        return produtoRepository.findAll();
    }


    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o id: " + id));
    }

    public Produto criarProduto(ProdutoDTO produtoDTO){
        Categoria categoria = categoriaService.buscarPorId(produtoDTO.getCategoriaId());
        Produto produto = new Produto();

        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setCodigoBarras(produtoDTO.getCodigoBarras());
        produto.setQuantidadeEstoque(produtoDTO.getQuantidadeEstoque());
        produto.setCategoria(categoria);
        return produtoRepository.save(produto);
    }


    //ATUALIZAR PRODUTO
    public Produto atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        // 1. Busca o produto existente, se não encontrar, lança exceção (reaproveitando nosso método!)
        Produto produtoExistente = buscarPorId(id);

        Categoria novaCategoria = categoriaService.buscarPorId(produtoDTO.getCategoriaId());

        produtoExistente.setNome(produtoDTO.getNome());
        produtoExistente.setDescricao(produtoDTO.getDescricao());
        produtoExistente.setPreco(produtoDTO.getPreco());
        produtoExistente.setCodigoBarras(produtoDTO.getCodigoBarras());
        produtoExistente.setQuantidadeEstoque(produtoDTO.getQuantidadeEstoque());
        produtoExistente.setCategoria(novaCategoria); // Associa a nova categoria

        // 4. Salva as alterações
        return produtoRepository.save(produtoExistente);
    }

    //DELETAR PRODUTO
    public void deletarProduto(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }
}
