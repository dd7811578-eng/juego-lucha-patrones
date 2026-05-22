package com.juego.model;

import com.juego.patrones.strategy.AtaqueArco;
import com.juego.patrones.strategy.EstrategiaAtaque;

public class Arquero extends Personaje {

    public Arquero(String nombre) {
        super(nombre, 100, new AtaqueArco());
    }

    public Arquero(String nombre, EstrategiaAtaque estrategia) {
        super(nombre, 100, estrategia);
    }

    @Override
    public String getTipo() {
        return "Arquero 🏹  (HP: 100 | Estrategia: Arco | Flechas: 30)";
    }
}
