package br.com.academy.lais.mercadolivre.Pagamento;

import br.com.academy.lais.mercadolivre.Compra.Compra;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class PaypalRequest implements RetornoPagamento{
    @NotEmpty
    public String idPagamento;
    @Min(0) @Max(1)
    public Integer status;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public LocalDateTime dataPagamento = LocalDateTime.now();

    public Pagamento toModel(Compra compra){
        return  new Pagamento(compra, idPagamento, status, dataPagamento);
    }

    @Override
    public String toString() {
        return "PaypalRequest{" +
                "idPagamento='" + idPagamento + '\'' +
                ", status=" + status +
                ", dataPagamento=" + dataPagamento +
                '}';
    }
}
