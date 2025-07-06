-- Base de datos
CREATE DATABASE IF NOT EXISTS clinicadbv2;
USE clinicadbv2;

-- 1. Tabla registrarusuario
CREATE TABLE registrarusuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

-- 2. Tabla usuario
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    rol ENUM('ADMIN', 'RECEPCIONISTA', 'MEDICO') NOT NULL,
    estado ENUM('ACTIVO', 'INACTIVO') DEFAULT 'ACTIVO'
) ENGINE=InnoDB;

-- 3. Tabla especialidad
CREATE TABLE especialidad (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
) ENGINE=InnoDB;

-- 4. Tabla medico
CREATE TABLE medico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(8) NOT NULL UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    cmp VARCHAR(20) NOT NULL UNIQUE,
    telefono VARCHAR(15),
    correo VARCHAR(100),
    genero ENUM('MASCULINO', 'FEMENINO', 'OTRO'),
    horario_atencion VARCHAR(100),
    especialidad_id BIGINT,
    FOREIGN KEY (especialidad_id) REFERENCES especialidad(id)
) ENGINE=InnoDB;

-- 5. Tabla paciente
CREATE TABLE paciente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(8) NOT NULL UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    genero ENUM('MASCULINO', 'FEMENINO', 'OTRO'),
    fecha_nacimiento DATE,
    telefono VARCHAR(15),
    correo VARCHAR(100),
    direccion VARCHAR(200),
    pais VARCHAR(100),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- 6. Tabla cita
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
) ENGINE=InnoDB;

-- 7. Tabla historial clinico
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
) ENGINE=InnoDB;

-- ========================
-- DATOS DE EJEMPLO
-- ========================


-- especialidad
INSERT INTO especialidad (nombre, descripcion) VALUES
('Traumatología', 'Trata lesiones del aparato locomotor'),
('Oftalmología', 'Especialidad de los ojos y la visión'),
('Psiquiatría', 'Tratamiento de trastornos mentales'),
('Endocrinología', 'Estudio del sistema hormonal'),
('Reumatología', 'Diagnóstico de enfermedades autoinmunes'),
('Oncología', 'Tratamiento del cáncer'),
('Urología', 'Trata enfermedades urinarias y genitales'),
('Otorrinolaringología', 'Estudio del oído, nariz y garganta'),
('Cardiología', 'Trata enfermedades cardiovasculares'),
('Dermatología', 'Trata enfermedades de la piel'),
('Pediatría', 'Atención médica infantil'),
('Gastroenterología', 'Estudio del sistema digestivo'),
('Neumología', 'Trata enfermedades respiratorias'),
('Neurología', 'Estudio del sistema nervioso'),
('Nefrología', 'Enfermedades del riñón'),
('Ginecología', 'Salud reproductiva femenina'),
('Medicina Interna', 'Trata enfermedades generales del adulto'),
('Alergología', 'Diagnóstico de alergias'),
('Hematología', 'Enfermedades de la sangre'),
('Medicina Familiar', 'Atención integral de la familia');



