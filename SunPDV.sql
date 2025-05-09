-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 09/05/2025 às 16:38
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `SunPDV`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `cargo`
--

CREATE TABLE `cargo` (
  `ID_Cargo` int(11) NOT NULL,
  `Cargo` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cargo`
--

INSERT INTO `cargo` (`ID_Cargo`, `Cargo`) VALUES
(1, 'CargoADM'),
(2, 'CargoMOD'),
(3, 'CargoFUN');

-- --------------------------------------------------------

--
-- Estrutura para tabela `carrinho`
--

CREATE TABLE `carrinho` (
  `ID_Carrinho` int(11) NOT NULL,
  `ID_Produto` int(11) NOT NULL,
  `Cod_Barras` varchar(35) DEFAULT NULL,
  `SubTotal` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `login_sistema`
--

CREATE TABLE `login_sistema` (
  `ID_LOGIN` smallint(6) NOT NULL,
  `Nome` varchar(35) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Senha` varchar(20) NOT NULL,
  `ID_Cargo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `login_sistema`
--

INSERT INTO `login_sistema` (`ID_LOGIN`, `Nome`, `Email`, `Senha`, `ID_Cargo`) VALUES
(1, 'João Mendes', 'jpmendes@gmail.com', '1234', 1),
(2, 'João Schinato', 'jpschinato@gmail.com', '1234', 2),
(3, 'Toshi', 'toshi@gmail.com', '1234', 3);

-- --------------------------------------------------------

--
-- Estrutura para tabela `pagamento`
--

CREATE TABLE `pagamento` (
  `ID_Pagamento` int(11) NOT NULL,
  `Forma_Pagamento` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `pagamento`
--

INSERT INTO `pagamento` (`ID_Pagamento`, `Forma_Pagamento`) VALUES
(1, 'Dinheiro'),
(2, 'Débito'),
(3, 'Crédito'),
(4, 'Pix'),
(5, 'Voucher');

-- --------------------------------------------------------

--
-- Estrutura para tabela `produtos`
--

CREATE TABLE `produtos` (
  `ID_Produto` int(11) NOT NULL,
  `Nome` varchar(40) NOT NULL,
  `Cod_Barras` varchar(35) NOT NULL,
  `Preco` double NOT NULL COMMENT 'Preço em BRL'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produtos`
--

INSERT INTO `produtos` (`ID_Produto`, `Nome`, `Cod_Barras`, `Preco`) VALUES
(1, 'cafe', '789738277382', 50),
(2, 'feijao', '4545155454', 60.98),
(4, 'leite', '54646464', 1),
(5, 'leite', '20', 45),
(6, 'bolacha', '656565654', 49.9),
(7, 'cetoprofeno', '7891317001926', 1.99);

-- --------------------------------------------------------

--
-- Estrutura para tabela `user_sistema`
--

CREATE TABLE `user_sistema` (
  `ID_User` smallint(6) NOT NULL,
  `ID_LOGIN` smallint(6) DEFAULT NULL,
  `ID_Venda` int(11) DEFAULT NULL,
  `ID_Produto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `user_sistema`
--

INSERT INTO `user_sistema` (`ID_User`, `ID_LOGIN`, `ID_Venda`, `ID_Produto`) VALUES
(6, 1, NULL, NULL),
(7, 2, NULL, NULL),
(8, 3, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `vendas`
--

CREATE TABLE `vendas` (
  `ID_Venda` int(11) NOT NULL,
  `SubTotal` double NOT NULL,
  `ID_Pagamento` int(11) DEFAULT NULL,
  `Total` double NOT NULL,
  `Data_Venda` date DEFAULT NULL,
  `ID_Carrinho` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `cargo`
--
ALTER TABLE `cargo`
  ADD PRIMARY KEY (`ID_Cargo`);

--
-- Índices de tabela `carrinho`
--
ALTER TABLE `carrinho`
  ADD PRIMARY KEY (`ID_Carrinho`),
  ADD KEY `Ligação entre produtos e carrinho` (`ID_Produto`),
  ADD KEY `Ligação de produtos e carrinho com codbarras` (`Cod_Barras`);

--
-- Índices de tabela `login_sistema`
--
ALTER TABLE `login_sistema`
  ADD PRIMARY KEY (`ID_LOGIN`),
  ADD KEY `Login com o cargo selecionado` (`ID_Cargo`);

--
-- Índices de tabela `pagamento`
--
ALTER TABLE `pagamento`
  ADD PRIMARY KEY (`ID_Pagamento`),
  ADD UNIQUE KEY `Forma_Pagamento` (`Forma_Pagamento`) USING HASH,
  ADD KEY `idx_pagamento` (`ID_Pagamento`);

--
-- Índices de tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`ID_Produto`),
  ADD UNIQUE KEY `Cod_Barras` (`Cod_Barras`);

--
-- Índices de tabela `user_sistema`
--
ALTER TABLE `user_sistema`
  ADD PRIMARY KEY (`ID_User`),
  ADD KEY `Ligação entre user e login` (`ID_LOGIN`),
  ADD KEY `ligação com user e produtos` (`ID_Produto`),
  ADD KEY `Ligação entre vendas e user` (`ID_Venda`);

--
-- Índices de tabela `vendas`
--
ALTER TABLE `vendas`
  ADD PRIMARY KEY (`ID_Venda`),
  ADD KEY `ligação entre pagamento e vendas` (`ID_Pagamento`),
  ADD KEY `Ligação entre carrinho e vendas` (`ID_Carrinho`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `cargo`
--
ALTER TABLE `cargo`
  MODIFY `ID_Cargo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `carrinho`
--
ALTER TABLE `carrinho`
  MODIFY `ID_Carrinho` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `login_sistema`
--
ALTER TABLE `login_sistema`
  MODIFY `ID_LOGIN` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `pagamento`
--
ALTER TABLE `pagamento`
  MODIFY `ID_Pagamento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `ID_Produto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `user_sistema`
--
ALTER TABLE `user_sistema`
  MODIFY `ID_User` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `vendas`
--
ALTER TABLE `vendas`
  MODIFY `ID_Venda` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `carrinho`
--
ALTER TABLE `carrinho`
  ADD CONSTRAINT `Ligação de produtos e carrinho com codbarras` FOREIGN KEY (`Cod_Barras`) REFERENCES `produtos` (`Cod_Barras`),
  ADD CONSTRAINT `Ligação entre produtos e carrinho` FOREIGN KEY (`ID_Produto`) REFERENCES `produtos` (`ID_Produto`);

--
-- Restrições para tabelas `login_sistema`
--
ALTER TABLE `login_sistema`
  ADD CONSTRAINT `Login com o cargo selecionado` FOREIGN KEY (`ID_Cargo`) REFERENCES `cargo` (`ID_Cargo`);

--
-- Restrições para tabelas `user_sistema`
--
ALTER TABLE `user_sistema`
  ADD CONSTRAINT `Ligação entre user e login` FOREIGN KEY (`ID_LOGIN`) REFERENCES `login_sistema` (`ID_LOGIN`),
  ADD CONSTRAINT `Ligação entre vendas e user` FOREIGN KEY (`ID_Venda`) REFERENCES `vendas` (`ID_Venda`),
  ADD CONSTRAINT `ligação com user e produtos` FOREIGN KEY (`ID_Produto`) REFERENCES `produtos` (`ID_Produto`),
  ADD CONSTRAINT `ligação entre user e vendas` FOREIGN KEY (`ID_Venda`) REFERENCES `vendas` (`ID_Venda`);

--
-- Restrições para tabelas `vendas`
--
ALTER TABLE `vendas`
  ADD CONSTRAINT `Ligação entre carrinho e vendas` FOREIGN KEY (`ID_Carrinho`) REFERENCES `carrinho` (`ID_Carrinho`),
  ADD CONSTRAINT `ligação entre pagamento e vendas` FOREIGN KEY (`ID_Pagamento`) REFERENCES `pagamento` (`ID_Pagamento`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
