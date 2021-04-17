USE Illia200453638;

DROP TABLE IF EXISTS usersA;
DROP TABLE IF EXISTS videoGames;

CREATE TABLE usersA(
	userNum INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nameUser VARCHAR(20),
    birthday DATE,
    creditCard INT(11)
) auto_increment=1;

CREATE TABLE videoGames(
	videoGamesNum INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nameGame VARCHAR(20),
    ageRating INT(11)
);

CREATE TABLE `GameUsers` (
    `UserId` int(11) NOT NULL,
    `GameId` int(11) NOT NULL,
    PRIMARY KEY (`UserId`,`GameId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1




