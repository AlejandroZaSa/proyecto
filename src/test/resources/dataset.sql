INSERT INTO medico VALUES (1, 'password1', 'az0031456@gmail.com', '1234567890', 2, 1, 'foto1.jpg', 'Dr. Juan Perez', '1234567890', 100.00, 3);

INSERT INTO medico VALUES (2, 'password2', 'medico2@example.com', '0987654321', 1, 0, 'foto2.jpg', 'Dra. María Rodríguez', '0987654321', 80.00, 5);

INSERT INTO medico VALUES (3, 'password3', 'medico3@example.com', '5678901234', 3, 1, 'foto3.jpg', 'Dr. Pedro Gómez', '5678901234', 120.00, 7);

INSERT INTO medico VALUES (4, 'password4', 'medico4@example.com', '2345678901', 0, 0, 'foto4.jpg', 'Dra. Laura González', '2345678901', 90.00, 2);

INSERT INTO medico VALUES (5, 'password5', 'alejandro.zapatas@uqvirtual.edu.co', '3456789012', 2, 1, 'foto5.jpg', 'Dr. Ana Lopez', '3456789012', 110.00, 4);

INSERT INTO horario VALUES (1, 0, '08:00:00', '12:00:00', 1);

INSERT INTO horario VALUES (2, 1, '09:30:00', '14:00:00', 2);

INSERT INTO horario VALUES (3, 2, '10:00:00', '13:30:00', 3);

INSERT INTO horario VALUES (4, 3, '13:00:00', '17:00:00', 1);

INSERT INTO horario VALUES (5, 4, '14:30:00', '18:30:00', 2);

INSERT INTO dia_libre VALUES (1, '2023-10-10', 1);

INSERT INTO dia_libre VALUES (2, '2023-11-05', 2);

INSERT INTO dia_libre VALUES (3, '2023-12-20', 3);

INSERT INTO dia_libre VALUES (4, '2023-11-15', 5);

INSERT INTO dia_libre VALUES (5, '2023-10-25', 2);

INSERT INTO eps VALUES (1, 'EPS A', 5.0);

INSERT INTO eps VALUES (2, 'EPS B', 7.5);

INSERT INTO eps VALUES (3, 'EPS C', 6.0);

INSERT INTO eps VALUES (4, 'EPS D', 4.5);

INSERT INTO eps VALUES (5, 'EPS E', 6.5);

INSERT INTO administrador VALUES (1, 'password1', 'admin1@example.com');

INSERT INTO administrador VALUES (2, 'password2', 'admin2@example.com');

INSERT INTO administrador VALUES (3, 'password3', 'admin3@example.com');

INSERT INTO administrador VALUES (4, 'password4', 'admin4@example.com');

INSERT INTO administrador VALUES (5, 'password5', 'admin5@example.com');

INSERT INTO paciente VALUES (1, 'password1', 'nancysalgado2369@gmail.com', '1234567890', 2, 1, 'foto1.jpg', 'Juan Pérez', '1234567890', 'Ninguna', '1990-01-15', 3, 1);

INSERT INTO paciente VALUES (2, 'password2', 'paciente2@example.com', '0987654321', 1, 1, 'foto2.jpg', 'María Rodríguez', '0987654321', 'Polen', '1985-03-20', 5, 2);

INSERT INTO paciente VALUES (3, 'password3', 'paciente3@example.com', '5678901234', 3, 1, 'foto3.jpg', 'Pedro Gómez', '5678901234', 'Penicilina', '1995-07-10', 4, 1);

INSERT INTO paciente VALUES (4, 'password4', 'paciente4@example.com', '2345678901', 0, 1, 'foto4.jpg', 'Laura González', '2345678901', 'Ninguna', '1988-11-25', 2, 1);

INSERT INTO paciente VALUES (5, 'password5', 'paciente5@example.com', '3456789012', 2, 1, 'foto5.jpg', 'Ana López', '3456789012', 'Ninguna', '1992-05-30', 6, 3);

INSERT INTO cita VALUES (1, 2, '2023-10-06', '2023-10-05 09:00:00', '10:30:00', 'Consulta de rutina', 1, 1);

INSERT INTO cita VALUES (2, 2, '2023-10-06', '2023-10-05 14:15:00', '15:30:00', 'Dolor de cabeza', 2, 2);

INSERT INTO cita VALUES (3, 0, '2023-11-10', '2023-10-25 11:45:00', '12:15:00', 'Examen de sangre', 3, 3);

INSERT INTO cita VALUES (4, 0, '2023-10-30', '2023-10-10 08:30:00', '09:45:00', 'Vacunación', 1, 4);

INSERT INTO cita VALUES (5, 0, '2023-11-20', '2023-10-28 16:00:00', '16:45:00', 'Seguimiento de tratamiento', 2, 5);

