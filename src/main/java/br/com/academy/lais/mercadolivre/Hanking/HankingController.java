package br.com.academy.lais.mercadolivre.Hanking;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/hanking")
public class HankingController {
    @PostMapping()
    public String cadastrar(@RequestBody @Valid HankingRequest hankingRequest) {
        return hankingRequest.toString();
    }
}

