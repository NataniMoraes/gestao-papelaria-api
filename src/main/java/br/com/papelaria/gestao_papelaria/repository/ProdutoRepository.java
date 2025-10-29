//O Repository é uma interface para os métodos de interação com a tabela. Interfaces definem "contratos" de métodos, mas não os implementam.

package br.com.papelaria.gestao_papelaria.repository;

import br.com.papelaria.gestao_papelaria.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
