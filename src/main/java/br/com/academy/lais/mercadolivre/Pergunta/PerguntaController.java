package br.com.academy.lais.mercadolivre.Pergunta;

import br.com.academy.lais.mercadolivre.EnvioEmail.Email;
import br.com.academy.lais.mercadolivre.EnvioEmail.EmailService;
import br.com.academy.lais.mercadolivre.Produto.Produto;
import br.com.academy.lais.mercadolivre.Usuario.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/pergunta")
public class PerguntaController {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    Email email;
    @Autowired
    EmailService emailService;

    @PostMapping(value = "{id}")
    @Transactional
    public String cadastrar(@PathVariable("id") Long id, @RequestBody @Valid PerguntaRequest perguntaRequest) {
        Pergunta pergunta = perguntaRequest.toModel();
        Produto produto = entityManager.find(Produto.class, id);
        Optional<Usuario> usuarioLogado = (Optional<Usuario>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (usuarioLogado.isEmpty())
            Assert.isNull("Usuário não cadastrado.");

        pergunta.setProduto(produto);
        pergunta.setUsuario(usuarioLogado.get());
        entityManager.persist(pergunta);

        String corpoEmail = "De: "+ usuarioLogado.get().getUsername();
        corpoEmail += " / Mensagem: " + perguntaRequest.getDescricao() +"";

        Email email = new Email(produto.getUsuario().getUsername(), perguntaRequest.getTitulo(), corpoEmail);
        emailService.sendEmail(email);

        return "cadastrado e email enviado";
    }
}
