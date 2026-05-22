package com.juego.model;

import com.juego.patrones.strategy.EstrategiaAtaque;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("PersonajeTest – clase base abstracta")
class PersonajeTest {

    // Subclase concreta mínima para testear Personaje directamente
    static class PersonajeSimple extends Personaje {
        public PersonajeSimple(String nombre, int hp, EstrategiaAtaque e) {
            super(nombre, hp, e);
        }
        @Override public String getTipo() { return "Simple"; }
    }

    private EstrategiaAtaque estrategiaMock;
    private PersonajeSimple sujeto;
    private PersonajeSimple oponente;

    @BeforeEach
    void setUp() {
        estrategiaMock = Mockito.mock(EstrategiaAtaque.class);
        sujeto   = new PersonajeSimple("Thor",  100, estrategiaMock);
        oponente = new PersonajeSimple("Loki",  100, estrategiaMock);
    }

    // ── Creación ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Constructor asigna nombre y HP correctamente")
    void testCreacionPersonaje() {
        assertEquals("Thor", sujeto.getNombre());
        assertEquals(100,    sujeto.getPuntosDeVida());
        assertTrue(sujeto.estaVivo());
    }

    @Test
    @DisplayName("Constructor lanza excepción con nombre vacío")
    void testNombreVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new PersonajeSimple("", 100, estrategiaMock));
    }

    @Test
    @DisplayName("Constructor lanza excepción con HP <= 0")
    void testHpCeroLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new PersonajeSimple("X", 0, estrategiaMock));
    }

    // ── recibirDano ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("recibirDano reduce HP correctamente")
    void testRecibirDanoReduceHp() {
        sujeto.recibirDano(30);
        assertEquals(70, sujeto.getPuntosDeVida());
    }

    @Test
    @DisplayName("HP nunca baja de 0")
    void testHpNoNegativo() {
        sujeto.recibirDano(999);
        assertEquals(0,     sujeto.getPuntosDeVida());
        assertFalse(sujeto.estaVivo());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -100})
    @DisplayName("Daño negativo es ignorado")
    void testDanoNegativoIgnorado(int danoNegativo) {
        sujeto.recibirDano(danoNegativo);
        assertEquals(100, sujeto.getPuntosDeVida());
    }

    // ── atacar / Strategy ─────────────────────────────────────────────────────

    @Test
    @DisplayName("atacar delega en EstrategiaAtaque y aplica el daño al oponente")
    void testAtacarDelegaEnEstrategia() {
        when(estrategiaMock.calcularDano()).thenReturn(25);

        sujeto.atacar(oponente);

        verify(estrategiaMock, times(1)).calcularDano();
        assertEquals(75, oponente.getPuntosDeVida());
    }

    @Test
    @DisplayName("atacar retorna el daño calculado")
    void testAtacarRetornaDano() {
        when(estrategiaMock.calcularDano()).thenReturn(15);
        int dano = sujeto.atacar(oponente);
        assertEquals(15, dano);
    }

    @Test
    @DisplayName("setEstrategiaAtaque reemplaza la estrategia en tiempo de ejecución")
    void testCambiarEstrategia() {
        EstrategiaAtaque nueva = Mockito.mock(EstrategiaAtaque.class);
        when(nueva.calcularDano()).thenReturn(50);

        sujeto.setEstrategiaAtaque(nueva);
        sujeto.atacar(oponente);

        verify(estrategiaMock, never()).calcularDano();
        verify(nueva, times(1)).calcularDano();
        assertEquals(50, oponente.getPuntosDeVida());
    }
}
