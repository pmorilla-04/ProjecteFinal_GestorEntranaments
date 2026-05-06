use Entranaments;
-- =========================
-- USUARIS
-- =========================
INSERT INTO usuari (nom, contrassenya, rol) VALUES
('Carles', '1234','Admin'),
('Pau', '1234', 'Esportista'),
('Pep', '1234', 'Entrenador'),
('joan', 'pass123', 'Esportista'),
('maria', 'pass123', 'Esportista'),
('pep', 'pass123', 'Entrenador'),
('laura', 'pass123', 'Entrenador');

-- =========================
-- TIPUS D'ESPORT
-- =========================
INSERT INTO tipus_esport (nom) VALUES
('Ciclisme'),
('Running'),
('Natacio'),
('Gimnas'),
('Esqui');

-- =========================
-- ENTRENAMENTS
-- =========================
INSERT INTO entrenament (`data`, duradaMinuts, distancia, descripcio, intensitat, completat, usuari_id, tipus_esport_id) VALUES
('2026-04-01', 90, 15, 'Sortida per muntanya', 'Alta', true, 2, 1),
('2026-04-02', 45, 8, 'Running suau', 'Baixa', true, 3, 2),
('2026-04-03', 60, 600, 'Piscina tècnica', 'Mitja', false, 2, 3),
('2026-04-04', 120, 60, 'Port de muntanya', 'Alta', true, 3, 1),
('2026-04-05', 50, null,'Entrenament de força', 'Mitja', true, 2, 4),
('2026-04-06', 180, 15, 'Esquí de muntanya', 'Alta', false, 3, 5);

-- =========================
-- COMENTARIS
-- =========================
INSERT INTO comentari (text, `data`, entranador_id, entranament_id) VALUES
('Molt bon entrenament!', '2026-04-01', 4, 1),
('Has de millorar la tècnica', '2026-04-02', 5, 2),
('Bon ritme, segueix així', '2026-04-03', 4, 3),
('Intensitat massa alta, vigila', '2026-04-04', 5, 4),
('Perfecte per començar la setmana', '2026-04-05', 4, 5),
('Treballa més la resistència', '2026-04-06', 5, 6);

