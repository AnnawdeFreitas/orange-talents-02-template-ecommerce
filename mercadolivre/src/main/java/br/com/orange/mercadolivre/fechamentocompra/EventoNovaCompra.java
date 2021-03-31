package br.com.orange.mercadolivre.fechamentocompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventoNovaCompra {
    @Autowired
    private Set<EventoCompraSucesso> eventosCompraSucesso;

    public void processa(Compra compra) {
        if(compra.processadaComSucesso()) {
            eventosCompraSucesso.forEach(evento -> evento.processa(compra));
        }
        else {
            //implementar retorno para caso de falha.
        }
    }
}
