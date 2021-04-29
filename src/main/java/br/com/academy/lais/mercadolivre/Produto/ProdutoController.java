package br.com.academy.lais.mercadolivre.Produto;

import br.com.academy.lais.mercadolivre.Categoria.CategoriaRepository;
import br.com.academy.lais.mercadolivre.Usuario.Usuario;
import br.com.academy.lais.mercadolivre.Usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    CaracteristicaRepository caracteristicaRepository;
    @Autowired
    ImagemProdutoRepository imagemProdutoRepository;
    @Autowired
    ProibeCaracteristicasIguaisParaOMesmoProdutoValidator proibeCaracteristicasIguaisParaOMesmoProdutoValidator;

    @InitBinder(value = "produtoRequest")
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(proibeCaracteristicasIguaisParaOMesmoProdutoValidator);
    }


    @PostMapping
    public String cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest) {

        Produto produto = produtoRequest.converter(categoriaRepository, usuarioRepository);

        List<Caracteristica> caracteristicaList = produtoRequest.getCaracteristicas().stream()
                .map(Caracteristica::new).collect(Collectors.toList());

        for (Caracteristica caracteristica : caracteristicaList) {
            caracteristica.setProduto(produto);
        }
        produtoRepository.save(produto);
        caracteristicaRepository.saveAll(caracteristicaList);
        return "cadastrado";
    }

    @PostMapping(value = "{id}/imagens")
    public String insereImage(@PathVariable("id") Long id, @Valid ImagemProdutoRequest imagemProdutoRequest) {

        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            Optional<Usuario> usuarioLogado = (Optional<Usuario>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (usuarioLogado.isPresent()) {
                if (usuarioLogado.get() == produto.get().getUsuario()) {

                    List<String> listaImagens = imagemProdutoRequest.getImagens().stream().map(i -> i.getOriginalFilename())
                            .collect(Collectors.toList());

                    List<ImagemProduto> imagemProdutoList = listaImagens.stream().map(i -> new ImagemProduto(i, produto.get()))
                            .collect(Collectors.toList());

                    imagemProdutoRepository.saveAll(imagemProdutoList);
                    return "inserido";
                }
                return "Produto não é do usuário logado";
            }
            return "usuário não existe";
        }
        return "Id do produto não cadastrado";
    }
}