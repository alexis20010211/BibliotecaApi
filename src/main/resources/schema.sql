-- ==========================================
-- TABLA: rol
-- ==========================================
CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) UNIQUE NOT NULL
);

-- ==========================================
-- TABLA: usuario
-- ==========================================
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- ==========================================
-- TABLA INTERMEDIA: usuario_roles (ManyToMany)
-- ==========================================
CREATE TABLE IF NOT EXISTS usuario_roles (
    usuario_id BIGINT NOT NULL,
    rol_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, rol_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (rol_id) REFERENCES rol(id) ON DELETE CASCADE
);

-- ==========================================
-- TABLA: libro
-- ==========================================
CREATE TABLE IF NOT EXISTS libros (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255),
    stock INTEGER DEFAULT 1,
    disponible BOOLEAN DEFAULT TRUE
);

-- ==========================================
-- TABLA: prestamo
-- ==========================================
CREATE TABLE IF NOT EXISTS prestamos (
    id SERIAL PRIMARY KEY,
    fecha_prestamo TIMESTAMP,
    fecha_devolucion TIMESTAMP,
    devuelto BOOLEAN DEFAULT FALSE,

    usuario_id BIGINT,
    libro_id BIGINT,

    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE SET NULL,
    FOREIGN KEY (libro_id) REFERENCES libro(id) ON DELETE SET NULL
);
