package br.com.academy.lais.mercadolivre.NotaFiscal;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notafiscal")
public class NotaFiscalController {

    @PostMapping()
    public String cadastrar(@RequestBody @Valid NotaFiscalRequest notaFiscalRequest){
        return notaFiscalRequest.toString();
    }

}
