package br.com.orange.mercadolivre.adicionapergunta;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FakeMail implements Mailer{

    @Override
    public void send(String body, String subject, String nameFrom, String from,
                     String to) {
        System.out.println(body);
        System.out.println(subject);
        System.out.println(nameFrom);
        System.out.println(from);
        System.out.println(to);
    }

}