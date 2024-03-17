package br.com.spring.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.spring.mvc.model.Livro;
import br.com.spring.mvc.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    LivroRepository livroRepository;

    public List<Livro> list() {
        return livroRepository.findAll();
    }

    public Livro create(Livro data) {
        return livroRepository.save(data);
    }

    public Livro read(Long id) throws NotFoundException {
        return livroRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    public Livro update(Long id, Livro data) throws NotFoundException {
        return livroRepository.findById(id)
                .map(response -> {
                    Livro livro = new Livro();
                    livro.setTitulo(data.getTitulo());
                    livro.setAutor(data.getAutor());
                    livro.setEditora(data.getEditora());
                    livro.setDescricao(data.getDescricao());
                    livro.setGenero(data.getGenero());
                    livro.setLancamento(data.getLancamento());
                    return livroRepository.save(response);
                })
                .orElseThrow(() -> new NotFoundException());
    }

    public void delete(Long id) {
        livroRepository.deleteById(id);
    }
}
