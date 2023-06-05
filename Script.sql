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
	type_id		Integer		not null,	-- 상품타입
	type_name 	varchar(50) not null,	-- 상품타입이름
	sido_code	varchar(10) not null,	-- 판매위치
	sido_name 	varchar(15) not null,	-- 판매위치이름
	sigoon_code	varchar(10) not null,	-- 판매위치
	sigoon_name varchar(15) not null,	-- 판매위치이름
	dong_code	varchar(10) not null,	-- 판매위치
	dong_name 	varchar(15) not null,	-- 판매위치이름
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

select * from danggeun;
truncate danggeun restart identity;
truncate zzim_danggeun;

INSERT INTO public.t_user
(id, pwd, "name", email, birth, sns, reg_date)
VALUES('earth', '1234', 'earth', 'earth@naver.com', '2020-01-01', 'fa', CURRENT_TIMESTAMP);

select * from dang_member;
select * from zzim_danggeun;
select * from danggeun_type;
select * from danggeun;

insert into dang_member values('earth@naver.com', '12345', 'earth');
insert into dang_member values('gojhkim123@naver.com', '12345', 'kim');

insert into danggeun_type values(0, '전체');
insert into danggeun_type values(1, '사료/간식');
insert into danggeun_type values(2, '영양제');
insert into danggeun_type values(3, '산책 용품');
insert into danggeun_type values(4, '집/방석');
insert into danggeun_type values(5, '옷/악세사리');
insert into danggeun_type values(6, '위생 용품');
insert into danggeun_type values(7, '기타 용품');
-----------
--t_chat--
-----------

drop table if exists t_userchatroom;
create table t_userchatroom(
	chatroom_id integer not null,
	login_email varchar(50) not null,
	other_email varchar(50) not null,
	primary key(chatroom_id, login_email)
);

drop table if exists t_chatroom;
create table t_chatroom(
	id serial primary key,
	recent_email varchar(50) not null,
	recent_chat text not null,
	recent_date timestamp default current_timestamp,
	unread_cnt int default 1
);

drop table if exists t_chatting;
create table t_chatting(
	id serial primary key,
	sender_email varchar(50) not null,
	chatroom_id integer not null,
	chat text not null,
	chat_date timestamp default current_timestamp,
	check_read boolean default false
);

select * from t_user;
select * from t_chatroom;
select * from t_userchatroom;
select * from t_chatting;

SELECT id, other_email, recent_email, recent_chat, recent_date, unread_cnt FROM t_userchatroom 
JOIN t_chatroom ON chatroom_id = id WHERE login_email = 'earth@naver.com' ORDER BY recent_date;

truncate t_chatroom restart identity;
truncate t_userchatroom restart identity; 
truncate t_chatting restart identity;

		SELECT chatroom_id
		FROM t_userchatroom
		WHERE login_email = 'gojhkim123@naver.com' and other_email = '';
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


-- location
-- sido and guggon
drop table if exists sido;
create table sido(
	sido_code varchar(10) primary key,
	sido_name varchar(15) not null
);

select * from sido;
select * from sigoon;
select * from dong;

select sigoon_code, concat(sido_name, ' ', sigoon_name)
from sigoon, sido
where sigoon_code like concat(sido_code, '%');

drop table if exists sigoon;
create table sigoon(
	sigoon_code varchar(10) primary key,
	sigoon_name varchar(15) not null
);
drop table if exists dong;
create table dong(
	dong_code varchar(10) primary key,
	dong_name varchar(15) not null
);




























