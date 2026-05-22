package com.juego.patrones;

import com.juego.patrones.decorator.AmuletoFuegoDecorador;
import com.juego.patrones.decorator.EspadaBendecidaDecorador;
import com.juego.patrones.strategy.EstrategiaAtaque;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("DecoradorArmaTest – patrón Decorator sobre estrategias")
class DecoradorArmaTest {

    private EstrategiaAtaque baseMock;

    @BeforeEach
    void setUp() {
        baseMock = mock(EstrategiaAtaque.class);
        when(baseMock.calcularDano()).thenReturn(20);
        when(baseMock.getNombre()).thenReturn("Base");
    }

    @Test
    @DisplayName("EspadaBendecida añade +8 al daño base")
    void testEspadaBendecida() {
        EspadaBendecidaDecorador espada = new EspadaBendecidaDecorador(baseMock);
        assertEquals(28, espada.calcularDano()); // 20 + 8
    }

    @Test
    @DisplayName("EspadaBendecida delega en la estrategia base")
    void testEspadaDelegaEnBase() {
        EspadaBendecidaDecorador espada = new EspadaBendecidaDecorador(baseMock);
        espada.calcularDano();
        verify(baseMock, times(1)).calcularDano();
    }

    @Test
    @DisplayName("EspadaBendecida compone el nombre correctamente")
    void testEspadaNombre() {
        EspadaBendecidaDecorador espada = new EspadaBendecidaDecorador(baseMock);
        assertEquals("Base + Espada Bendecida", espada.getNombre());
    }

    @Test
    @DisplayName("AmuletoFuego añade +5 al daño base")
    void testAmuletoFuego() {
        AmuletoFuegoDecorador amuleto = new AmuletoFuegoDecorador(baseMock);
        assertEquals(25, amuleto.calcularDano()); // 20 + 5
    }

    @Test
    @DisplayName("Decoradores se pueden apilar (Espada + Amuleto)")
    void testDecoracionApilada() {
        EspadaBendecidaDecorador espada  = new EspadaBendecidaDecorador(baseMock);
        AmuletoFuegoDecorador    amuleto = new AmuletoFuegoDecorador(espada);

        assertEquals(33, amuleto.calcularDano()); // 20 + 8 + 5
        assertEquals("Base + Espada Bendecida + Amuleto de Fuego", amuleto.getNombre());
    }
}
