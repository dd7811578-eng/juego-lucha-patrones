package com.juego.patrones.strategy;

import java.util.Random;

/**
 * Estrategia de ataque mágico.
 * Daño base: 10-40. Con 50% de probabilidad y mana suficiente, potencia el hechizo (+15).
 */
public class AtaqueMagico implements EstrategiaAtaque {

    private int mana;
    private final Random rand;

    public AtaqueMagico() {
        this.mana = 100;
        this.rand = new Random();
    }

    public AtaqueMagico(int mana, Random rand) {
        this.mana = mana;
        this.rand = rand;
    }

    @Override
    public int calcularDano() {
        int dano = rand.nextInt(31) + 10; // 10-40
        if (mana >= 20 && rand.nextBoolean()) {
            dano += 15;
            mana -= 20;
            System.out.println("[¡Hechizo potenciado! Mana restante: " + mana + "]");
        }
        return dano;
    }

    public int getMana() { return mana; }

    @Override
    public String getNombre() { return "Magia"; }
}