INSERT INTO cita VALUES (6, 0, '2023-10-12', '2023-10-10 08:30:00', '09:45:00', 'Vacunación', 1, 5);

INSERT INTO consulta VALUES (1, 'Gripe comun', '2023-10-06', 'Descanso y tomar líquidos', 'Fiebre, dolor de garganta', 1);

INSERT INTO consulta VALUES (2, 'Dolor de cabeza', '2023-10-06', 'Recetado analgésicos', 'Dolor en la sien', 2);

INSERT INTO consulta VALUES (3, 'Examen de sangre', '2023-11-10', 'Resultados en 2 días', 'Exámenes de rutina', 3);

INSERT INTO consulta VALUES (4, 'Vacunación', '2023-10-30', 'Dolor en el brazo normal', 'Vacunación contra la gripe', 4);

INSERT INTO consulta VALUES (5, 'Seguimiento de tratamiento', '2023-11-20', 'Continuar con la medicación', 'Dolor en las articulaciones', 5);

INSERT INTO consulta VALUES (6, 'Seguimiento de tratamiento', '2023-10-12', 'Continuar con la medicación', 'Dolor en las articulaciones', 6);

INSERT INTO factura VALUES (1, 'Consulta de rutina', '2023-10-06', 50.00, 1);

INSERT INTO factura VALUES (2, 'Cita médica', '2023-10-06', 80.00, 2);

INSERT INTO factura VALUES (3, 'Examen de sangre', '2023-11-10', 35.50, 3);

INSERT INTO factura VALUES (4, 'Vacunación', '2023-10-30', 20.00, 4);

INSERT INTO factura VALUES (5, 'Seguimiento de tratamiento', '2023-11-20', 65.00, 5);

INSERT INTO tratamiento VALUES (1, 1, 5, 'Tomar una vez al día antes de las comidas', 1);

INSERT INTO tratamiento VALUES (2, 2, 12, 'Tomar dos veces al día con agua', 2);

INSERT INTO tratamiento VALUES (3, 1, 7, 'Tomar una vez al día después de las comidas', 1);

INSERT INTO tratamiento VALUES (4, 3, 18, 'Tomar tres veces al día con alimentos', 1);

INSERT INTO tratamiento VALUES (5, 2, 9, 'Tomar dos veces al día con leche', 2);

INSERT INTO pqrs VALUES (1, 'Solicitud de información adicional', 0, '2023-10-15 09:30:00', 1);

INSERT INTO pqrs VALUES (2, 'Queja sobre la atención recibida', 1, '2023-11-02 14:45:00', 2);

INSERT INTO pqrs VALUES (3, 'Sugerencia para mejorar el servicio', 2, '2023-11-10 12:00:00', 3);

INSERT INTO pqrs VALUES (4, 'Reporte de error en la factura', 1, '2023-10-30 16:15:00', 4);

INSERT INTO pqrs VALUES (5, 'Reclamo por falta de atención médica', 3, '2023-11-20 11:00:00', 5);

INSERT INTO respuesta_admin VALUES (1, '2023-10-16 10:00:00', 'Hemos recibido su solicitud y la estamos revisando.', 1, 1);

INSERT INTO respuesta_admin VALUES (2, '2023-10-09 15:00:00', 'Lamentamos su experiencia, estamos investigando su queja.', 2, 2);

INSERT INTO respuesta_admin VALUES (3, '2023-10-17 12:30:00', 'Gracias por su sugerencia, la tendremos en cuenta.', 1, 1);

INSERT INTO respuesta_admin VALUES (4, '2023-10-10 16:30:00', 'Hemos revisado su factura y corregido el error.', 2, 2);

INSERT INTO respuesta_admin VALUES (5, '2023-10-18 11:30:00', 'Estamos investigando su reclamo y le daremos una respuesta pronto.', 1, 1);

INSERT INTO respuesta_paciente VALUES (1, '2023-10-16 11:00:00', 'Gracias por su respuesta, estamos a la espera de una solución.', 1, 1, 1);

INSERT INTO respuesta_paciente VALUES (2, '2023-10-09 16:00:00', 'Esperamos una solución lo más pronto posible.', 2, 2, 2);

INSERT INTO respuesta_paciente VALUES (3, '2023-10-17 13:00:00', 'Estamos contentos de que consideren nuestra sugerencia.', 1, 1, 3);

INSERT INTO respuesta_paciente VALUES (4, '2023-10-10 17:00:00', 'Gracias por corregir el error en la factura.', 2, 2, 4);

INSERT INTO respuesta_paciente VALUES (5, '2023-10-18 12:00:00', 'Esperamos una pronta resolución de nuestro reclamo.', 1, 1, 5);




