package com.juego.model;

import com.juego.patrones.strategy.EstrategiaAtaque;

/**
 * Clase base abstracta para todos los personajes del juego.
 * Patrón Strategy: delega el comportamiento de ataque a EstrategiaAtaque.
 */
public abstract class Personaje {

    protected String nombre;
    protected int puntosDeVida;
    protected EstrategiaAtaque estrategiaAtaque;

    public Personaje(String nombre, int puntosDeVida, EstrategiaAtaque estrategiaAtaque) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede ser vacío.");
        if (puntosDeVida <= 0)
            throw new IllegalArgumentException("Los puntos de vida deben ser positivos.");
        this.nombre = nombre;
        this.puntosDeVida = puntosDeVida;
        this.estrategiaAtaque = estrategiaAtaque;
    }

    /**
     * Ataca al oponente usando la estrategia de ataque configurada.
     */
    public int atacar(Personaje oponente) {
        int dano = estrategiaAtaque.calcularDano();
        oponente.recibirDano(dano);
        System.out.println(nombre + " ataca a " + oponente.getNombre()
                + " causando " + dano + " puntos de daño.");
        return dano;
    }

    public void recibirDano(int dano) {
        if (dano < 0) return;
        this.puntosDeVida -= dano;
        if (this.puntosDeVida < 0) this.puntosDeVida = 0;
    }

    public boolean estaVivo() {
        return puntosDeVida > 0;
    }

    public void setEstrategiaAtaque(EstrategiaAtaque estrategia) {
        this.estrategiaAtaque = estrategia;
    }

    public String getNombre() { return nombre; }
    public int getPuntosDeVida() { return puntosDeVida; }

    public abstract String getTipo();
}
