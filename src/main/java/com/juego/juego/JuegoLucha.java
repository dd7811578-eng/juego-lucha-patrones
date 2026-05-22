package com.juego.juego;

import com.juego.model.Personaje;

/**
 * Motor del juego de lucha por turnos.
 * Coordina los turnos hasta que uno de los dos personajes quede sin vida.
 */
public class JuegoLucha {

    private final Personaje jugador1;
    private final Personaje jugador2;

    public JuegoLucha(Personaje jugador1, Personaje jugador2) {
        if (jugador1 == null || jugador2 == null)
            throw new IllegalArgumentException("Los jugadores no pueden ser nulos.");
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    /**
     * Ejecuta la pelea completa y retorna el ganador.
     * @return el Personaje ganador
     */
    public Personaje iniciarPelea() {
        System.out.println("\n========================================");
        System.out.println("  ¡Comienza el combate medieval!");
        System.out.println("  " + jugador1.getNombre() + " vs " + jugador2.getNombre());
        System.out.println("========================================\n");

        int turno = 1;
        while (jugador1.estaVivo() && jugador2.estaVivo()) {
            System.out.println("--- Turno " + turno + " ---");
            ejecutarTurno(jugador1, jugador2);
            if (jugador2.estaVivo()) {
                ejecutarTurno(jugador2, jugador1);
            }
            turno++;
            System.out.println();
        }

        Personaje ganador = jugador1.estaVivo() ? jugador1 : jugador2;
        System.out.println("========================================");
        System.out.println("  🏆 " + ganador.getNombre() + " ha ganado la pelea!");
        System.out.println("========================================");
        return ganador;
    }

    private void ejecutarTurno(Personaje atacante, Personaje defensor) {
        System.out.println("Turno de " + atacante.getNombre()
                + " | HP defensor (" + defensor.getNombre() + "): "
                + defensor.getPuntosDeVida());
        atacante.atacar(defensor);
        System.out.println(defensor.getNombre() + " ahora tiene "
                + defensor.getPuntosDeVida() + " HP.");
    }

    public Personaje getJugador1() { return jugador1; }
    public Personaje getJugador2() { return jugador2; }
}
