package br.com.academy.lais.mercadolivre.DetalheProduto;

import br.com.academy.lais.mercadolivre.Produto.Produto;
import br.com.academy.lais.mercadolivre.Produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/detalhe")
public class DetalheProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping(value = "{id}")
    public ResponseEntity<ProdutoResponse> detalhar(@PathVariable("id") Long idProduto) {
        Optional<Produto> possivelProduto = produtoRepository.findById(idProduto);
        if (possivelProduto.isPresent()) {
            ProdutoResponse pr = new ProdutoResponse(possivelProduto.get());
            return ResponseEntity.ok(pr);
        }
        return ResponseEntity.notFound().build();
    }
}
