package br.com.academy.lais.mercadolivre.Pagamento;

import br.com.academy.lais.mercadolivre.Compra.Compra;
import br.com.academy.lais.mercadolivre.Compra.Gateway;
import br.com.academy.lais.mercadolivre.Compra.StatusCompra;
import br.com.academy.lais.mercadolivre.Usuario.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PagamentoController {

    @PersistenceContext
    EntityManager entityManager;

    //@Autowired
    //EmailService emailService;

    @PostMapping(value = "/retorno-pagseguro/{id}")
    @Transactional
    public String cadastrar(@PathVariable("id") Long idCompra, @RequestBody @Valid PagseguroRequest pagseguroRequest, UriComponentsBuilder uriComponentsBuilder){

        return criaPagamento(idCompra, pagseguroRequest,uriComponentsBuilder);
    }
    @PostMapping(value = "/retorno-paypal/{id}")
    @Transactional
    public String cadastrar(@PathVariable("id") Long idCompra, @RequestBody @Valid PaypalRequest pagseguroRequest, UriComponentsBuilder uriComponentsBuilder){

        return criaPagamento(idCompra, pagseguroRequest,uriComponentsBuilder);
    }

    public String criaPagamento(Long idCompra, RetornoPagamento request, UriComponentsBuilder uriComponentsBuilder) {
        Optional<Usuario> usuarioLogado = (Optional<Usuario>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Compra compra = entityManager.find(Compra.class, idCompra);
        Pagamento pagamento = request.toModel(compra);

         Assert.isTrue(compra.status != StatusCompra.SUCESSO, "Pagamento já processado.");

        compra.atualizaStatus(pagamento);

        entityManager.persist(pagamento);
        entityManager.persist(compra);

        if (compra.status == StatusCompra.SUCESSO) {
            System.out.println(usuarioLogado.get().getUsername() + " - Pagamento realizado com sucesso");
        } else {
            System.out.println(usuarioLogado.get().getUsername() + " - Pagamento com erro. Acesse: " + compra.retornoURL(uriComponentsBuilder));
        }
        return request.toString();
    }
}
