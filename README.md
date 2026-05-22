# Juego de Lucha Medieval 🏰⚔️

Juego de combate por turnos en Java, refactorizado con patrones de diseño creacionales y estructurales, pruebas unitarias con JUnit 5 + Mockito e integración continua con GitHub Actions.

---

# Patrones de Diseño Implementados

## 1. Factory Method
Permite crear personajes sin depender de clases concretas.

### Clases:
- PersonajeFactory
- GuerreroFactory
- MagoFactory
- ArqueroFactory

Ejemplo:

```java
PersonajeFactory factory = new GuerreroFactory();
Personaje guerrero = factory.crearPersonaje("Arthur");
```

---

## 2. Strategy
Permite cambiar la estrategia de ataque dinámicamente.

### Clases:
- EstrategiaAtaque
- AtaqueCuerpoACuerpo
- AtaqueMagico
- AtaqueArco

Ejemplo:

```java
personaje.setEstrategiaAtaque(new AtaqueMagico());
```

---

## 3. Decorator
Añade mejoras de daño sin modificar clases existentes.

### Clases:
- DecoradorArma
- EspadaBendecidaDecorador
- AmuletoFuegoDecorador

Ejemplo:

```java
EstrategiaAtaque ataque =
    new AmuletoFuegoDecorador(
        new EspadaBendecidaDecorador(
            new AtaqueCuerpoACuerpo()));
```

---

# Personajes

| Personaje | Vida | Tipo de Ataque |
|------------|------|----------------|
| Guerrero | 120 HP | Cuerpo a cuerpo |
| Mago | 80 HP | Magia |
| Arquero | 100 HP | Arco |

---

# Estructura del Proyecto

```text
src/
├── main/java/com/juego/
│   ├── JuegoLuchaMedieval.java
│   ├── juego/
│   ├── model/
│   └── patrones/
│       ├── factory/
│       ├── strategy/
│       └── decorator/
└── test/java/com/juego/
```

---

# Pruebas Unitarias

El proyecto incluye 34 pruebas unitarias usando:
- JUnit 5
- Mockito

Resultado esperado:

```bash
Tests run: 34, Failures: 0, Errors: 0
```

---

# Integración Continua

GitHub Actions ejecuta automáticamente:
- Compilación
- Ejecución de pruebas
- Validación del proyecto

Archivo:

```text
.github/workflows/ci.yml
```

---

# Ejecutar el Proyecto

## Compilar

```bash
mvn compile
```

## Ejecutar pruebas

```bash
mvn clean test
```

## Ejecutar juego

```bash
mvn exec:java -Dexec.mainClass="com.juego.JuegoLuchaMedieval"
```

---

# Autor

Proyecto desarrollado para Ingeniería de Software utilizando Java, Maven, JUnit, Mockito y GitHub Actions.Fri May 22 05:51:00 UTC 2026
