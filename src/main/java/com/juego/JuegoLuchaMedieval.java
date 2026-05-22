package com.juego;

import com.juego.juego.JuegoLucha;
import com.juego.model.Personaje;
import com.juego.patrones.factory.*;

import java.util.Scanner;

/**
 * Punto de entrada del juego de lucha medieval.
 */
public class JuegoLuchaMedieval {

    private static PersonajeFactory elegirFactory(int opcion) {
        return switch (opcion) {
            case 2 -> new MagoFactory();
            case 3 -> new ArqueroFactory();
            default -> new GuerreroFactory();
        };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== JUEGO DE LUCHA MEDIEVAL ===\n");
        System.out.println("Tipos de personaje:");
        System.out.println("  1. Guerrero ⚔️   HP: 120 | Estrategia: Espada | Armadura: 5");
        System.out.println("  2. Mago     🔮   HP:  80 | Estrategia: Magia  | Mana: 100");
        System.out.println("  3. Arquero  🏹   HP: 100 | Estrategia: Arco   | Flechas: 30");

        System.out.print("\nJugador 1 – Introduce tu nombre: ");
        String nombre1 = scanner.nextLine();
        System.out.print("Jugador 1 – Elige tipo (1/2/3): ");
        int tipo1 = scanner.nextInt(); scanner.nextLine();

        System.out.print("\nJugador 2 – Introduce tu nombre: ");
        String nombre2 = scanner.nextLine();
        System.out.print("Jugador 2 – Elige tipo (1/2/3): ");
        int tipo2 = scanner.nextInt(); scanner.nextLine();

        Personaje p1 = elegirFactory(tipo1).crearPersonaje(nombre1);
        Personaje p2 = elegirFactory(tipo2).crearPersonaje(nombre2);

        System.out.println("\nPersonaje 1: " + p1.getNombre() + " – " + p1.getTipo());
        System.out.println("Personaje 2: " + p2.getNombre() + " – " + p2.getTipo());

        new JuegoLucha(p1, p2).iniciarPelea();
        scanner.close();
    }
}
