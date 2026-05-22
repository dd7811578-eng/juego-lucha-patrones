package com.juego.patrones;

import com.juego.patrones.strategy.AtaqueArco;
import com.juego.patrones.strategy.AtaqueCuerpoACuerpo;
import com.juego.patrones.strategy.AtaqueMagico;
import org.junit.jupiter.api.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("EstrategiaAtaqueTest – las tres estrategias concretas")
class EstrategiaAtaqueTest {

    // ── AtaqueCuerpoACuerpo ───────────────────────────────────────────────────

    @Nested
    @DisplayName("AtaqueCuerpoACuerpo")
    class CuerpoACuerpoTest {

        @Test
        @DisplayName("Daño normal está en rango 15-35")
        void testDanoEnRango() {
            // Forzamos que rand.nextInt(3) != 0 para evitar golpe de escudo
            Random rand = mock(Random.class);
            when(rand.nextInt(21)).thenReturn(10); // dano base = 10+15 = 25
            when(rand.nextInt(3)).thenReturn(1);   // sin golpe de escudo

            AtaqueCuerpoACuerpo estrategia = new AtaqueCuerpoACuerpo(rand);
            int dano = estrategia.calcularDano();

            assertEquals(25, dano);
        }

        @Test
        @DisplayName("Golpe de escudo añade +10 al daño")
        void testGolpeEscudo() {
            Random rand = mock(Random.class);
            when(rand.nextInt(21)).thenReturn(5);  // base = 20
            when(rand.nextInt(3)).thenReturn(0);   // golpe de escudo

            AtaqueCuerpoACuerpo estrategia = new AtaqueCuerpoACuerpo(rand);
            assertEquals(30, estrategia.calcularDano()); // 20 + 10
        }

        @Test
        @DisplayName("getNombre retorna 'Espada'")
        void testNombre() {
            assertEquals("Espada", new AtaqueCuerpoACuerpo().getNombre());
        }
    }

    // ── AtaqueMagico ──────────────────────────────────────────────────────────

    @Nested
    @DisplayName("AtaqueMagico")
    class MagicoTest {

        @Test
        @DisplayName("Hechizo potenciado añade +15 y consume 20 mana")
        void testHechizoPotenciado() {
            Random rand = mock(Random.class);
            when(rand.nextInt(31)).thenReturn(10); // base = 20
            when(rand.nextBoolean()).thenReturn(true);

            AtaqueMagico estrategia = new AtaqueMagico(100, rand);
            int dano = estrategia.calcularDano();

            assertEquals(35, dano);         // 20 + 15
            assertEquals(80, estrategia.getMana()); // 100 - 20
        }

        @Test
        @DisplayName("Sin mana suficiente no se potencia el hechizo")
        void testSinManaNoPotencia() {
            Random rand = mock(Random.class);
            when(rand.nextInt(31)).thenReturn(10);
            when(rand.nextBoolean()).thenReturn(true);

            AtaqueMagico estrategia = new AtaqueMagico(10, rand); // mana < 20
            int dano = estrategia.calcularDano();

            assertEquals(20, dano);          // sin bonus
            assertEquals(10, estrategia.getMana()); // mana intacto
        }

        @Test
        @DisplayName("getNombre retorna 'Magia'")
        void testNombre() {
            assertEquals("Magia", new AtaqueMagico().getNombre());
        }
    }

    // ── AtaqueArco ────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("AtaqueArco")
    class ArcoTest {

        @Test
        @DisplayName("Disparo normal está en rango 12-28 y reduce flechas")
        void testDisparoNormal() {
            Random rand = mock(Random.class);
            when(rand.nextInt(17)).thenReturn(5);  // base = 17
            when(rand.nextInt(5)).thenReturn(1);   // sin crítico

            AtaqueArco estrategia = new AtaqueArco(5, rand);
            int dano = estrategia.calcularDano();

            assertEquals(17, dano);
            assertEquals(4, estrategia.getFlechas());
        }

        @Test
        @DisplayName("Disparo crítico duplica el daño")
        void testDisparoCritico() {
            Random rand = mock(Random.class);
            when(rand.nextInt(17)).thenReturn(8);  // base = 20
            when(rand.nextInt(5)).thenReturn(0);   // crítico

            AtaqueArco estrategia = new AtaqueArco(10, rand);
            assertEquals(40, estrategia.calcularDano()); // 20 * 2
        }

        @Test
        @DisplayName("Sin flechas ataca con daga (5 puntos) y no reduce flechas")
        void testSinFlechas() {
            AtaqueArco estrategia = new AtaqueArco(0, new Random());
            assertEquals(5, estrategia.calcularDano());
            assertEquals(0, estrategia.getFlechas());
        }

        @Test
        @DisplayName("getNombre retorna 'Arco'")
        void testNombre() {
            assertEquals("Arco", new AtaqueArco().getNombre());
        }
    }
}
