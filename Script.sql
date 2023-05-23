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

select id, title, name, zzim_cnt
from danggeun
order by reg_date desc, id desc;


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
insert into dang_member 
values('earth@naver.com', '12345', 'earth');

INSERT INTO public.danggeun
(title, "name", type_id, local_id, price, "content", writer_email)
VALUES('123', '123', 3, 1, 2333, '123213', 'earth@naver.com');


insert into danggeun_type values(0, '전체');
insert into danggeun_type values(1, '사료/간식');
insert into danggeun_type values(2, '영양제');
insert into danggeun_type values(3, '산책 용품');
insert into danggeun_type values(4, '집/방석');
insert into danggeun_type values(5, '옷/악세사리');
insert into danggeun_type values(6, '위생 용품');
insert into danggeun_type values(7, '기타 용품');

delete from zzim_danggeun;

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
values('earth', '0629', 'earth', 'earth@naver.com', '2000-01-01', 'facebook')

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
--t_comment--
-----------
drop table if exists t_comment;
create table t_comment (
	cno				serial primary key
	,bno 			int 		not null
	,pcno			int 
	,comment		varchar(3000)
	,commenter		varchar(30)
	,reg_date		date	default now()
	,up_date 		date 	default now()
);

SELECT cno, bno, pcno, comment, commenter, reg_date, up_date
FROM t_comment;









drop table if exists t_userchatrooom;
create table t_userchatrooom(
	id serial primary key,
	user_email varchar(50) not null,
	chatroom_id integer not null
);

drop table if exists t_chatroom;
create table t_chatroom(
	id serial primary key,
	recent_id varchar(50) not null,
	recent_chatting text not null,
	recent_date date default current_timestamp,
	unread_cnt int default 1
);

drop table if exists t_chatting;
create table t_chatting(
	id serial primary key,
	sender_id varchar(50) not null,
	chatroom_id integer not null,
	chat text not null,
	chat_date date default current_timestamp,
	check_read boolean default false
);



