create table borrowedStuff(
    id BIGINT auto_increment PRIMARY KEY,
    title varchar(255) NOT NULL,
    category varchar(255) NOT NULL,
    borrower varchar(255) NOT NULL
    --time int NOT NULL
)

create table returnedStuff(
    id BIGINT auto_increment PRIMARY KEY,
    title varchar(255) NOT NULL,
    category varchar(255) NOT NULL,
    returner varchar(255) NOT NULL
    --time int NOT NULL
)

insert into borrowedStuff(title, category, borrower) values('ป้ายไฟ', 'อุปกรณ์ตกแต่ง', 'A');
insert into borrowedStuff(title, category, borrower) values('ไขควง', 'อุปกรณ์ช่าง', 'B');
insert into borrowedStuff(title, category, borrower) values('สว่าน', 'อุปกรณ์ช่าง', 'C');

insert into returnedStuff(title, category, returner) values('พลั่ว', 'อุปกรณ์ทำสวน', 'A');
insert into returnedStuff(title, category, returner) values('หม้อ', 'อุปกรณ์ตกแต่ง', 'B');
insert into returnedStuff(title, category, returner) values('ไห', 'อุปกรณ์ตกแต่ง', 'C');

