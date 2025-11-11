//CONTROLLER: Porta de entrada da nossa API -- Expor os endpoints e lidar com as requisições HTTP

package br.com.papelaria.gestao_papelaria.controller;

import br.com.papelaria.gestao_papelaria.dto.ProdutoDTO;
import  br.com.papelaria.gestao_papelaria.model.Produto;
import br.com.papelaria.gestao_papelaria.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")

public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody ProdutoDTO produtoDTO) {
        Produto novoProduto = produtoService.criarProduto(produtoDTO);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produtoDTO);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(){
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorId(id);
        // O método buscarPorId já lida com o erro 404,
        // então se o produto for encontrado, podemos apenas retornar OK.
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    //ESTOQUE
    @PatchMapping("/{id}/adicionar-estoque")
    public ResponseEntity<Produto> adicionarEstoque(@PathVariable Long id, @RequestBody Integer quantidade){
        Produto produtoAtualizado = produtoService.adicionarEstoque(id, quantidade);
        return ResponseEntity.ok(produtoAtualizado);
    }
    @PatchMapping("/{id}/remover-estoque")
    public ResponseEntity<Produto> removerEstoque(@PathVariable Long id, @RequestBody Integer quantidade){
        Produto produtoAtualizado = produtoService.removerEstoque(id, quantidade);
        return ResponseEntity.ok(produtoAtualizado);
    }

}
