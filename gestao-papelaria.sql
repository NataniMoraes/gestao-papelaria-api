-- CRIAR O BANCO DE DADOS
CREATE DATABASE IF NOT EXISTS gestao_papelaria
CHARACTER SET utf8mb4 -- BANCO IRÁ SUPORTAR ACENTOS
COLLATE utf8mb4_unicode_ci; -- BANCO IRÁ SURPORTAR CARACTERES ESPECIAIS

use gestao_papelaria
INSERT INTO categorias (nome) VALUES ('Canetas e Marcadores');
INSERT INTO categorias (nome) VALUES ('Cadernos e Blocos');
INSERT INTO categorias (nome) VALUES ('Material de Escritório');
INSERT INTO categorias (nome) VALUES ('Arte e Pintura');
INSERT INTO categorias (nome) VALUES ('Papelaria Fofa');

-- Categoria 1: Canetas e Marcadores
INSERT INTO produtos (nome, descricao, preco, quantidade_estoque, codigo_barras, categoria_id) VALUES 
('Kit 10 Canetas Gel Coloridas', 'Cores vibrantes, ponta 0.5mm', 29.90, 100, '7890000000011', 1),
('Marca Texto Pastel Kit 4 Cores', 'Cores suaves, ponta chanfrada', 18.50, 150, '7890000000012', 1),
('Caneta Ponta Fina (Fineliner) Preta', 'Perfeita para contornos, 0.4mm', 4.50, 200, '7890000000013', 1);

-- Categoria 2: Cadernos e Blocos
INSERT INTO produtos (nome, descricao, preco, quantidade_estoque, codigo_barras, categoria_id) VALUES 
('Caderno 10 Matérias Candy (Capa Dura)', 'Caderno espiral 160 folhas', 32.00, 80, '7890000000021', 2),
('Bloco Adesivo (Post-it) Coração', 'Bloco rosa em formato de coração', 12.00, 300, '7890000000022', 2),
('Caderno A5 Pontilhado (Bullet Journal)', 'Capa de couro sintético, 80g', 45.00, 50, '7890000000023', 2);

-- Categoria 3: Material de Escritório
INSERT INTO produtos (nome, descricao, preco, quantidade_estoque, codigo_barras, categoria_id) VALUES 
('Clips de Papel Coloridos (50 un.)', 'Clips em formato de gota, cores pastel', 8.00, 500, '7890000000031', 3),
('Grampeador Mini Rosa Pastel', 'Grampeador pequeno para até 15 folhas', 22.00, 70, '7890000000032', 3);

-- Categoria 4: Arte e Pintura
INSERT INTO produtos (nome, descricao, preco, quantidade_estoque, codigo_barras, categoria_id) VALUES 
('Lápis de Cor Aquarelável 24 Cores', 'Caixa com 24 lápis aquareláveis', 55.00, 40, '7890000000041', 4),
('Tinta Guache 6 Cores Neon', 'Cores vibrantes que brilham na luz UV', 14.90, 60, '7890000000042', 4);

-- Categoria 5: Papelaria Fofa (Candy)
INSERT INTO produtos (nome, descricao, preco, quantidade_estoque, codigo_barras, categoria_id) VALUES 
('Borracha Fofa Formato Picolé', 'Borracha divertida em 3 modelos', 5.00, 400, '7890000000051', 5),
('Washi Tape (Fita Decorativa) Kit Candy', 'Kit com 5 fitas adesivas decorativas', 19.90, 120, '7890000000052', 5),
('Apontador Gatinho com Depósito', 'Apontador fofo com depósito', 9.50, 150, '7890000000053', 5);