-- ROLES
INSERT INTO roles (nombre) VALUES 
('ADMIN'),
('USER')
ON CONFLICT DO NOTHING;

-- USUARIOS
INSERT INTO usuarios (username, email, password) VALUES 
('admin', 'admin@correo.com', '123456'),
('juan', 'juan@correo.com', '123456')
ON CONFLICT DO NOTHING;

-- ASIGNAR ROLES
INSERT INTO usuario_roles (usuario_id, rol_id) VALUES
(1, 1),    -- admin -> ADMIN
(1, 2),    -- admin -> USER
(2, 2)     -- juan -> USER
ON CONFLICT DO NOTHING;

-- LIBROS
INSERT INTO libros (titulo, autor, stock, disponible) VALUES
('El Principito', 'Antoine de Saint-Exupéry', 5, TRUE),
('Cien años de soledad', 'Gabriel García Márquez', 3, TRUE),
('1984', 'George Orwell', 4, TRUE)
ON CONFLICT DO NOTHING;

-- PRÉSTAMOS
INSERT INTO prestamos (fecha_prestamo, devuelto, usuario_id, libro_id) VALUES
(NOW(), FALSE, 2, 1)
ON CONFLICT DO NOTHING;
