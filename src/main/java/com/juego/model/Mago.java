package com.juego.model;

import com.juego.patrones.strategy.AtaqueMagico;
import com.juego.patrones.strategy.EstrategiaAtaque;

public class Mago extends Personaje {

    public Mago(String nombre) {
        super(nombre, 80, new AtaqueMagico());
    }

    public Mago(String nombre, EstrategiaAtaque estrategia) {
        super(nombre, 80, estrategia);
    }

    @Override
    public String getTipo() {
        return "Mago 🔮  (HP: 80 | Estrategia: Magia | Mana: 100)";
    }
}
