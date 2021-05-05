package br.com.academy.lais.mercadolivre.Pagamento;

import br.com.academy.lais.mercadolivre.Compra.Compra;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class PagseguroRequest implements RetornoPagamento {
    @NotEmpty
    public String idPagamento;
    public StatusPagSeguro status;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public LocalDateTime dataPagamento = LocalDateTime.now();

    public Pagamento toModel(Compra compra){
        return  new Pagamento(compra, idPagamento, status.converter(), dataPagamento);
    }

    @Override
    public String toString() {
        return "PagseguroRequest{" +
                "idPagamento='" + idPagamento + '\'' +
                ", status=" + status +
                ", dataPagamento=" + dataPagamento +
                '}';
    }
}
