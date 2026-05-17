-- USUARIS
INSERT INTO usuari (nom, contrassenya, rol) VALUES
('Pau', '1234', 'ESPORTISTA'),
('Carles', '1234', 'ADMIN'),
('Pep', '1234', 'ENTRENADOR'),
('admin1', 'admin123', 'ADMIN'),
('joan', 'joan123', 'ESPORTISTA'),
('marta', 'marta123', 'ESPORTISTA'),
('pere', 'pere123', 'ENTRENADOR'),
('laia', 'laia123', 'ENTRENADOR');

-- TIPUS D'ESPORT
INSERT INTO tipus_esport (nom) VALUES
('Ciclisme'),
('Running'),
('Natacio'),
('Trail Running'),
('Esqui');

-- ENTRENAMENTS
INSERT INTO entrenament 
(`data`, duradaMinuts, distancia, descripcio, intensitat, completat, validat, usuari_id, tipus_esport_id)
VALUES
('2026-05-01', 90, 45, 'Sortida amb bicicleta per carretera', 'MITJA', true, true, 1, 1),

('2026-05-03', 60, 10, 'Entrenament de running suau', 'BAIXA', true, true, 5, 2),

('2026-05-04', 120, 70, 'Ruta llarga amb desnivell', 'ALTA', true, false, 6, 1),

('2026-05-06', 45, 2, 'Sessio de natacio tecnica', 'BAIXA', true, true, 1, 3),

('2026-05-08', 150, 25, 'Trail running per muntanya', 'ALTA', false, false, 5, 4),

('2026-05-10', 180, 40, 'Esqui de muntanya al Pirineu', 'ALTA', true, true, 6, 5);

-- COMENTARIS
INSERT INTO comentari
(text, `data`, entranador_id, entranament_id)
VALUES
('Bon ritme durant tota la sessio', '2026-05-02', 3, 1),

('Cal millorar la tecnica de carrera', '2026-05-04', 7, 2),

('Entrenament exigent pero ben completat', '2026-05-05', 3, 3),

('Molt bona resistencia a la muntanya', '2026-05-09', 8, 5),

('Perfecte control del ritme i intensitat', '2026-05-11', 7, 6);