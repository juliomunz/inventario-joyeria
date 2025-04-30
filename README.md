# 💎 Sistema de Inventario y Ventas de Joyería

Aplicación de escritorio desarrollada en **Java Swing** con arquitectura **MVC + DAO**, conectada a una base de datos **PostgreSQL** mediante JDBC.

Permite gestionar joyas, registrar ventas a clientes reales, y mostrar reportes como historial de ventas y joyas más vendidas.

---

## 🛠️ Tecnologías Utilizadas

- 💻 Java 17
- 🎨 Java Swing (Interfaz gráfica)
- 🐘 PostgreSQL (vía JDBC)
- 🐳 Docker + Docker Compose (para levantar la base de datos)
- 🧱 Arquitectura MVC + DAO

---

## 🎯 Funcionalidades

- **Gestión de Inventario** (CRUD de joyas)
- **Registro de Ventas** a clientes reales (con validación de stock)
- **Reportes:**
  - Historial de ventas (fecha, cliente, joya)
  - Joyas más vendidas
- **Gestión de Clientes** (registro y selección en venta)
- Interfaz de usuario amigable con **JTable**, `JDialog`, `JComboBox`, `JOptionPane` y más.

---

## 🚀 Cómo Ejecutar la Aplicación

### 1. Clonar el repositorio

git clone https://github.com/tu-usuario/inventario-joyeria.git

cd inventario-joyeria

### 2. Levantar la base de datos con Docker
** Asegúrate de tener Docker instalado.

Ejecutar el siguiente comando en la raíz del proyecto:

* docker compose up -d

Esto levantará un contenedor de PostgreSQL con la configuración especificada en el archivo `docker-compose.yml`.
Se creará una base de datos PostgreSQL en `localhost:5432` con:

* Usuario: admin
* Contraseña: admin123
* Base de datos: joyeria_db

### 3. Cargar el respaldo SQL

Una vez levantada la base, ejecuta:

* psql -U admin -d joyeria_db -h localhost -f backup_joyeria_db.sql

### 4. Ejecutar el archivo .jar

* java -jar SistemaInventarioJoyeria.jar


### 📂 Estructura del Proyecto

```bash
SistemaInventarioJoyeria/
├── src/
│   └── com/joyeria/
│       ├── controller/
│       ├── dao/
│       ├── model/
│       ├── util/
│       └── view/
├── SistemaInventarioJoyeria.jar
├── backup_joyeria_db.sql
├── docker-compose.yml
├── README.md
└── .gitignore

