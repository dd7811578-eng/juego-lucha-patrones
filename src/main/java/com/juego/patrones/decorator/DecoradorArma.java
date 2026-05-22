package com.juego.patrones.decorator;

import com.juego.patrones.strategy.EstrategiaAtaque;

/**
 * Patrón Decorator: envuelve una EstrategiaAtaque y añade bonificaciones.
 */
public abstract class DecoradorArma implements EstrategiaAtaque {
    protected final EstrategiaAtaque estrategiaBase;

    public DecoradorArma(EstrategiaAtaque estrategiaBase) {
        this.estrategiaBase = estrategiaBase;
    }
}
