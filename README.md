# TaskFlow

API REST de gestion de tâches, sécurisée par authentification JWT, développée avec Spring Boot et PostgreSQL. Inclut une documentation interactive Swagger et un frontend de démonstration.

## Fonctionnalités

- Inscription et connexion des utilisateurs (JWT)
- Isolation des données : chaque utilisateur ne voit que ses propres tâches
- CRUD complet des tâches (créer, lister, modifier, supprimer)
- Validation des données (titre obligatoire, longueurs maximales, email valide)
- Documentation API interactive (Swagger/OpenAPI)
- Frontend web de démonstration (HTML/CSS/JS)

## Stack technique

- **Java 21**
- **Spring Boot** (Spring Web, Spring Data JPA, Spring Security)
- **PostgreSQL** (base de données relationnelle)
- **JWT** (io.jsonwebtoken / JJWT) pour l'authentification stateless
- **Swagger / OpenAPI** (springdoc-openapi) pour la documentation
- **Docker / Docker Compose** (conteneurisation complète : backend + base de données)
- **Maven** (gestion des dépendances et du build)
- **JUnit 5 / Mockito** (tests unitaires)
- **GitHub Actions** (intégration continue)

## Prérequis

- JDK 21
- Docker Desktop
- Un client HTTP pour tester l'API (Postman recommandé), ou directement Swagger UI

## Installation et lancement

### 1. Cloner le projet

```bash
git clone https://github.com/TON_USERNAME/taskflow.git
cd taskflow
```

### 2. Lancer l'application complète (backend + base de données)

```bash
docker compose up -d --build
```

L'API est accessible sur `http://localhost:8080`.

### 3. Documentation interactive (Swagger UI)

http://localhost:8080/swagger-ui/index.html


Pour tester les endpoints protégés depuis Swagger :
1. Récupère un token via `POST /auth/login`
2. Clique sur le bouton **Authorize** en haut à droite
3. Colle le token (sans le mot `Bearer`)

### 4. Frontend de démonstration

Ouvre le fichier `frontend/index.html` dans un navigateur (ou via une extension type Live Server). Il permet de s'inscrire, se connecter, et gérer ses tâches visuellement, en consommant l'API.

## Authentification

| Méthode | URL             | Description                          |
|---------|------------------|----------------------------------------|
| POST    | /auth/register   | Créer un compte, retourne un token JWT |
| POST    | /auth/login      | Se connecter, retourne un token JWT    |

Toutes les routes `/tasks/**` nécessitent un header : Authorization: Bearer <votre_token>


## Endpoints des tâches

| Méthode | URL              | Description                            |
|---------|-------------------|------------------------------------------|
| GET     | /tasks            | Lister les tâches de l'utilisateur connecté |
| GET     | /tasks/{id}       | Récupérer une tâche (si elle appartient à l'utilisateur) |
| POST    | /tasks            | Créer une nouvelle tâche               |
| PUT     | /tasks/{id}       | Modifier une tâche existante           |
| DELETE  | /tasks/{id}       | Supprimer une tâche                    |

### Exemple de requête POST /tasks

```json
{
  "titre": "Créer la BDD",
  "description": "Mettre en place PostgreSQL",
  "terminee": false
}
```

## Lancer les tests

```bash
./mvnw test
```

Inclut des tests unitaires sur le contrôleur (avec repositories mockés et contexte de sécurité simulé), ainsi qu'un test d'intégration vérifiant le démarrage complet de l'application.

## Intégration continue

Un pipeline GitHub Actions (`.github/workflows/ci.yml`) exécute automatiquement les tests à chaque push, avec un PostgreSQL temporaire fourni comme service CI.

## Auteur

[BelkaG27]