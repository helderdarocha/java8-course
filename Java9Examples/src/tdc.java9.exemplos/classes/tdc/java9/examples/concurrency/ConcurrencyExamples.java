/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdc.java9.examples.concurrency;

import java.util.Arrays;
import java.util.concurrent.SubmissionPublisher;

/**
 *
 * @author helderdarocha
 */
public class ConcurrencyExamples {
    public static void main(String[] args) {
        // streams reativos
        /*
        Componentes são Publisher, Subscriber e Subscription
        Subscriber assina uma Subscription pelo Publisher para receber um stream
        
        Publisher::subscribe
        Subscriber::onNext
        Subscriber::onSubscription
        Subscription::request
        
        JEP 266: More Concurrency Updates
        
        pulish-subscribe fw para streams reativos
        
        Arquiteturas orientadas a streams capturam e processam dados em tempo real, rapidamente podem modificar sistemas baseados nos resultados.
        Volumes de dados em que desconhece a quantidade
        Assíncrono
        reactive-streams.org - padrão para proce async
        
        Um reactive stream possui meios de sinalizar à fonte
        para controlar a produção de dados se o destino fica sobrecarregado
        Como uma torneira.
        Fechar a válviula aumneta a pressão na fonte (back pressure)
        diminuindo esforco do destino
        
        Objetivo é controlar a troca de dados em streams através de limites
        assíncronos (dados entre threads) e nquanto garante que o destino 
        não precise bufferizar quantidades arbitrarias de dados.
        
        API Flow
        */
        
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        Assinante<String> assinante = new Assinante<>();
        publisher.subscribe(assinante);
        
        System.out.println("Publicando dados...");
        String[] dados = { "Um", "dois", "três", "quatro"};
        Arrays.asList(dados).stream().forEach(i -> publisher.submit(i));
        publisher.close();
        
        try {
         synchronized("A") { "A".wait();}
        } catch (InterruptedException e) {}
    }
}
