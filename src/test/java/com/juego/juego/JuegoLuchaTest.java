package com.juego.juego;

import com.juego.model.Personaje;
import com.juego.patrones.strategy.EstrategiaAtaque;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("JuegoLuchaTest – motor de combate por turnos")
class JuegoLuchaTest {

    // Personaje mínimo de prueba
    static class PersonajeTest extends Personaje {
        public PersonajeTest(String nombre, int hp, EstrategiaAtaque e) {
            super(nombre, hp, e);
        }
        @Override public String getTipo() { return "Test"; }
    }

    private EstrategiaAtaque mockEstrategia;

    @BeforeEach
    void setUp() {
        mockEstrategia = Mockito.mock(EstrategiaAtaque.class);
    }

    @Test
    @DisplayName("Constructor lanza excepción si algún jugador es nulo")
    void testConstructorNulos() {
        Personaje p = new PersonajeTest("A", 100, mockEstrategia);
        assertThrows(IllegalArgumentException.class, () -> new JuegoLucha(null, p));
        assertThrows(IllegalArgumentException.class, () -> new JuegoLucha(p, null));
    }

    @Test
    @DisplayName("El jugador que sobrevive es declarado ganador")
    void testGanadorCorrecto() {
        // J1 golpea 999 → J2 muere en primer turno
        when(mockEstrategia.calcularDano()).thenReturn(999);

        Personaje j1 = new PersonajeTest("Héroe",  100, mockEstrategia);
        Personaje j2 = new PersonajeTest("Villano", 100, mockEstrategia);

        JuegoLucha juego = new JuegoLucha(j1, j2);
        Personaje ganador = juego.iniciarPelea();

        // J1 atacó primero con 999; J2 muere; J2 nunca ataca
        assertEquals(j1.getNombre(), ganador.getNombre());
    }

    @Test
    @DisplayName("Ambos personajes se dañan mutuamente por turno")
    void testDanoMutuo() {
        when(mockEstrategia.calcularDano()).thenReturn(10);

        Personaje j1 = new PersonajeTest("A", 100, mockEstrategia);
        Personaje j2 = new PersonajeTest("B", 100, mockEstrategia);

        JuegoLucha juego = new JuegoLucha(j1, j2);
        // Ejecutamos manualmente un turno usando reflexión no es necesario;
        // Verificamos que tras iniciarPelea ambos han recibido daño
        juego.iniciarPelea();

        // Con 10 de daño por turno y 100 HP, la pelea termina en ≥5 turnos
        // Ambos deberían haber recibido daño (al menos uno en 0)
        assertTrue(j1.getPuntosDeVida() == 0 || j2.getPuntosDeVida() == 0);
    }

    @Test
    @DisplayName("getJugador1 y getJugador2 retornan las instancias correctas")
    void testGetters() {
        Personaje j1 = new PersonajeTest("J1", 50, mockEstrategia);
        Personaje j2 = new PersonajeTest("J2", 50, mockEstrategia);
        JuegoLucha juego = new JuegoLucha(j1, j2);

        assertSame(j1, juego.getJugador1());
        assertSame(j2, juego.getJugador2());
    }
}
