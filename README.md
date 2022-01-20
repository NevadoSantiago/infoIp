## Informacion de IP

### Pasos para correr la aplicacion:

1. Abrir la consola y colocarse en la raiz del proyecto
2. mvn clean install
3. docker build -t "infoip" .
4. docker run --name infoip -p 8080:8080 infoip:latest

### Funcionamiento:

* [Home](localhost:8080/home).
* [Swagger](localhost:8080/swagger-ui.html#/)
    >[Distancia promedio](localhost:8080/distanceAverage/)
    >[Distancia mas lejana](localhost:8080/farthest/)
    >[Distancia mas cercana](localhost:8080/nearest/)

