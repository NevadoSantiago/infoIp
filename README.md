## Informacion de IP

### Pasos para correr la aplicacion:

1. Abrir la consola y colocarse en la raiz del proyecto
2. mvn clean install
3. docker build -t "infoip" .
4. docker run --name infoip -p 8080:8080 infoip:latest

### Funcionamiento:

 * [Home](http://localhost:8080/home).
 * [Swagger](http://localhost:8080/swagger-ui.html#/)
   * [Distancia promedio](http://localhost:8080/api/invocations/distanceAverage/)
   * [Distancia mas lejana](http://localhost:8080/api/invocations/farthest/)
   * [Distancia mas cercana](http://localhost:8080/api/invocations/nearest/)

