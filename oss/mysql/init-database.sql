mysql -uroot -p

CREATE USER 'root'@'%' IDENTIFIED BY 'root1234';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;

create database spring default character set utf8mb4;
create users 'spring'@'%' identified by 'spring1234';
grant all privileges on *.* to 'spring'@'%';
