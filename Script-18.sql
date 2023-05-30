drop table if exists danggeun_type;
create table danggeun_type(
	id Integer primary key,
	name varchar(50) not null
);

insert into danggeun_type values(0, '전체');
insert into danggeun_type values(1, '사료/간식');
insert into danggeun_type values(2, '영양제');
insert into danggeun_type values(3, '산책 용품');
insert into danggeun_type values(4, '집/방석');
insert into danggeun_type values(5, '옷/악세사리');
insert into danggeun_type values(6, '위생 용품');
insert into danggeun_type values(7, '기타 용품');

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