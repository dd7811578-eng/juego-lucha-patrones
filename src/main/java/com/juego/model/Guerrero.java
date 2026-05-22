package com.juego.model;

import com.juego.patrones.strategy.AtaqueCuerpoACuerpo;
import com.juego.patrones.strategy.EstrategiaAtaque;

public class Guerrero extends Personaje {
    private final int armadura;

    public Guerrero(String nombre) {
        super(nombre, 120, new AtaqueCuerpoACuerpo());
        this.armadura = 5;
    }

    public Guerrero(String nombre, EstrategiaAtaque estrategia) {
        super(nombre, 120, estrategia);
        this.armadura = 5;
    }

    @Override
    public void recibirDano(int dano) {
        int danoReal = Math.max(0, dano - armadura);
        super.recibirDano(danoReal);
        System.out.println("  [Armadura absorbe " + armadura + " puntos]");
    }

    public int getArmadura() { return armadura; }

    @Override
    public String getTipo() {
        return "Guerrero ⚔️  (HP: 120 | Estrategia: Espada | Armadura: 5)";
    }
}