-- medico (20 médicos distintos con especialidad)
INSERT INTO medico (dni, nombres, apellidos, cmp, telefono, correo, genero, horario_atencion, especialidad_id) VALUES
('67890123', 'Fernando', 'Mora', 'CMP006', '999888777', 'fmora@clinica.com', 'MASCULINO', 'Lun-Vie 13:00-19:00', 1), 
('78901234', 'Elena', 'Reyes', 'CMP007', '911122233', 'ereyes@clinica.com', 'FEMENINO', 'Mar-Jue 14:00-20:00', 2), 
('89012345', 'Raúl', 'Salazar', 'CMP008', '922334466', 'rsalazar@clinica.com', 'MASCULINO', 'Lun-Vie 12:00-18:00', 3), 
('90123456', 'Carmen', 'Flores', 'CMP009', '933445577', 'cflores@clinica.com', 'FEMENINO', 'Lun-Mie 08:00-12:00', 4), 
('01234567', 'Patricia', 'Vargas', 'CMP013', '977889911', 'pvargas@clinica.com', 'FEMENINO', 'Vie-Sab 10:00-16:00', 5), 
('13579246', 'Manuel', 'Ibarra', 'CMP012', '966778800', 'mibarra@clinica.com', 'MASCULINO', 'Lun-Jue 15:00-21:00', 6), 
('24681357', 'Oscar', 'Torres', 'CMP018', '944667788', 'otorres@clinica.com', 'MASCULINO', 'Mar-Vie 09:00-13:00', 7), 
('98765432', 'Sonia', 'Gómez', 'CMP014', '988223344', 'sgomez@clinica.com', 'FEMENINO', 'Mar-Vie 15:00-20:00', 8), 
('12345678', 'Luis', 'Zamora', 'CMP015', '977334455', 'lzamora@clinica.com', 'MASCULINO', 'Lun-Vie 10:00-16:00', 9), 
('23456789', 'Lucía', 'Paredes', 'CMP017', '955556677', 'lparedes@clinica.com', 'FEMENINO', 'Mar-Vie 12:00-18:00', 10), 
('34567890', 'Pablo', 'Sánchez', 'CMP016', '966445566', 'psanchez@clinica.com', 'MASCULINO', 'Lun-Sab 08:00-14:00', 11), 
('45678901', 'Laura', 'Huamán', 'CMP023', '911223344', 'lhuaman@clinica.com', 'FEMENINO', 'Mar-Vie 12:00-18:00', 12), 
('56789012', 'Jorge', 'Ramírez', 'CMP020', '922889900', 'jramirez@clinica.com', 'MASCULINO', 'Lun-Vie 14:00-20:00', 13), 
('67890124', 'Mariana', 'Pinto', 'CMP019', '933778899', 'mpinto@clinica.com', 'FEMENINO', 'Mie-Sab 09:00-13:00', 14),
('78901235', 'David', 'Montes', 'CMP022', '900001122', 'dmontes@clinica.com', 'MASCULINO', 'Lun-Vie 10:00-17:00', 15),
('89012346', 'Silvia', 'Salinas', 'CMP021', '911990011', 'ssalinas@clinica.com', 'FEMENINO', 'Lun-Jue 15:00-21:00', 16),
('90123457', 'Henry', 'Campos', 'CMP024', '922334455', 'hcampos@clinica.com', 'MASCULINO', 'Vie-Sab 10:00-14:00', 17), 
('01234568', 'Angela', 'López', 'CMP025', '933445566', 'alopez@clinica.com', 'FEMENINO', 'Lun-Jue 14:00-20:00', 18),
('13579247', 'Lucía', 'Salas', 'CMP026', '977889900', 'lsalas@clinica.com', 'FEMENINO', 'Mar-Vie 12:00-18:00', 19), 
('24681358', 'Marco', 'Ramos', 'CMP027', '988990011', 'mramos@clinica.com', 'MASCULINO', 'Lun-Vie 13:00-19:00', 20); 


