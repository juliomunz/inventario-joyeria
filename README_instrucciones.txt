# Sistema de Inventario y Ventas de Joyería

Este sistema permite gestionar un inventario de joyas, registrar ventas a clientes y generar reportes de ventas.

## 🚀 Instrucciones para levantar la base de datos

1. Asegúrese de tener **Docker** instalado.
2. Desde el directorio del proyecto, levante el contenedor de PostgreSQL utilizando Docker Compose:

   docker compose up -d

3. Conéctese a la base de datos (puede usar `psql` o PgAdmin).
4. Restaure el backup ejecutando el archivo:

   psql -U admin -d joyeria_db -h localhost -f backup_joyeria_db.sql

   (La contraseña de usuario es `admin123`.)

---

## 🚀 Instrucciones para ejecutar la aplicación

1. Asegúrese de que la base de datos PostgreSQL esté corriendo (`localhost:5432`).
2. Desde la terminal, en el directorio donde está el archivo `.jar`, ejecute:

   java -jar SistemaInventarioJoyeria.jar

✅ Esto abrirá la aplicación de escritorio Swing.

---

## 📋 Datos de conexión usados

- **Host**: localhost
- **Puerto**: 5432
- **Base de datos**: joyeria_db
- **Usuario**: admin
- **Contraseña**: admin123

---

## 📋 Requisitos

- Java 17 o superior.
- Docker instalado (para levantar PostgreSQL fácilmente).

---