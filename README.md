# Personal Blog

A simple personal blog application built with Spring Boot. Supports writing, editing, and deleting articles through a protected admin panel. Articles are stored as JSON files — no database required.

---

## Tech Stack

- **Java 21**
- **Spring Boot 3.2.5**
- **Spring Security** — admin authentication
- **Thymeleaf** — server-side HTML templates
- **Tailwind CSS** — styling via CDN
- **Jackson** — JSON file storage

---

## Features

- 📖 Public home page listing all articles
- 📄 Individual article pages
- 🔐 Protected admin panel (login required)
- ✏️ Create, edit, and delete articles
- 💾 File-based storage (no database)

---

## Project Structure

```
src/main/java/com/blog/personal_blog/
├── config/
│   └── SecurityConfig.java       # Spring Security setup
├── controller/
│   ├── GuestController.java      # Public routes (/, /article/{slug})
│   └── AdminController.java      # Admin routes (/admin/*)
├── model/
│   └── Article.java              # Article model
├── repository/
│   └── ArticleRepository.java    # JSON file read/write
└── service/
    └── ArticleService.java       # Business logic

src/main/resources/
├── templates/
│   ├── index.html                # Home page
│   ├── article.html              # Article page
│   └── admin/
│       ├── login.html
│       ├── dashboard.html
│       ├── add.html
│       └── edit.html
└── application.properties
```

---

## Getting Started

### Prerequisites

- Java 21+
- Maven (or use the included `./mvnw` wrapper)

### Run the app

```bash
./mvnw spring-boot:run
```

Then open your browser:

| URL | Description |
|-----|-------------|
| `http://localhost:8080` | Public home page |
| `http://localhost:8080/admin/login` | Admin login |

---

## Configuration

Edit `src/main/resources/application.properties`:

```properties
server.port=8080
blog.articles.directory=data/articles
blog.admin.username=admin
blog.admin.password=secret123
spring.thymeleaf.cache=false
```

> ⚠️ Change the admin username and password before deploying.

---

## Admin Credentials (default)

| Field | Value |
|-------|-------|
| Username | `admin` |
| Password | `secret123` |

---

## How Articles Are Stored

Each article is saved as a `.json` file in the `data/articles/` directory, named by its slug.

Example — `data/articles/my-first-post.json`:
```json
{
  "slug": "my-first-post",
  "title": "My First Post",
  "content": "Hello world!",
  "date": "2026-04-01"
}
```
