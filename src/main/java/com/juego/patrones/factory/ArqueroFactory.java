package com.juego.patrones.factory;

import com.juego.model.Arquero;
import com.juego.model.Personaje;

public class ArqueroFactory extends PersonajeFactory {
    @Override
    public Personaje crearPersonaje(String nombre) { return new Arquero(nombre); }
}
