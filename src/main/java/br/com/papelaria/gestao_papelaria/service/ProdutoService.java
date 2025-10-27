//SERVICE: O service é o cérebro da apliacação, é a parte que orquestre as operações.

package br.com.papelaria.gestao_papelaria.service;
import br.com.papelaria.gestao_papelaria.model.Produto;
import br.com.papelaria.gestao_papelaria.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service //Marcar a classe como um componente de serviço
public class ProdutoService {

    @Autowired //Injeção de depedência
    private ProdutoRepository produtoRepository;

    public Produto criarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos(){
        return produtoRepository.findAll();
    }
}
