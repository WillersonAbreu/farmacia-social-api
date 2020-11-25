alter table users add role_id varchar(20) NULL;
alter table pharmacies add role_id varchar(20) NULL;

INSERT INTO pharmacies (address, cep, region, pharmaceutical, cnpj, email, fantasy_name, password, phone, role_id, latitude, longitude) VALUES

(
    'R. Cel. José de Castro, 588, Centro, Cruzeiro, SP', 
    '12701-450',
    'Centro',
    'José Osvaldo',    
    '47.112.630/0001-11',
    'farmavalemanipulacao@farmavale.com', 
    'Farma Vale - Farmacia de manuipulação', 
    '$2a$10$24H8wf8Iwv8udxKQ2t6ElewrOzwUkTEcy7m4Au6ICxZ0/S3UCxZMG', 
    '(12) 98173-5652',
    2,
    '-22.5772988130571', 
    '-44.96031287175403'
),
(
    'Av. Maj. Novaes, 166, Centro, Cruzeiro, SP', 
    '12701-330',
    'Centro', 
    'José Antônio',
    '68.442.646/0001-62',
    'droaraiacentro@drogaraia.com', 
    'Droga Raia', 
    '$2a$10$24H8wf8Iwv8udxKQ2t6ElewrOzwUkTEcy7m4Au6ICxZ0/S3UCxZMG', 
    '(12) 99783-3345',
    2,
    '-22.576653431244342',  
    '-44.95925907560592'
),
(
    'Av. Nesralla Rubez, 707, Centro, Cruzeiro, SP', 
    '12701-000',
    'Centro', 
    'Mario Sérgio Santana',
    '42.156.140/0001-01',
    'farmacondecruzeiro1@farmaconde.com', 
    'Farma Conde - Centro', 
    '$2a$10$24H8wf8Iwv8udxKQ2t6ElewrOzwUkTEcy7m4Au6ICxZ0/S3UCxZMG', 
    '(12) 99685-5321',
    2,
    '-22.576565675940117',
    '-44.95929427088797'
),
(
    'Av. Maj. Novaes, 315, Centro, Cruzeiro, SP', 
    '12701-330',
    'Centro',
    'Antônio Bastos',
    '36.683.793/0001-38', 
    'farmaultrapopular@ultrapopular.com', 
    'Farma Ultrapopular', 
    '$2a$10$24H8wf8Iwv8udxKQ2t6ElewrOzwUkTEcy7m4Au6ICxZ0/S3UCxZMG', 
    '(12) 99645-4790',
    2,
    '-22.576517575171014',
    '-44.96078745562976'
),
(
    'Av. Maj. Novaes, 378, Centro, Cruzeiro, SP', 
    '12701-330',
    'Centro', 
    'João Almeida',
    '87.112.848/0001-90',
    'drogariaavenida@drogariaavenida.com', 
    'Drogaria Avenida', 
    '$2a$10$24H8wf8Iwv8udxKQ2t6ElewrOzwUkTEcy7m4Au6ICxZ0/S3UCxZMG', 
    '(12) 98575-4790',
    2,
    '-22.57596924615876',
    '-44.96124979228621'
)