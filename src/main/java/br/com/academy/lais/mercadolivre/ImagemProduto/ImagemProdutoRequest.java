package br.com.academy.lais.mercadolivre.ImagemProduto;

import br.com.academy.lais.mercadolivre.Produto.Produto;
import br.com.academy.lais.mercadolivre.Usuario.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ImagemProdutoRequest {
    @NotNull
    @Size(min = 1)
    private List<MultipartFile> imagens = new ArrayList<>();

    public ImagemProdutoRequest(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

//    public List<MultipartFile> getImagens(){
//        return imagens;
//    }

    public List<ImagemProduto> converter(Produto produto) {
        Optional<Usuario> usuarioLogado = (Optional<Usuario>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> listaImagens = new ArrayList<>();
        if (usuarioLogado.isEmpty())
            Assert.isNull("Usuário não cadastrado.");


        if (usuarioLogado.get().getId() == produto.getUsuario().getId()) {

            listaImagens = imagens.stream().map(i -> i.getOriginalFilename())
                    .collect(Collectors.toList());
        } else
            Assert.state(usuarioLogado.get() == produto.getUsuario(), "Usuário logado é diferente do usuário dono do produto");


        return listaImagens.stream().map(i -> new ImagemProduto(i, produto))
                .collect(Collectors.toList());
    }
}
