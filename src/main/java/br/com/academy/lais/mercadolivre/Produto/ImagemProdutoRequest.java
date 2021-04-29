package br.com.academy.lais.mercadolivre.Produto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagemProdutoRequest {
    @NotNull @Size(min = 1)
    private List<MultipartFile> imagens = new ArrayList<>();

    public ImagemProdutoRequest(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens(){
        return imagens;
    }
}
