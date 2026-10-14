# TecnoStore - Sistema de Gestión de Ventas e Inventario

**TecnoStore** es una aplicación desarrollada en Java para la gestión integral de inventarios, carrito de compras, procesamiento de ventas y generación de reportes transaccionales con persistencia en bases de datos MySQL, optimizada para ejecución en entorno de terminal (Git Bash).

---

## Información del Proyecto
* **Desarrolladora:** Sara Julieth Sandoval Orellan (DEV001)
---

## Características Principales
1. **Filtrado de Inventario:** Búsqueda interactiva de dispositivos móviles por marca utilizando flujos de datos (`Java Streams`).
2. **Gestión de Carrito y Stock:** Validación en tiempo real de existencias físicas y actualización transaccional automatizada en la base de datos MySQL.
3. **Persistencia y Restablecimiento Automático:** Restablecimiento dinámico del stock a 50 unidades al inicializar la aplicación para facilitar pruebas y demostraciones continuas.
4. **Formato Financiero Profesional:** Implementación estricta de `DecimalFormat` y `Locale.US` para garantizar la visualización correcta de precios con separadores de miles y evitar la notación científica (`E7`).
5. **Exportación de Reportes:** Generación automática de resúmenes de venta detallados en formato `.txt` dentro de la carpeta `reportes/`.
6. **Transaccionalidad Segura (JDBC):** Uso de control de transacciones (`commit`/`rollback`) para garantizar la atomicidad al registrar pedidos y detalles de venta.

---

## Tecnologías y Herramientas Utilizadas
* **Lenguaje:** Java (JDK / Programación Orientada a Objetos)
* **Base de Datos:** MySQL / MySQL Workbench (`tecnostore_SaraSandoval`)
* **Entorno de Ejecución:** Terminal Git Bash (`javac` / `java`)
* **Control de Versiones:** Git y GitHub

---

## Estructura del Proyecto
```text
TecnostoreSaraSandovalS2/
│
├── build/                 # Archivos compilados (.class)
├── src/                   # Código fuente organizado por paquetes
│   ├── Controlador/       # Lógica de negocio (ModuloVentas.java)
│   ├── modelo/            # Entidades y fábricas (Celular, DetalleVenta, CelularFactory)
│   ├── utilidades/        # Clases de apoyo y manejo de archivos
│   └── com/s2/            # Clase Principal (Main.java)
├── reportes/              # Exportación automática de tickets de venta (.txt)
└── README.md              # Documentación del proyecto
