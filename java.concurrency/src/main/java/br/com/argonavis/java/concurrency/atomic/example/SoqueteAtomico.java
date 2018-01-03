package br.com.argonavis.java.concurrency.atomic.example;

import java.util.concurrent.atomic.AtomicReference;

public class SoqueteAtomico {    
    private AtomicReference<Lampada> lampadaRef;
    
    public SoqueteAtomico(Lampada lampada) {
    		this.lampadaRef = new AtomicReference<>(lampada);
    }
    
    public Lampada substituir(Lampada lampadaNova) {
        Lampada lampadaVelha = this.lampadaRef.get();
        while(!lampadaRef.compareAndSet(lampadaVelha, lampadaNova)) {
        	    lampadaVelha = this.lampadaRef.get();
        }
        return lampadaVelha;
    }
    
    public Lampada get() {
    		return lampadaRef.get();
    }
}

