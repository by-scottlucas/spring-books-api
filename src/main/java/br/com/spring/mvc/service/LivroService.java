package br.com.spring.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.mvc.exception.NotFoundException;
import br.com.spring.mvc.model.Livro;
import br.com.spring.mvc.model.dto.LivroDTO;
import br.com.spring.mvc.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    LivroRepository livroRepository;

    public List<Livro> list() {
        return livroRepository.findAll();
    }

    public Livro create(LivroDTO data) {
        Livro livro = data.toEntity();
        return livroRepository.save(livro);
    }

    public Livro read(Long id) throws NotFoundException {
        return livroRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Livro update(Long id, LivroDTO data) throws NotFoundException {
        return livroRepository.findById(id)
                .map(livro -> {
                    livro.setTitulo(data.getTitulo());
                    livro.setAutor(data.getAutor());
                    livro.setEditora(data.getEditora());
                    livro.setDescricao(data.getDescricao());
                    livro.setGenero(data.getGenero());
                    livro.setLancamento(data.getLancamento());

                    return livroRepository.save(livro);
                })
                .orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(Long id) {
        livroRepository.deleteById(id);
    }
}
