package br.com.academy.lais.mercadolivre.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    public String cadastrar(@RequestBody @Valid CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaRequest.converter(categoriaRepository);
        categoriaRepository.save((categoria));
        return "cadastrado";
    }
}
