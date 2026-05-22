package com.juego.patrones.strategy;

import java.util.Random;

/**
 * Estrategia de ataque a distancia con arco.
 * Daño base: 12-28. 20% de probabilidad de disparo crítico (x2).
 * Máximo 30 flechas; si se agotan, ataca con daga (5 puntos fijos).
 */
public class AtaqueArco implements EstrategiaAtaque {

    private int flechas;
    private final Random rand;

    public AtaqueArco() {
        this.flechas = 30;
        this.rand = new Random();
    }

    public AtaqueArco(int flechas, Random rand) {
        this.flechas = flechas;
        this.rand = rand;
    }

    @Override
    public int calcularDano() {
        if (flechas == 0) {
            System.out.println("[Sin flechas. Ataca con daga (daño: 5)]");
            return 5;
        }
        int dano = rand.nextInt(17) + 12; // 12-28
        if (rand.nextInt(5) == 0) {
            System.out.println("[¡Disparo crítico!]");
            dano *= 2;
        }
        flechas--;
        return dano;
    }

    public int getFlechas() { return flechas; }

    @Override
    public String getNombre() { return "Arco"; }
}
