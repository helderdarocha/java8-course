/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdc.java9.examples.concurrency;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;

/**
 *
 * @author helderdarocha
 */
public class Assinante<T> implements Flow.Subscriber<T> {
    
    private Subscription assinatura;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("onSubscribe: " + subscription);
        assinatura = subscription;
        assinatura.request(1);
    }

    @Override
    public void onNext(T item) {
        System.out.println("onNext: " + item);
        assinatura.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("onError: " + throwable);
        synchronized("A") { "A".notifyAll(); }
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete");
        synchronized("A") { "A".notifyAll(); }
    }

}