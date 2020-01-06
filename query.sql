-- Lista de comandos em SQL

-- Limpar a estrutura da tabela Clube se existir.
DROP TABLE IF EXISTS Clube;

CREATE TABLE Clube(
Id INT AUTO_INCREMENT, 
Nome VARCHAR(255) DEFAULT 'Brasil',
Ativo BOOL DEFAULT true,
Fundacao DATE NOT NULL,
PRIMARY KEY(Id),
);

-- Limpar a estrutura da tabela Jogador se existir.
DROP TABLE IF EXISTS Jogador;

-- Cria uma tabela de nome Jogador com as colunas Id (chave primária),
-- Nome (cadeia de caracteres - string), Idade (inteiro), Ativo (booleano), Nascimento (data),
-- idClube (chave estrangeira, referenciando o Id de Clube).
CREATE TABLE Jogador(
Id INT AUTO_INCREMENT, 
Nome VARCHAR(255),
Idade INT,
Ativo BOOL,
Nascimento DATE,
idClube INT NOT NULL,
PRIMARY KEY(Id),
FOREIGN KEY(idClube) REFERENCES Clube(Id)
);

-- Insere, de forma múltipla, duas seleções de futebol na tabela Clube,
-- indicando os valores Nome (cadeia de caracteres - string), Ativo (booleano),
-- e Fundacao (data) para cada um deles.
INSERT INTO Clube (Nome, Ativo, Fundacao) VALUES 
('Brasil', true, '1914-06-08'),
('USA', true, '1985-08-18');

-- Insere, de forma única, uma jogadora de futebol na seleção de Id 1 (Brasil).
INSERT INTO Jogador (Nome, Idade, Ativo, Nascimento, idClube) 
VALUES ('Marta', 34, true, '1986-02-19', 1);

-- Insere, de forma múltipla, jogadores de futebol nas seleções de Id 1 (Brasil) e Id 2 (Estados Unidos).
INSERT INTO Jogador (Nome, Idade, Ativo, Nascimento, idClube) VALUES 
('Megan', 34, true, '1985-07-05', 2),
('Pelé', 79, false, '1940-10-23', 1),
('Cristiane', 34, true, '1985-05-15', 1),
('Hope', 38, false, '1981-07-21', 2);

-- Pesquisa por todas as informações/linhas da tabela Clube, com todas as colunas (*).
SELECT * FROM Clube;

-- Realiza uma junção (JOIN) entre as tabelas Jogador e Clube na pesquisa, 
-- para todas as colunas (*), referenciando o idClube de cada linha do Jogador
-- com uma linha com Id do Clube para isso.
SELECT * FROM Jogador INNER JOIN Clube ON Jogador.idClube = Clube.Id;

-- Realiza uma junção (JOIN) entre as tabelas Jogador e Clube na pesquisa,
-- para colunas selecionadas (Jogador.Nome, Idade, Jogador.Ativo, Nascimento, 
-- Clube.Nome, Clube.Ativo, Fundacao), referenciando o idClube de cada linha do Jogador
-- a cada linha com Id do Clube para isso.
SELECT Jogador.Nome, Idade, Jogador.Ativo, Nascimento, Clube.Nome, Clube.Ativo, Fundacao
FROM Jogador INNER JOIN Clube ON Jogador.idClube = Clube.Id;

-- Agrupa os jogadores na tabela de acordo com a seleção que cada um participa, retornando
-- a quantidade em cada equipe (através do COUNT).
SELECT COUNT(Jogador.Id) FROM Jogador GROUP BY idClube;

-- Pesquisa por informações dos jogadores, com nomes em ordem alfabética (e invertida em DESC).
SELECT * FROM Jogador ORDER BY Nome ASC;
SELECT * FROM Jogador ORDER BY Nome DESC;

-- Pesquisa por todas as informações/linhas que tivermos na tabela Jogador.
SELECT * FROM Jogador;

-- Pesquisa por todas as informações/linhas que tivermos na tabela Jogador, filtrando
-- os jogadores que estiverem na ativa (WHERE Ativo = true).
SELECT * FROM Jogador WHERE Ativo = true;

-- Atualiza a jogadora de nome Marta (WHERE Nome = 'Marta') para inativa (SET Ativo = false) 
UPDATE Jogador SET Ativo = false WHERE Nome = 'Marta';

-- Remove o jogador de nome Pelé (WHERE Nome = 'Pelé') da tabela Jogador 
DELETE FROM Jogador WHERE Nome = 'Pelé';

DROP TABLE IF EXISTS Product;

CREATE TABLE Product (
    Id INT AUTO_INCREMENT, 
    raca VARCHAR(255),
    valor FLOAT,
    quantidade INT
);