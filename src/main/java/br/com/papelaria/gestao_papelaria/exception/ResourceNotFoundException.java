package br.com.papelaria.gestao_papelaria.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //Para o status 404 Not Found
public class ResourceNotFoundException extends RuntimeException{ //A exceção personalizada herda o padrão Java
    public ResourceNotFoundException(String message){ //construtor para passar uma mensagem personalizada de erro
        super(message);
    }
}
