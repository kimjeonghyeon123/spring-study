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

drop table if exists dang_member;
create table dang_member(
	email varchar(50) primary key,
	pwd varchar(50) not null,
	name varchar(50) not null
);

drop table if exists danggeun_type;
create table danggeun_type(
	id Integer primary key,
	name varchar(50) not null
);

drop table if exists location;
create table location(
	id int primary key,
	name varchar(50) not null
);

drop table if exists danggeun;
create table danggeun(
	id 			serial 		primary key,-- 상품아이디
	title		varchar(50) not null,	-- 상품게시물제목
	name 		varchar(50) not null,	-- 상품이름
	type_id		Integer		not null,	-- 상품타입
	local_id	Integer		not null,	-- 판매지역
	price		int			not null,	-- 상품가격
	content 	text 		not null,	-- 상품설명
	writer_email varchar(50) not null,	-- 판매자
	view_cnt 	int 		default 0,	-- 조회수
	zzim_cnt	int			default 0,	-- 찜 수
	reg_date 	date 		default current_timestamp,
	up_date 	date 		default current_timestamp
);

drop table if exists zzim_danggeun;
create table zzim_danggeun(
	member_email varchar(50),
	danggeun_id Integer,
	primary key(member_email, danggeun_id)
);

select *
from danggeun;

select *
from dang_member;

select *
from zzim_danggeun;

select *
from location;

select *
from danggeun_type;


insert into dang_member 
values('gojhkim123@naver.com', '12345', 'kim');

insert into danggeun_type values(0, '전체');
insert into danggeun_type values(1, '사료/간식');
insert into danggeun_type values(2, '영양제');
insert into danggeun_type values(3, '산책 용품');
insert into danggeun_type values(4, '집/방석');
insert into danggeun_type values(5, '옷/악세사리');
insert into danggeun_type values(6, '위생 용품');
insert into danggeun_type values(7, '기타 용품');

delete from zzim_danggeun;
