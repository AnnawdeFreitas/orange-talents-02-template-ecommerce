package br.com.orange.mercadolivre.fechamentocompra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagSeguroRequest implements RetornoGatewayPagamento {

    @NotBlank
    private String idTransacao;
    @NotNull
    private StatusRetornoPagSeguro status;

    public RetornoPagSeguroRequest(@NotBlank String idTransacao,
                                   StatusRetornoPagSeguro status) {
        super();
        this.idTransacao = idTransacao;
        this.status = status;
    }
    @Override
    public String toString() {
        return "RetornoPagseguroRequest [idTransacao=" + idTransacao
                + ", status=" + status + "]";
    }
    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(),idTransacao,compra);
    }

}