INSERT INTO paciente (dni, nombres, apellidos, genero, fecha_nacimiento, telefono, correo, direccion, pais) VALUES
('32109876', 'Gabriela', 'Ruiz', 'FEMENINO', '1992-10-05', '933334455', 'gabriela@gmail.com', 'Av. Perú 222', 'Perú'),
('21098765', 'Miguel', 'Vera', 'MASCULINO', '1980-04-18', '922221100', 'miguel@hotmail.com', 'Jr. Arequipa 555', 'Perú'),
('10987654', 'Verónica', 'Castro', 'FEMENINO', '2001-08-27', '911113344', 'veronica@gmail.com', 'Calle Rosa 123', 'Perú'),
('19876543', 'Julio', 'Quispe', 'MASCULINO', '1970-12-02', '988776655', 'julio@outlook.com', 'Mz H Lt 3', 'Perú'),
('08765432', 'Sandra', 'Valdez', 'FEMENINO', '1998-03-11', '900111222', 'sandra@gmail.com', 'Av. El Sol 456', 'Perú'),
('07654321', 'Rodrigo', 'Delgado', 'MASCULINO', '1987-06-30', '933221100', 'rodrigo@gmail.com', 'Jr. Santa Cruz 888', 'Perú'),
('06543210', 'Natalia', 'Montoya', 'FEMENINO', '1999-01-19', '955445566', 'natalia@hotmail.com', 'Pasaje Sur 777', 'Perú'),
('05432109', 'Javier', 'Cano', 'MASCULINO', '1993-11-07', '944223344', 'javier@gmail.com', 'Av. Central 1010', 'Perú'),
('04321098', 'Claudia', 'Fernández', 'FEMENINO', '1995-02-22', '922334455', 'claudia@gmail.com', 'Av. Los Héroes 456', 'Perú'),
('03210987', 'Carlos', 'Sánchez', 'MASCULINO', '1982-09-15', '933556677', 'carlos@gmail.com', 'Calle Libertad 222', 'Perú'),
('02109876', 'Rosa', 'Luna', 'FEMENINO', '1990-11-25', '944667788', 'rosa@gmail.com', 'Jr. Progreso 333', 'Perú'),
('01123456', 'Juan', 'Mendoza', 'MASCULINO', '1985-07-14', '955778899', 'juan@gmail.com', 'Av. Libertad 100', 'Perú'),
('00123456', 'Paula', 'Reyes', 'FEMENINO', '1997-03-28', '911223344', 'paula@gmail.com', 'Mz K Lt 12', 'Perú'),
('99887766', 'Esteban', 'Ortega', 'MASCULINO', '1983-12-10', '922334455', 'esteban@gmail.com', 'Pasaje Lima 456', 'Perú'),
('98776655', 'Martha', 'Gómez', 'FEMENINO', '1979-04-30', '933445566', 'martha@gmail.com', 'Calle San Juan 789', 'Perú'),
('97665544', 'Renzo', 'Peña', 'MASCULINO', '1991-06-09', '944556677', 'renzo@gmail.com', 'Av. Universitaria 555', 'Perú'),
('96554433', 'Lorena', 'Paredes', 'FEMENINO', '1994-09-17', '955667788', 'lorena@gmail.com', 'Jr. Tambo 123', 'Perú'),
('95443322', 'David', 'Villanueva', 'MASCULINO', '1988-11-21', '966778899', 'david@gmail.com', 'Mz A Lt 9', 'Perú'),
('94332211', 'Lucía', 'Salas', 'FEMENINO', '1996-02-14', '977889900', 'lucia@gmail.com', 'Pasaje Norte 555', 'Perú'),
('93221100', 'Marco', 'Ramos', 'MASCULINO', '1986-08-19', '988990011', 'marco@gmail.com', 'Jr. Sur 333', 'Perú');


-- HISTORIAL_CLINICO (modificado para concordar con las citas generadas)

INSERT INTO historial_clinico (paciente_id, medico_id, cita_id, fecha, diagnostico, tratamiento, observaciones) VALUES
(1, 1, 1, '2020-05-15', 'Fractura distal de radio', 'Yeso y control radiográfico', 'Revisión en 4 semanas'),
(2, 2, 2, '2020-08-20', 'Astigmatismo', 'Lentes correctores', 'Control en 1 año'),
(3, 3, 3, '2020-11-10', 'Trastorno adaptativo', 'Psicoterapia semanal', 'Evaluar evolución en 1 mes'),
(4, 4, 4, '2020-12-03', 'Hipertiroidismo leve', 'Antitiroideos', 'Control de TSH en 2 meses'),
(5, 5, 5, '2021-02-18', 'Lupus eritematoso sistémico', 'Inmunosupresores', 'Control bimensual'),
(6, 6, 6, '2021-06-24', 'Linfoma no Hodgkin', 'Quimioterapia', 'Evaluar efectos secundarios'),
(7, 7, 7, '2021-09-14', 'Litiasis renal', 'Litotricia', 'Control de urea y creatinina en 1 mes'),
(8, 8, 8, '2021-10-05', 'Otitis media aguda', 'Antibióticos orales', 'Control en 7 días'),
(9, 9, 9, '2022-03-21', 'Fibrilación auricular', 'Beta bloqueadores', 'Monitorear frecuencia cardiaca'),
(10, 10, 10, '2022-06-10', 'Acné severo', 'Isotretinoína', 'Monitorear función hepática'),
(11, 11, 11, '2022-07-15', 'Faringitis bacteriana', 'Amoxicilina', 'Control en 1 semana'),
(12, 12, 12, '2022-09-27', 'Gastritis aguda', 'Inhibidores de bomba de protones', 'Evaluar en 30 días'),
(13, 13, 13, '2023-01-18', 'Bronquitis aguda', 'Broncodilatadores', 'Control en 15 días'),
(14, 14, 14, '2023-04-04', 'Cefalea tensional crónica', 'Analgesia y relajantes', 'Control en 1 mes'),
(15, 15, 15, '2023-06-22', 'Infección urinaria baja', 'Nitrofurantoína', 'Control de urocultivo'),
(16, 16, 16, '2023-09-12', 'Endometriosis leve', 'Tratamiento hormonal', 'Reevaluar tras 3 ciclos'),
(17, 17, 17, '2024-02-05', 'Hipertensión esencial', 'Enalapril', 'Control mensual'),
(18, 18, 18, '2024-05-19', 'Rinitis alérgica', 'Antihistamínicos orales', 'Evitar exposición a polvo'),
(19, 19, 19, '2024-08-13', 'Leucemia linfoblástica aguda', 'Quimioterapia', 'Control hematológico semanal'),
(20, 20, 20, '2024-11-21', 'Lumbalgia mecánica', 'Ejercicios y fisioterapia', 'Evaluar en 4 semanas'),
(1, 1, 21, '2025-01-09', 'Tendinitis del supraespinoso', 'Fisioterapia y reposo', 'Revisión en 2 semanas'),
(2, 2, 22, '2025-04-11', 'Presbicia', 'Lentes de cerca', 'Control anual'),
(3, 3, 23, '2025-07-06', 'Trastorno de pánico', 'Ansiolíticos y terapia', 'Seguimiento mensual'),
(4, 4, 24, '2025-07-15', 'Diabetes mellitus tipo 2', 'Metformina y dieta', 'Monitorear HbA1c en 3 meses');



