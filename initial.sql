create
database "users";
create
users users with encrypted password 'users1234';
grant all privileges on database
users to users;

create
database product;
create
users product with encrypted password 'product1234';
grant all privileges on database
product to product;

create
database "order";
create
users product with encrypted password 'product1234';
grant all privileges on database
product to product;