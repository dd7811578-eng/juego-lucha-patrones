package com.juego.patrones.strategy;

import java.util.Random;

/**
 * Estrategia de ataque cuerpo a cuerpo con espada.
 * Daño base: 15-35. Con 33% de probabilidad ejecuta un golpe de escudo (+10).
 */
public class AtaqueCuerpoACuerpo implements EstrategiaAtaque {

    private final Random rand;

    public AtaqueCuerpoACuerpo() {
        this.rand = new Random();
    }

    // Constructor para tests (inyección de Random)
    public AtaqueCuerpoACuerpo(Random rand) {
        this.rand = rand;
    }

    @Override
    public int calcularDano() {
        int dano = rand.nextInt(21) + 15; // 15-35
        if (rand.nextInt(3) == 0) {
            System.out.println("[¡Golpe de escudo!]");
            dano += 10;
        }
        return dano;
    }

    @Override
    public String getNombre() { return "Espada"; }
}
