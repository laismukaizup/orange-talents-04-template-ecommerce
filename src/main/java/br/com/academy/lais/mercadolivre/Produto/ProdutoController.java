package br.com.academy.lais.mercadolivre.Produto;

import br.com.academy.lais.mercadolivre.Categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    CaracteristicaRepository caracteristicaRepository;

    @PostMapping
    public String cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest) {

        Produto produto = produtoRequest.converter(categoriaRepository);

        List<Caracteristica> caracteristicaList = produtoRequest.getCaracteristicas().stream()
                .map(Caracteristica::new).collect(Collectors.toList());

        for (Caracteristica caracteristica:caracteristicaList) {
            caracteristica.setProduto(produto);
        }
        produtoRepository.save(produto);
        caracteristicaRepository.saveAll(caracteristicaList);
        return "cadastrado";
    }
}
