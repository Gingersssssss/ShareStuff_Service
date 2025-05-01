create table stuff(
    id BIGINT auto_increment PRIMARY KEY,
    title varchar(255) NOT NULL,
    category varchar(255) NOT NULL,
    stock int NOT NULL
)


insert into stuff(title, category, stock) values('เต็นท์', 'อุปกรณ์แคมป์ปิ้ง', 5);
insert into stuff(title, category, stock) values('หนังสือ Java', 'หนังสือ', 3);
insert into stuff(title, category, stock) values('ไม้กวาด', 'อุปกรณ์ทำความสะอาด', 2);
insert into stuff(title, category, stock) values('เตาแก๊ส', 'อุปกรณ์ทำอาหาร', 4);
insert into stuff(title, category, stock) values('หม้อหุงข้าว', 'อุปกรณ์ทำอาหาร', 6);

