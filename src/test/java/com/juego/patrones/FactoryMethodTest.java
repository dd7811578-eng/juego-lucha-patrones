package com.juego.patrones;

import com.juego.model.Personaje;
import com.juego.patrones.factory.ArqueroFactory;
import com.juego.patrones.factory.GuerreroFactory;
import com.juego.patrones.factory.MagoFactory;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FactoryMethodTest – creación de personajes vía Factory")
class FactoryMethodTest {

    @Test
    @DisplayName("GuerreroFactory crea un Guerrero con HP 120")
    void testGuerreroFactory() {
        Personaje p = new GuerreroFactory().crearPersonaje("Arthur");
        assertEquals("Arthur", p.getNombre());
        assertEquals(120, p.getPuntosDeVida());
        assertTrue(p.getTipo().contains("Guerrero"));
    }

    @Test
    @DisplayName("MagoFactory crea un Mago con HP 80")
    void testMagoFactory() {
        Personaje p = new MagoFactory().crearPersonaje("Merlin");
        assertEquals(80, p.getPuntosDeVida());
        assertTrue(p.getTipo().contains("Mago"));
    }

    @Test
    @DisplayName("ArqueroFactory crea un Arquero con HP 100")
    void testArqueroFactory() {
        Personaje p = new ArqueroFactory().crearPersonaje("Legolas");
        assertEquals(100, p.getPuntosDeVida());
        assertTrue(p.getTipo().contains("Arquero"));
    }

    @Test
    @DisplayName("Cada factory crea instancias independientes")
    void testInstanciasIndependientes() {
        Personaje p1 = new GuerreroFactory().crearPersonaje("A");
        Personaje p2 = new GuerreroFactory().crearPersonaje("B");
        assertNotSame(p1, p2);
    }
}
