package br.com.academy.lais.mercadolivre.Hanking;

import br.com.academy.lais.mercadolivre.NotaFiscal.NotaFiscalRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/notafiscal")
public class HankingController {
    @PostMapping()
    public String cadastrar(@RequestBody @Valid HankingRequest hankingRequest) {
        return hankingRequest.toString();
    }
}

