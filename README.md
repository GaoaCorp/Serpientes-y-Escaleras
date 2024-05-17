# Serpientes-y-Escaleras

"Serpientes y Escaleras" es un juego clásico de mesa que combina suerte y estrategia, donde los jugadores compiten para ser los primeros en llegar a la casilla final del tablero. El juego consta de un tablero con una serie de casillas numeradas y conectadas por serpientes y escaleras. Los jugadores lanzan un dado en su turno y avanzan por el tablero según el resultado del lanzamiento. Si caen en una casilla con una escalera, avanzan hacia arriba; si caen en una casilla con una serpiente, retroceden hacia abajo.

Requerimientos Funcionales:

Tablero de Juego:

El juego debe tener un tablero de juego con un tamaño configurable (10x10, 13x13, 15x15).
El tablero contendrá casillas numeradas del 1 al número total de casillas.
Las serpientes y las escaleras estarán ubicadas en casillas específicas del tablero.
Jugadores:

El juego permitirá un número configurable de jugadores (por ejemplo, de 2 a 4).
Cada jugador tendrá una ficha que se moverá por el tablero.
Los jugadores se turnarán para lanzar un dado y avanzar su ficha por el tablero.
Movimiento de Jugadores:

Después de lanzar el dado, un jugador moverá su ficha la cantidad de casillas correspondiente al resultado del dado.
Si un jugador cae en una casilla con una serpiente, su ficha retrocederá a la casilla inferior de la serpiente.
Si un jugador cae en una casilla con una escalera, su ficha avanzará a la casilla superior de la escalera.
Fin del Juego:

El juego finalizará cuando un jugador alcance la última casilla del tablero.
Se declarará como ganador al primer jugador que llegue a la última casilla.
Personalización:

Permitir la personalización de las ubicaciones de las serpientes y las escaleras en el tablero.
Proporcionar opciones para cambiar el número de jugadores y el tamaño del tablero.
Gestión de Turnos:

Alternar correctamente entre los turnos de los jugadores.
Mostrar de manera clara el jugador actual en cada turno.
Registro de Eventos:

Registrar los eventos del juego, como los movimientos de los jugadores y los encuentros con serpientes y escaleras.
Mostrar un registro de eventos para que los jugadores puedan ver el historial de la partida.
