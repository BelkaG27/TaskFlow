# TaskFlow

API REST de gestion de tâches, développée avec Spring Boot et PostgreSQL.

## Fonctionnalités

- Créer une tâche
- Récupérer la liste des tâches
- Récupérer une tâche par son id
- Modifier une tâche existante
- Supprimer une tâche

## Stack technique

- **Java 21**
- **Spring Boot** (Spring Web, Spring Data JPA)
- **PostgreSQL** (base de données relationnelle)
- **Docker / Docker Compose** (conteneurisation de la base de données)
- **Maven** (gestion des dépendances et du build)
- **Git / GitHub** (versioning)

## Prérequis

- JDK 21
- Docker Desktop
- Un client HTTP pour tester l'API (Postman recommandé)

## Installation et lancement

### 1. Cloner le projet

\`\`\`bash
git clone https://github.com/BelkaG27/taskflow.git
cd taskflow
\`\`\`

### 2. Lancer la base de données PostgreSQL

\`\`\`bash
docker compose up -d
\`\`\`

### 3. Lancer l'application

\`\`\`bash
./mvnw spring-boot:run
\`\`\`

L'API est accessible sur `http://localhost:8080`.

## Endpoints disponibles

| Méthode | URL              | Description                        |
|---------|-------------------|------------------------------------|
| GET     | /tasks            | Récupérer toutes les tâches        |
| GET     | /tasks/{id}       | Récupérer une tâche par son id     |
| POST    | /tasks            | Créer une nouvelle tâche           |
| PUT     | /tasks/{id}       | Modifier une tâche existante       |
| DELETE  | /tasks/{id}       | Supprimer une tâche                |

### Exemple de requête POST

\`\`\`json
{
  "titre": "Créer la BDD",
  "description": "Mettre en place PostgreSQL",
  "terminee": false
}
\`\`\`

## Auteur

[Github : BelkaG27]