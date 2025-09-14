# Cinema Scheduler - CineMille

## Descrizione
Applicazione web per la gestione della programmazione dei film nel multisala CineMille. Permette la consultazione pubblica delle programmazioni, la gestione dei film e delle sale, e offre API REST documentate tramite OpenAPI/Swagger.

## Funzionalità principali
- Visualizzazione lista film in programmazione
- Filtri per data di inizio/fine programmazione
- Consultazione storica delle programmazioni
- API CRUD REST per film, sale, programmazioni
- Endpoint pubblici e protetti (admin)
- Documentazione API generata automaticamente

## Struttura del progetto
- **Spring Boot** (Java)
- **H2 Database** (in-memory, demo)
- **Swagger/OpenAPI** per la documentazione
- **Caffeine Cache** per ottimizzazione endpoint pubblici
- **Docker** per deploy rapido

## Endpoints principali
- `/movie` - Lista, dettaglio, aggiunta, modifica, cancellazione film
- `/schedule` - Lista, dettaglio, aggiunta, modifica, cancellazione programmazioni
- `/rooms` - Lista sale cinema
- `/public/movie-schedule/by-day` - Programmazioni raggruppate per giorno (pubblico)
- `/public/movie-schedule/by-date-range` - Programmazioni per intervallo date (pubblico)
- `/management/import` - Importazione da Excel (admin, endpoint placeholder)

## Avvio rapido
1. Clona il repository:
   ```
   git clone https://github.com/dbkira96/cinema-scheduler-be.git
   ```
2. Avvia l'applicazione:
   ```
   ./mvnw spring-boot:run
   ```
3. Accedi alla documentazione API:
   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Note tecniche
- Gli id delle entità sono autogenerati.
- La sicurezza è gestita via token JWT.
- I dati demo sono caricati da `data.sql`.
- La documentazione OpenAPI è generata in `cinema-scheduler-0.0.1-SNAPSHOT.yaml`.


---

Per dettagli su API e schema dati, consulta la documentazione Swagger inclusa nel progetto.
