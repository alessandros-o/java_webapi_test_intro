package br.com.iteris.universidade.testes.intro.component;

import org.springframework.stereotype.Component;

@Component
public class EmailNotificationComponent {
    public void send(final String email){
        System.out.printf("Sending notification to %s!", email);
    }
}
