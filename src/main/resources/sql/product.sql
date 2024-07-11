create schema if not exists product;

drop table if exists product.category;
drop table if exists product.product;

create table product.category (
    id serial primary key,
    name varchar(255) not null
);

create table product.product (
    id serial primary key,
    name varchar(255) not null,
    description text not null,
    price decimal(10, 2) not null,
    category_id integer not null references product.category(id)
);

insert into product.product (name, description, price, category_id) values ('Car', 'A car', 10000.00, 1);
insert into product.product (name, description, price, category_id) values ('Motorcycle', 'A motorcycle', 5000.00, 1);
insert into product.product (name, description, price, category_id) values ('Bicycle', 'A bicycle', 300.00, 1);
insert into product.product (name, description, price, category_id) values ('Truck', 'A truck', 15000.00, 1);
insert into product.product (name, description, price, category_id) values ('Phone', 'A phone', 500.00, 2);
insert into product.product (name, description, price, category_id) values ('Computer', 'A computer', 2000.00, 2);
insert into product.product (name, description, price, category_id) values ('Tablet', 'A tablet', 300.00, 2);
insert into product.product (name, description, price, category_id) values ('Laptop', 'A laptop', 1000.00, 2);
insert into product.product (name, description, price, category_id) values ('Book', 'A book', 20.00, 3);
insert into product.product (name, description, price, category_id) values ('Magazine', 'A magazine', 5.00, 3);
insert into product.product (name, description, price, category_id) values ('Novel', 'A novel', 10.00, 3);
insert into product.product (name, description, price, category_id) values ('Shirt', 'A shirt', 30.00, 4);
insert into product.product (name, description, price, category_id) values ('Pants', 'A pants', 40.00, 4);
insert into product.product (name, description, price, category_id) values ('Dress', 'A dress', 50.00, 4);
insert into product.product (name, description, price, category_id) values ('Chair', 'A chair', 50.00, 5);
insert into product.product (name, description, price, category_id) values ('Table', 'A table', 100.00, 5);
insert into product.product (name, description, price, category_id) values ('Sofa', 'A sofa', 200.00, 5);
insert into product.product (name, description, price, category_id) values ('Soap', 'A soap', 1.00, 6);
insert into product.product (name, description, price, category_id) values ('Shampoo', 'A shampoo', 10.00, 6);
insert into product.product (name, description, price, category_id) values ('Toothpaste', 'A toothpaste', 5.00, 6);
insert into product.product (name, description, price, category_id) values ('Doll', 'A doll', 10.00, 7);
insert into product.product (name, description, price, category_id) values ('Toy', 'A toy', 5.00, 7);
insert into product.product (name, description, price, category_id) values ('Game', 'A game', 20.00, 7);
insert into product.product (name, description, price, category_id) values ('Ball', 'A ball', 15.00, 8);
insert into product.product (name, description, price, category_id) values ('Other', 'Other', 0.00, 9);


insert into product.category (name) values ('Automotive');
insert into product.category (name) values ('Electronics');
insert into product.category (name) values ('Books');
insert into product.category (name) values ('Clothing');
insert into product.category (name) values ('Home & Garden');
insert into product.category (name) values ('Health & Beauty');
insert into product.category (name) values ('Toys & Hobbies');
insert into product.category (name) values ('Sporting Goods');
insert into product.category (name) values ('Other');
