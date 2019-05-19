GRANT ALL PRIVILEGES ON *.* TO root@'*' IDENTIFIED BY 'spring1234' WITH GRANT OPTION;

create database spring default character set utf8mb4;
create user 'spring'@'%' identified by 'spring1234';
grant all privileges on *.* to 'spring'@'%';
