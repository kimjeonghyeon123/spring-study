drop table if exists dang_board;
create table dang_board(
	bno 		serial 		primary key,
	title 		varchar(50) not null,
	content 	text 		not null,
	writer 		varchar(50) not null,
	view_cnt 	int 		default 0,
	comment_cnt int 		default 0,
	reg_date 	date 		default current_timestamp,
	up_date 	date 		default current_timestamp
);

select *
from dang_board;

		SELECT bno, title, writer, reg_date, view_cnt
		FROM dang_board
		WHERE bno = 1;

insert into dang_board(title, writer, content)
values('title', 'writer', 'abcdefg');

truncate dang_board restart identity;
truncate danggeun restart identity;

drop table if exists dang_member;
create table dang_member(
	email varchar(50) primary key,
	pwd varchar(50) not null,
	name varchar(50) not null
);

select *
from dang_member;

insert into dang_member 
values('gojhkim123@naver.com', '12345', 'kim');


drop table if exists danggeun;
create table danggeun(
	id 			serial 		primary key,-- 상품아이디
	title		varchar(50) not null,	-- 상품게시물제목
	name 		varchar(50) not null,	-- 상품이름
	type_id		Integer		not null,	-- 상품타입
	local_id	Integer		not null,	-- 판매지역
	price		int			not null,	-- 상품가격
	content 	text 		not null,	-- 상품설명
	writer 		varchar(50) not null,	-- 판매자
	view_cnt 	int 		default 0,	-- 조회수
	add_cnt		int			default 0,	-- 찜 수
	reg_date 	date 		default current_timestamp,
	up_date 	date 		default current_timestamp,
	foreign key (type_id) references danggeun_type(id)
);

insert into danggeun(title, name, type_id, local_id, price, content, writer)
values('1', '1', 1, 1, 3000, '1', '1');

drop table if exists danggeun_type;
create table danggeun_type(
	id serial primary key,
	name varchar(50) not null
);

insert into danggeun_type(name) values('사료/간식');
insert into danggeun_type(name) values('영양제');
insert into danggeun_type(name) values('산책 용품');
insert into danggeun_type(name) values('집/방석');
insert into danggeun_type(name) values('옷/악세사리');
insert into danggeun_type(name) values('위생 용품');
insert into danggeun_type(name) values('기타 용품');

select *
from danggeun;

select *
from danggeun_type;


SELECT name
FROM danggeun_type
WHERE id = 1;













