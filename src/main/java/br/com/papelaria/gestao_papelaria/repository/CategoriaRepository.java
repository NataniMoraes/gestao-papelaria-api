package br.com.papelaria.gestao_papelaria.repository;


import br.com.papelaria.gestao_papelaria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
