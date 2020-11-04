CREATE TABLE status_code (
  id bigint PRIMARY KEY AUTO_INCREMENT, 
  color varchar(20)  NOT NULL,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL
);

INSERT INTO status_code (color) VALUES('publicado');
INSERT INTO status_code (color) VALUES('aguardando entrega');
INSERT INTO status_code (color) VALUES('aguardando retirada');
INSERT INTO status_code (color) VALUES('reprovado');
INSERT INTO status_code (color) VALUES('concluído');
INSERT INTO status_code (color) VALUES('cancelado');

