CREATE TABLE IF NOT EXISTS invocations(
    pais varchar(40) not null,
    distancia DOUBLE not null,
    invocaciones INT,
    PRIMARY KEY (pais)
);