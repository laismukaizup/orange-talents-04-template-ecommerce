package br.com.academy.lais.mercadolivre.Pagamento;

import br.com.academy.lais.mercadolivre.Compra.Compra;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pagamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull @ManyToOne
    public Compra compra;
    @NotEmpty
    public String idPagamento;
    @Min(0) @Max(1)  //1 para sucesso e o número 0 para erro.
    public Integer statusPagamento;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public LocalDateTime dataProcessamento;

    public Integer getStatusPagamento() {
        return statusPagamento;
    }

    @Deprecated
    public Pagamento(){}

    public Pagamento(Compra compra, String idPagamento, Integer statusPagamento,
                          LocalDateTime dataProcessamento) {
        this.compra = compra;
        this.idPagamento = idPagamento;
        this.statusPagamento = statusPagamento;
        this.dataProcessamento = dataProcessamento;
    }

    public boolean statusPagamentoSucessso() {
        if (statusPagamento.equals(1))
            return true;
        return false;
    }
}
