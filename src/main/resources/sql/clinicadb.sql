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