create database clinicadb

use clinicadb


-- 1. Tabla de usuarios (recepcionista, admin, médicos con acceso al sistema)
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    rol ENUM('ADMIN', 'RECEPCIONISTA', 'MEDICO') NOT NULL,
    estado ENUM('ACTIVO', 'INACTIVO') DEFAULT 'ACTIVO'
);

-- 2. Tabla de especialidades médicasa
CREATE TABLE especialidad (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
);

-- 3. Tabla de médicos
CREATE TABLE medico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(10) NOT NULL UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    cmp VARCHAR(20) NOT NULL UNIQUE,
    telefono VARCHAR(15),
    correo VARCHAR(100),
    genero ENUM('MASCULINO', 'FEMENINO', 'OTRO'),
    horario_atencion VARCHAR(100),
    especialidad_id BIGINT,
    FOREIGN KEY (especialidad_id) REFERENCES especialidad(id)
);

-- 4. Tabla de pacientes
CREATE TABLE paciente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(10) NOT NULL UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    genero ENUM('MASCULINO', 'FEMENINO', 'OTRO'),
    fecha_nacimiento DATE,
    telefono VARCHAR(15),
    correo VARCHAR(100),
    direccion VARCHAR(200),
    pais VARCHAR(100),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. Tabla de citas médicas
CREATE TABLE cita (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    fecha DATE NOT NULL,
    hora VARCHAR(10) NOT NULL,
    estado ENUM('SOLICITADA', 'CONFIRMADA', 'ATENDIDA', 'CANCELADA') DEFAULT 'SOLICITADA',
    motivo_consulta TEXT,
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    FOREIGN KEY (medico_id) REFERENCES medico(id)
);

-- 6. Tabla opcional: historial clínico (si deseas implementarla)
CREATE TABLE historial_clinico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    fecha DATE NOT NULL,
    diagnostico TEXT,
    tratamiento TEXT,
    observaciones TEXT,
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    FOREIGN KEY (medico_id) REFERENCES medico(id)
);


INSERT INTO usuario (username, password, rol, estado) VALUES
('admin1', 'admin123', 'ADMIN', 'ACTIVO'),
('recepcion1', 'recep456', 'RECEPCIONISTA', 'ACTIVO'),
('drmaria', 'maria789', 'MEDICO', 'ACTIVO'),
('drluis', 'luis321', 'MEDICO', 'INACTIVO'),
('admin2', 'admin456', 'ADMIN', 'ACTIVO');

INSERT INTO especialidad (nombre, descripcion) VALUES
('Pediatría', 'Atención médica a niños y adolescentes.'),
('Cardiología', 'Diagnóstico y tratamiento de enfermedades del corazón.'),
('Dermatología', 'Cuidado de la piel y sus enfermedades.'),
('Ginecología', 'Salud reproductiva femenina.'),
('Medicina General', 'Atención médica general y derivación especializada.');


INSERT INTO medico (dni, nombres, apellidos, cmp, telefono, correo, genero, horario_atencion, especialidad_id) VALUES
('12345678', 'María', 'Gonzales Ríos', 'CMP12345', '987654321', 'maria@clinica.com', 'FEMENINO', 'Lun-Vie 8am-2pm', 1),
('87654321', 'Luis', 'Pérez Ramírez', 'CMP23456', '998877665', 'luis@clinica.com', 'MASCULINO', 'Lun-Vie 2pm-8pm', 2),
('11223344', 'Ana', 'Torres Díaz', 'CMP34567', '911122233', 'ana@clinica.com', 'FEMENINO', 'Mar-Jue 9am-3pm', 3),
('33445566', 'Carlos', 'Rojas Mejía', 'CMP45678', '922334455', 'carlos@clinica.com', 'MASCULINO', 'Mié-Vie 10am-4pm', 4),
('55667788', 'Lucía', 'Cano Salazar', 'CMP56789', '933445566', 'lucia@clinica.com', 'FEMENINO', 'Lun-Vie 1pm-7pm', 5);


INSERT INTO paciente (dni, nombres, apellidos, genero, fecha_nacimiento, telefono, correo, direccion, pais) VALUES
('99887766', 'Juan', 'Dueñas Peña', 'MASCULINO', '2014-05-12', '944556677', 'juan@mail.com', 'Av. Perú 123', 'Perú'),
('88776655', 'Sofía', 'Martínez Flores', 'FEMENINO', '1990-11-03', '933112233', 'sofia@mail.com', 'Jr. Lima 456', 'Perú'),
('77665544', 'Diego', 'Ramos Ortega', 'MASCULINO', '1985-07-20', '922334455', 'diego@mail.com', 'Calle Los Olivos 89', 'Chile'),
('66554433', 'Camila', 'Herrera Soto', 'FEMENINO', '2002-02-15', '955667788', 'camila@mail.com', 'Av. Grau 789', 'Perú'),
('55443322', 'José', 'Silva Luna', 'MASCULINO', '1978-09-25', '988776655', 'jose@mail.com', 'Jr. Junín 234', 'Bolivia');


INSERT INTO cita (paciente_id, medico_id, fecha, hora, estado, motivo_consulta) VALUES
(1, 1, '2025-06-24', '09:00', 'CONFIRMADA', 'Control de niño sano'),
(2, 2, '2025-06-25', '15:30', 'SOLICITADA', 'Dolor en el pecho'),
(3, 3, '2025-06-26', '11:00', 'CANCELADA', 'Alergia en la piel'),
(4, 4, '2025-06-27', '13:00', 'ATENDIDA', 'Chequeo general'),
(5, 5, '2025-06-28', '10:00', 'CONFIRMADA', 'Dolor de cabeza frecuente');


INSERT INTO historial_clinico (paciente_id, medico_id, fecha, diagnostico, tratamiento, observaciones) VALUES
(1, 1, '2025-06-24', 'Desnutrición leve', 'Suplementos y dieta', 'Revisar en 1 mes'),
(2, 2, '2025-06-25', 'Angina de pecho', 'Reposo, nitroglicerina', 'Evaluación con cardiólogo'),
(3, 3, '2025-06-26', 'Dermatitis atópica', 'Cremas y antihistamínicos', 'Evitar contacto con alérgenos'),
(4, 4, '2025-06-27', 'Sin hallazgos', 'Control anual recomendado', 'Paciente sano'),
(5, 5, '2025-06-28', 'Migraña crónica', 'Paracetamol + seguimiento', 'Revisión neurológica próxima cita');
