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
    cita_id BIGINT NOT NULL,
    fecha DATE NOT NULL,
    diagnostico TEXT,
    tratamiento TEXT,
    observaciones TEXT,
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    FOREIGN KEY (medico_id) REFERENCES medico(id),
	FOREIGN KEY (cita_id) REFERENCES cita(id)
);

select * from paciente

INSERT INTO usuario (username, password, rol, estado) VALUES
('recepcion2', 'recep456', 'RECEPCIONISTA', 'ACTIVO'),
('medico3', 'med789', 'MEDICO', 'ACTIVO'),
('medico4', 'med321', 'MEDICO', 'INACTIVO'),
('admin3', 'admin789', 'ADMIN', 'ACTIVO'),
('recepcion3', 'recep789', 'RECEPCIONISTA', 'INACTIVO'),
('medico5', 'med654', 'MEDICO', 'ACTIVO'),
('admin4', 'admin999', 'ADMIN', 'ACTIVO'),
('medico6', 'med888', 'MEDICO', 'ACTIVO');

INSERT INTO especialidad (nombre, descripcion) VALUES
('Traumatología', 'Trata lesiones del aparato locomotor'),
('Oftalmología', 'Especialidad de los ojos y la visión'),
('Psiquiatría', 'Tratamiento de trastornos mentales'),
('Endocrinología', 'Estudio del sistema hormonal'),
('Reumatología', 'Diagnóstico de enfermedades autoinmunes'),
('Oncología', 'Tratamiento del cáncer'),
('Urología', 'Trata enfermedades urinarias y genitales'),
('Otorrinolaringología', 'Estudio del oído, nariz y garganta');

INSERT INTO medico (dni, nombres, apellidos, cmp, telefono, correo, genero, horario_atencion, especialidad_id) VALUES
('67890123', 'Fernando', 'Mora', 'CMP006', '999888777', 'fmora@clinica.com', 'MASCULINO', 'Lun-Vie 13:00-19:00', 6),
('78901234', 'Elena', 'Reyes', 'CMP007', '911122233', 'ereyes@clinica.com', 'FEMENINO', 'Mar-Jue 14:00-20:00', 7),
('89012345', 'Raúl', 'Salazar', 'CMP008', '922334466', 'rsalazar@clinica.com', 'MASCULINO', 'Lun-Vie 12:00-18:00', 8),
('90123456', 'Carmen', 'Flores', 'CMP009', '933445577', 'cflores@clinica.com', 'FEMENINO', 'Lun-Mie 08:00-12:00', 1),
('01234567', 'Ricardo', 'Díaz', 'CMP010', '944556688', 'rdiaz@clinica.com', 'MASCULINO', 'Vie-Sab 10:00-16:00', 2),
('13579246', 'Diana', 'Palacios', 'CMP011', '955667799', 'dpalacios@clinica.com', 'FEMENINO', 'Lun-Jue 15:00-21:00', 3),
('24681357', 'Manuel', 'Ibarra', 'CMP012', '966778800', 'mibarra@clinica.com', 'MASCULINO', 'Lun-Vie 09:00-13:00', 4),
('98765432', 'Patricia', 'Vargas', 'CMP013', '977889911', 'pvargas@clinica.com', 'FEMENINO', 'Mar-Vie 11:00-17:00', 5);

select * from cita

INSERT INTO paciente (dni, nombres, apellidos, genero, fecha_nacimiento, telefono, correo, direccion, pais) VALUES
('32109876', 'Gabriela', 'Ruiz', 'FEMENINO', '1992-10-05', '933334455', 'gabriela@gmail.com', 'Av. Perú 222', 'Perú'),
('21098765', 'Miguel', 'Vera', 'MASCULINO', '1980-04-18', '922221100', 'miguel@hotmail.com', 'Jr. Arequipa 555', 'Perú'),
('10987654', 'Verónica', 'Castro', 'FEMENINO', '2001-08-27', '911113344', 'veronica@gmail.com', 'Calle Rosa 123', 'Perú'),
('19876543', 'Julio', 'Quispe', 'MASCULINO', '1970-12-02', '988776655', 'julio@outlook.com', 'Mz H Lt 3', 'Perú'),
('08765432', 'Sandra', 'Valdez', 'FEMENINO', '1998-03-11', '900111222', 'sandra@gmail.com', 'Av. El Sol 456', 'Perú'),
('07654321', 'Rodrigo', 'Delgado', 'MASCULINO', '1987-06-30', '933221100', 'rodrigo@gmail.com', 'Jr. Santa Cruz 888', 'Perú'),
('06543210', 'Natalia', 'Montoya', 'FEMENINO', '1999-01-19', '955445566', 'natalia@hotmail.com', 'Pasaje Sur 777', 'Perú'),
('05432109', 'Javier', 'Cano', 'MASCULINO', '1993-11-07', '944223344', 'javier@gmail.com', 'Av. Central 1010', 'Perú');

INSERT INTO cita (paciente_id, medico_id, fecha, hora, estado, motivo_consulta) VALUES
(1, 9, '2025-07-06', '13:30', 'CONFIRMADA', 'Dolor en rodilla'),
(2, 10, '2025-07-07', '14:15', 'SOLICITADA', 'Problemas visuales'),
(3, 11, '2025-07-08', '15:00', 'ATENDIDA', 'Ansiedad y estrés'),
(4, 12, '2025-07-09', '08:00', 'SOLICITADA', 'Síntomas hormonales'),
(5, 13, '2025-07-10', '11:30', 'CANCELADA', 'Dolor articular'),
(6, 14, '2025-07-11', '16:20', 'CONFIRMADA', 'Bulto sospechoso'),
(7, 15, '2025-07-12', '09:50', 'ATENDIDA', 'Dolor al orinar'),
(8, 16, '2025-07-13', '10:45', 'SOLICITADA', 'Infección de garganta');


INSERT INTO historial_clinico (paciente_id, medico_id, cita_id, fecha, diagnostico, tratamiento, observaciones) VALUES
(7, 9, 33, '2025-07-06', 'Esguince leve', 'Reposo y vendaje', 'Control en una semana'),
(6, 16, 34, '2025-07-07', 'Miopía moderada', 'Uso de lentes', 'Revisar en 6 meses'),
(8, 15, 35, '2025-07-08', 'Trastorno de ansiedad', 'Psicoterapia y medicación', 'Seguimiento mensual'),
(1, 14, 36, '2025-07-09', 'Hipotiroidismo', 'Levotiroxina', 'Examen de sangre pendiente'),
(2, 10, 37, '2025-07-10', 'Artritis reumatoide', 'Antiinflamatorios', 'Evaluar respuesta en 2 semanas'),
(5, 11, 38, '2025-07-11', 'Tumor benigno', 'Exámenes adicionales', 'Paciente nervioso'),
(3, 12, 39, '2025-07-12', 'Infección urinaria', 'Antibióticos', 'Revisión post tratamiento'),
(4, 13, 40, '2025-07-13', 'Amigdalitis', 'Antibióticos y reposo', 'Paciente con fiebre');

select * from historial_clinico