package com.juego.patrones.decorator;

import com.juego.patrones.strategy.EstrategiaAtaque;

public class AmuletoFuegoDecorador extends DecoradorArma {
    private static final int BONUS = 5;

    public AmuletoFuegoDecorador(EstrategiaAtaque estrategiaBase) {
        super(estrategiaBase);
    }

    @Override
    public int calcularDano() {
        int dano = estrategiaBase.calcularDano() + BONUS;
        System.out.println("[Amuleto de Fuego: +" + BONUS + " daño ígneo]");
        return dano;
    }

    @Override
    public String getNombre() {
        return estrategiaBase.getNombre() + " + Amuleto de Fuego";
    }
}
