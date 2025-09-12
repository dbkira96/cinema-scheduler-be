# Documentazione API - Cinema Scheduler

## Introduzione
Questa applicazione espone API REST per la consultazione pubblica delle programmazioni cinematografiche e per la gestione (backoffice) autenticata. Di seguito sono descritti gli endpoint pubblici e i requisiti funzionali/tecnici.

---

## Requisiti Funzionali
| ID  | Nome         | Descrizione                                                                                   |
|-----|--------------|----------------------------------------------------------------------------------------------|
| RF1 | Elenco film  | Visualizza la lista dei film in programmazione, filtrabili per data di inizio/fine.           |
| RF2 | Storico      | I gestori possono accedere allo storico completo delle programmazioni passate.               |

## Requisiti Tecnici
| ID   | Nome                        | Descrizione                                                                                   |
|------|-----------------------------|----------------------------------------------------------------------------------------------|
| RT1  | Schema logico               | Schema UML delle entità principali (Movie, Schedule, Room, User).                            |
| RT2  | Backoffice e REST API       | Prototipo Spring Boot con REST API GET per la lista film.                                    |
| RT3  | Frontend (Opzionale)        | Prototipo Angular per visualizzare l’elenco film tramite chiamata REST.                      |

---

## Endpoint Pubblici

### 1. Elenco programmazioni per intervallo di giorni
```
GET /public/movie-schedule/by-day?start=2025-09-13&end=2025-09-15
```
**Descrizione:** Restituisce la programmazione raggruppata per giorno.

**Parametri:**
- `start` (yyyy-MM-dd): Data di inizio
- `end` (yyyy-MM-dd): Data di fine

**Risposta:**
```json
[
  {
    "date": "2025-09-13",
    "schedules": [
      {
        "id": 1,
        "title": "Evangelion: 3.0+1.0",
        "rooms": [
          {
            "id": 1,
            "name": "Room 1",
            "times": ["18:30", "20:30"]
          }
        ]
      }
    ]
  },
  ...
]
```

### 2. Elenco programmazioni per intervallo di date (non raggruppato per giorno)
```
GET /public/movie-schedule/by-date-range?start=2025-09-13&end=2025-09-15
```
**Descrizione:** Restituisce la lista dei film programmati nell’intervallo, con sale e orari.

**Parametri:**
- `start` (yyyy-MM-dd): Data di inizio
- `end` (yyyy-MM-dd): Data di fine

**Risposta:**
```json
[
  {
    "id": 1,
    "title": "Evangelion: 3.0+1.0",
    "rooms": [
      {
        "id": 1,
        "name": "Room 1",
        "times": ["18:30", "20:30"]
      }
    ]
  },
  ...
]
```

---

## Schema logico (UML)

Puoi rappresentare lo schema con un diagramma UML simile:

```
Movie <--- Schedule ---> Room
         ^
         |
       User
```
- **Movie**: id, title, duration, release_date, genre
- **Room**: id, name, capacity, technology
- **Schedule**: id, movie_id, room_id, start_date, end_date, time
- **User**: username, password, enabled, roles

---

## Backoffice (autenticato)
Gli endpoint di gestione (aggiunta, modifica, cancellazione film/schedule) sono protetti e accessibili solo agli utenti autenticati.

---

## Deploy & Utilizzo
- Build jar: `./mvnw clean package`
- Build Docker: `docker build -t cinema-scheduler .`
- Run Docker: `docker run -p 8080:8080 cinema-scheduler`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

---

## Note
- Database H2 in memoria/file.
- Caching con Caffeine per le API pubbliche.
- Sicurezza: endpoint pubblici e backoffice separati.

---

## Frontend (Opzionale)
Se vuoi aggiungere un frontend Angular, puoi consumare gli endpoint pubblici sopra descritti.