INSERT INTO cita (paciente_id, medico_id, fecha, hora, estado, motivo_consulta) VALUES
(1, 1, '2020-05-15', '09:00', 'CONFIRMADA', 'Dolor de muñeca'),
(2, 2, '2020-08-20', '10:30', 'CONFIRMADA', 'Revisión visual'),
(3, 3, '2020-11-10', '11:00', 'CONFIRMADA', 'Ansiedad y tristeza'),
(4, 4, '2020-12-03', '08:30', 'CONFIRMADA', 'Revisión de tiroides'),
(5, 5, '2021-02-18', '14:00', 'CONFIRMADA', 'Dolor articular generalizado'),
(6, 6, '2021-06-24', '15:30', 'CONFIRMADA', 'Evaluación de masa ganglionar'),
(7, 7, '2021-09-14', '16:00', 'CONFIRMADA', 'Dolor al orinar'),
(8, 8, '2021-10-05', '09:00', 'CONFIRMADA', 'Dolor de oído'),
(9, 9, '2022-03-21', '10:00', 'CONFIRMADA', 'Palpitaciones'),
(10, 10, '2022-06-10', '11:30', 'CONFIRMADA', 'Erupciones faciales'),
(11, 11, '2022-07-15', '12:00', 'CONFIRMADA', 'Dolor de garganta'),
(12, 12, '2022-09-27', '13:00', 'CONFIRMADA', 'Dolor abdominal'),
(13, 13, '2023-01-18', '14:00', 'CONFIRMADA', 'Tos persistente'),
(14, 14, '2023-04-04', '15:30', 'CONFIRMADA', 'Dolor de cabeza frecuente'),
(15, 15, '2023-06-22', '16:00', 'CONFIRMADA', 'Dolor al orinar'),
(16, 16, '2023-09-12', '09:00', 'CONFIRMADA', 'Dolor pélvico'),
(17, 17, '2024-02-05', '10:30', 'CONFIRMADA', 'Chequeo preventivo'),
(18, 18, '2024-05-19', '11:00', 'CONFIRMADA', 'Estornudos recurrentes'),
(19, 19, '2024-08-13', '12:00', 'CONFIRMADA', 'Fatiga extrema'),
(20, 20, '2024-11-21', '13:30', 'CONFIRMADA', 'Dolor lumbar crónico'),
(1, 1, '2025-01-09', '09:00', 'CONFIRMADA', 'Dolor de hombro'),
(2, 2, '2025-04-11', '10:30', 'CONFIRMADA', 'Problemas para enfocar'),
(3, 3, '2025-07-06', '11:00', 'CONFIRMADA', 'Ataques de pánico'),
(4, 4, '2025-07-15', '08:30', 'CONFIRMADA', 'Controles de diabetes');

