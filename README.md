# Juego de Lucha Medieval

Este proyecto consiste en un juego de combate por turnos desarrollado en Java para la materia Ingeniería de Software. Durante el refinamiento del proyecto se implementaron diferentes patrones de diseño, pruebas unitarias y automatización con GitHub Actions.

---

# Patrones de Diseño Implementados

## 1. Factory Method

Este patrón se utilizó para crear los personajes del juego de una manera más organizada, evitando crear directamente los objetos desde la clase principal.

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

Este patrón permite que cada personaje pueda cambiar su forma de ataque durante la ejecución del juego.

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

Este patrón se utilizó para agregar mejoras y habilidades especiales sin modificar directamente las clases originales.

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
- Compilación del proyecto
- Ejecución de pruebas
- Validación del funcionamiento del sistema

Archivo utilizado:

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

Proyecto desarrollado para la materia Ingeniería de Software utilizando Java, Maven, JUnit, Mockito y GitHub Actions.
