CREATE TABLE status_code (
  id bigint PRIMARY KEY AUTO_INCREMENT, 
  status_string varchar(20)  NOT NULL,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL
);

INSERT INTO status_code (status_string) VALUES('publicado');
INSERT INTO status_code (status_string) VALUES('aguardando entrega');
INSERT INTO status_code (status_string) VALUES('aguardando retirada');
INSERT INTO status_code (status_string) VALUES('reprovado');
INSERT INTO status_code (status_string) VALUES('concluído');
INSERT INTO status_code (status_string) VALUES('cancelado');

