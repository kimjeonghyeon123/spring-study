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

drop table if exists danggeun;
create table danggeun(
	id 			serial 		primary key,-- 상품아이디
	title		varchar(50) not null,	-- 상품게시물제목
	name 		varchar(50) not null,	-- 상품이름
	type_id		Integer		not null,	-- 상품타입
	type_name	varchar(50)	not null,	-- 상품타입이름
	price		int			not null,	-- 상품가격
	content 	text 		not null,	-- 상품설명
	writer_email varchar(50) not null,	-- 판매자 이메일
	writer_name varchar(50) not null,   -- 판매자 이름
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
from dang_member;

select *
from zzim_danggeun;

select *
from location;

select *
from danggeun_type;

select *
from danggeun;
insert into dang_member 
values('gojhkim123@naver.com', '12345', 'kim');
insert into dang_member 
values('earth@naver.com', '12345', 'earth');

INSERT INTO danggeun(title, name, type_id, type_name, price, content, writer_email, writer_name)
VALUES('개껌팔아요', '개껌', 1, '사료/간식', 3000, '개껌 3000원에 팝니다', 'gojhkim123@naver.com', 'kim');
INSERT INTO danggeun(title, name, type_id, type_name, price, content, writer_email, writer_name)
VALUES('영양제 팔아요', '비타민', 2, '영양제', 5000, '영양제 5000원에 팝니다', 'earth@naver.com', 'earth');

insert into danggeun_type values(0, '전체');
insert into danggeun_type values(1, '사료/간식');
insert into danggeun_type values(2, '영양제');
insert into danggeun_type values(3, '산책 용품');
insert into danggeun_type values(4, '집/방석');
insert into danggeun_type values(5, '옷/악세사리');
insert into danggeun_type values(6, '위생 용품');
insert into danggeun_type values(7, '기타 용품');

delete from zzim_danggeun;

SELECT COUNT(*) FROM danggeun WHERE true AND (title LIKE concat('%', '', '%') OR content LIKE 
concat('%', '', '%'));
----------------------------------------------------------------------









----------
--t_user--
----------
drop table if exists t_user;
create table t_user (
	id 		varchar(30)		not null	primary key
	,pwd	varchar(50)		not null
	,name	varchar(30)		not null
	,email 	varchar(30)		not null
	,birth	date			not null
	,sns 	varchar(30)		not null
	,reg_date	date  default current_timestamp
);

insert into t_user(id, pwd, name, email, birth, sns)
values('earth', '0629', 'earth', 'earth@naver.com', '2000-01-01', 'facebook');
insert into t_user(id, pwd, name, email, birth, sns)
values('earth2', '0629', 'earth', 'earth@naver.com', '2000-01-01', 'facebook');

select *
from t_user;

-----------
--t_board--
-----------
drop table if exists t_board;
create table t_board (
	bno 		serial	primary key
	,title		varchar(100)	not null
	,content	text			not null
	,writer 	varchar(30)		not null
	,view_cnt 	int		default 0
	,comment_cnt int 	default 0 
	,reg_date	date	default current_timestamp 
	,up_date 	date 	default current_timestamp
);

select *
from t_board;

SELECT COUNT(*) FROM t_board WHERE true AND (title LIKE concat('%', '', '%') OR content LIKE 
concat('%', '', '%'));

-----------
--t_chat--
-----------

drop table if exists t_userchatroom;
create table t_userchatroom(
	chatroom_id integer not null,
	login_id varchar(50) not null,
	other_id varchar(50) not null,
	primary key(chatroom_id, login_id)
);

drop table if exists t_chatroom;
create table t_chatroom(
	id serial primary key,
	recent_id varchar(50) not null,
	recent_chat text not null,
	recent_date timestamp default current_timestamp,
	unread_cnt int default 1
);

drop table if exists t_chatting;
create table t_chatting(
	id serial primary key,
	sender_id varchar(50) not null,
	chatroom_id integer not null,
	chat text not null,
	chat_date timestamp default current_timestamp,
	check_read boolean default false
);

select * from t_user;
select * from t_chatroom;
select * from t_userchatroom;
select * from t_chatting;

truncate t_chatroom restart identity;
truncate t_userchatroom restart identity; 
truncate t_chatting restart identity;

insert into t_chatroom(recent_id, recent_chat) values('earth', '안녕 earth2');
insert into t_chatting(sender_id, chatroom_id, chat) values('earth', 1, '안녕 earth2');
insert into t_userchatroom values(1, 'earth', 'earth2');
insert into t_userchatroom values(1, 'earth2', 'earth');

insert into t_chatroom(recent_id, recent_chat) values('earth3', '안녕 earth');
insert into t_chatting(sender_id, chatroom_id, chat) values('earth3', 2, '안녕 earth');
insert into t_userchatroom values(2, 'earth3', 'earth');
insert into t_userchatroom values(2, 'earth', 'earth3');

insert into t_chatroom(recent_id, recent_chat) values('earth4', '안녕 earth');
insert into t_chatting(sender_id, chatroom_id, chat) values('earth4', 3, '안녕 earth');
insert into t_userchatroom values(3, 'earth4', 'earth');
insert into t_userchatroom values(3, 'earth', 'earth4');

insert into t_chatroom(recent_id, recent_chat) values('earth5', '안녕 earth');
insert into t_chatting(sender_id, chatroom_id, chat) values('earth5', 4, '안녕 earth');
insert into t_userchatroom values(4, 'earth5', 'earth');
insert into t_userchatroom values(4, 'earth', 'earth5');

insert into t_chatroom(recent_id, recent_chat) values('earth6', '안녕 earth');
insert into t_chatting(sender_id, chatroom_id, chat) values('earth6', 5, '안녕 earth');
insert into t_userchatroom values(5, 'earth6', 'earth');
insert into t_userchatroom values(5, 'earth', 'earth6');

insert into t_chatroom(recent_id, recent_chat) values('earth7', '안녕 earth');
insert into t_chatting(sender_id, chatroom_id, chat) values('earth7', 6, '안녕 earth');
insert into t_userchatroom values(6, 'earth7', 'earth');
insert into t_userchatroom values(6, 'earth', 'earth7');
