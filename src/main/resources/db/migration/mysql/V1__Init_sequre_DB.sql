CREATE TABLE users
(
    username varchar(255),
    password varchar(255),
    enabled  tinyint(1),
    PRIMARY KEY (username)
);

CREATE TABLE authorities
(
    username  varchar(255),
    authority varchar(255),
    FOREIGN KEY (username) references users (username)
);