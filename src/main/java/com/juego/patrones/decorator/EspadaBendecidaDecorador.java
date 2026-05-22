package com.juego.patrones.decorator;

import com.juego.patrones.strategy.EstrategiaAtaque;

public class EspadaBendecidaDecorador extends DecoradorArma {
    private static final int BONUS = 8;

    public EspadaBendecidaDecorador(EstrategiaAtaque estrategiaBase) {
        super(estrategiaBase);
    }

    @Override
    public int calcularDano() {
        int dano = estrategiaBase.calcularDano() + BONUS;
        System.out.println("[Espada Bendecida: +" + BONUS + " daño sagrado]");
        return dano;
    }

    @Override
    public String getNombre() {
        return estrategiaBase.getNombre() + " + Espada Bendecida";
    }
}
