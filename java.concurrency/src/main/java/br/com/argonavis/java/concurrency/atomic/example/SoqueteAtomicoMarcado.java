package br.com.argonavis.java.concurrency.atomic.example;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class SoqueteAtomicoMarcado {    
    private final AtomicMarkableReference<Lampada> lampadaRef;
    private static final boolean VENCIDA = true;
    private static final boolean NOVA    = false;
    private volatile String nome;
    
    public SoqueteAtomicoMarcado(Lampada lampada, String nome) {
    		this.lampadaRef = new AtomicMarkableReference<>(lampada, NOVA);
    		this.nome = nome;
    }
    
    // Permite a troca apenas de lampadas marcadas como VENCIDA
    public Lampada substituir(Lampada lampadaNova) {
    		boolean[] marker = new boolean[1];
        Lampada lampadaVelha = this.lampadaRef.get(marker);
        while(!lampadaRef.compareAndSet(lampadaVelha, lampadaNova, VENCIDA, NOVA)) {
        	    lampadaVelha = this.lampadaRef.get(marker);
        	    if(marker[0] == NOVA) {
        	    	    System.out.println(Thread.currentThread().getName() + " FALHOU. " + nome + " contém NOVA " + lampadaVelha);
        	    	    break;
        	    }
        }
        return lampadaVelha;
    }
    
    public Lampada get() {
    		return lampadaRef.getReference();
    }
    
    public String getNome() {
    		return nome;
    }
    
    public void marcarLampadaParaTroca() {
      	boolean[] marker = new boolean[1];
    	    Lampada lampadaVelha = this.lampadaRef.get(marker);
    		while(!lampadaRef.attemptMark(lampadaVelha, VENCIDA)) {
    			lampadaVelha = this.lampadaRef.get(marker);
    		}
    }
}

