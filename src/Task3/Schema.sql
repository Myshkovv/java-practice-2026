create table Product(
    id serial unique not null,
	title char(30),
	price integer check (price > -1)
);

insert into Product (title, price) values ('milk', 210);
insert into Product (title, price) values ('bread', 68);