CREATE TABLE IF NOT EXISTS `cart` (
  `CartId` varchar(10) NOT NULL,
  `ProductId` varchar(10) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Price` int NOT NULL,
  `Quantity` int NOT NULL
);

CREATE TABLE IF NOT EXISTS `product` (
  `ProductId` varchar(10) NOT NULL PRIMARY KEY,
  `Name` varchar(30) NOT NULL,
  `Price` int NOT NULL,
  `Quantity` int NOT NULL
);

CREATE TABLE IF NOT EXISTS `order` (
  `OrderId` varchar(10) NOT NULL,
  `ProductId` varchar(10) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Price` int NOT NULL,
  `Quantity` int NOT NULL,
  `UserId` int NOT NULL
);


CREATE TABLE IF NOT EXISTS `user` (
  `UserId` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `UsrName` varchar(30) NOT NULL,
  `Pass` varchar(30) NOT NULL,
  `CartId` varchar(10) NOT NULL
);