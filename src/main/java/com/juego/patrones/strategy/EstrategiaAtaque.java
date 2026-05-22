package com.juego.patrones.strategy;

/**
 * Patrón Strategy: interfaz que define el comportamiento de ataque.
 * Permite cambiar dinámicamente cómo un personaje calcula su daño.
 */
public interface EstrategiaAtaque {
    /**
     * Calcula el daño del ataque.
     * @return puntos de daño causados (siempre >= 0)
     */
    int calcularDano();

    /**
     * Nombre descriptivo de la estrategia (para mostrar en UI).
     */
    String getNombre();
}
