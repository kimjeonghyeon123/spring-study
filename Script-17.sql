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


insert into zzim_danggeun values('gojhkim123@naver.com', '1');

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













truncate t_board restart identity;
truncate t_comment restart identity;

select * from t_board;
select * from t_comment;
select * from t_user;

INSERT INTO t_comment(bno, pcno, "comment", commenter, reg_date, up_date)
VALUES(1, 1, '댓글테스트', 'earth1234', now(), now());

update t_board set comment_cnt = comment_cnt + 1 where bno = 1;

drop table if exists t_chat;
create table t_chat(
	id 			serial 		primary key,-- 상품아이디
	sender_id varchar(50) not null,
	content text not null,
	reg_date 	date 		default current_timestamp
);

select * from t_chat;

truncate t_chat restart identity;

select * from t_chat order by reg_date;


drop table if exists t_userchatroom;
create table t_userchatroom(
	user_id varchar(50),
	chatroom_id integer,
	primary key(user_id, chatroom_id)
);
truncate t_userchatroom;

insert into t_userchatroom values ('earth1', 1);
insert into t_userchatroom values ('earth1', 2);
insert into t_userchatroom values ('earth1', 4);
insert into t_userchatroom values ('earth2', 1);
insert into t_userchatroom values ('earth2', 4);
insert into t_userchatroom values ('kim', 2);
insert into t_userchatroom values ('park', 3);
insert into t_userchatroom values ('jang', 3);

select chatroom_id
from t_userchatroom
where user_id = 'earth1';

select user_id
from t_userchatroom
where chatroom_id = 1 and user_id != 'earth1';

drop table if exists t_chatroom;
create table t_chatroom(
	id serial primary key,
	last_date date default current_timestamp,
	last_message text
);

drop table if exists t_chatlog;
create table t_chatlog(
	id serial primary key,
	chatroom_id integer not null,
	user_id varchar(50) not null,
	content text not null,
	chat_date date default current_timestamp,
	check_status boolean default false
);

SELECT chatroom_id, user_id
FROM t_userchatroom a
WHERE chatroom_id IN (
  SELECT chatroom_id
  FROM t_userchatroom b
  WHERE user_id = 'earth1'
) AND user_id != 'earth1';


select * from t_userchatroom;

delete from t_userchatroom 
where chatroom_id in (
	select chatroom_id
	from t_userchatroom
	where user_id = 'earth1'
);

select chatroom_id
from t_userchatroom
where user_id = 'earth1';





