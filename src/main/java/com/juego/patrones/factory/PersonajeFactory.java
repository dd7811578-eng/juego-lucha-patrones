package com.juego.patrones.factory;

import com.juego.model.Personaje;

/**
 * Patrón Factory Method: define la interfaz para crear personajes.
 * Cada subclase decide qué tipo concreto instanciar.
 */
public abstract class PersonajeFactory {
    public abstract Personaje crearPersonaje(String nombre);
}
