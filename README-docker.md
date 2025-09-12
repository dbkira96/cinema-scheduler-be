# Deploy su Docker

## Build del jar

Esegui:
```
./mvnw clean package
```
Il jar sarà in `target/cinema-scheduler*.jar`.

## Build dell'immagine Docker

```
docker build -t cinema-scheduler .
```

## Run del container

```
docker run -p 8080:8080 --name cinema-scheduler cinema-scheduler
```

## Note
- Assicurati che H2 sia configurato per modalità file o memoria.
- Swagger UI sarà disponibile su `http://localhost:8080/swagger-ui.html`.
