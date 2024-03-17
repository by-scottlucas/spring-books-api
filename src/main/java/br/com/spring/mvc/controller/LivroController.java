package br.com.spring.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.mvc.model.Livro;
import br.com.spring.mvc.model.dto.LivroDTO;
import br.com.spring.mvc.service.LivroService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController()
@RequestMapping("/api/v1/livros")
public class LivroController {

    @Autowired
    LivroService livroService;

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public List<Livro> listarLivros() {
        return livroService.list();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Livro cadastrarLivro(@RequestBody LivroDTO livro) {
        return livroService.create(livro);
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Livro listarLivro(@PathVariable() Long id) throws NotFoundException {
        return livroService.read(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Livro atualizarLivro(@PathVariable() Long id, @RequestBody() LivroDTO livro) throws NotFoundException {
        return livroService.update(id, livro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluirLivro(@PathVariable() Long id) {
        livroService.delete(id);
    }

}
