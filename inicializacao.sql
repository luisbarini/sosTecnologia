CREATE USER 'patrimonio'@'%' IDENTIFIED BY 'qwerty321';
GRANT ALL PRIVILEGES ON * . * TO 'patrimonio'@'%';
FLUSH PRIVILEGES;
create database patrimonio;