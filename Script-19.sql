drop table if exists tb_address_sido;
create table tb_address_sido(
	code varchar(30) primary key,
	name varchar(30) not null
);

drop table if exists tb_address_sigoon;
create table tb_address_sigoon(
	code varchar(30) primary key,
	name varchar(30) not null,
	sido_code varchar(30) not null,
	foreign key(sido_code) references tb_address_sido(code)
);

drop table if exists tb_address_dong;
create table tb_address_dong(
	code varchar(30) primary key,
	name varchar(30) not null,
	sigoon_code varchar(30) not null,
	foreign key(sigoon_code) references tb_address_sigoon(code)
);

select * from tb_address_sido;
select * from tb_address_sigoon;
select * from tb_address_dong;

-----------------------------------------
-----------------------------------------
-----------------------------------------

drop table if exists tb_danggeun_type;
create table tb_danggeun_type(
	id Integer primary key,
	name varchar(30) not null
);

drop table if exists tb_danggeun_info;
create table tb_danggeun_info(
	id 			serial 		primary key,-- 상품아이디
	photo_address varchar(255) not null, -- 대표 사진 주소
	title		varchar(50) not null,	-- 상품게시물제목
	type_id		Integer		not null,	-- 상품타입
	type_name 	varchar(30) not null,	-- 상품타입이름
	sido_code	varchar(30) not null,	-- 판매위치
	sido_name 	varchar(30) not null,	-- 판매위치이름
	sigoon_code	varchar(30) not null,	-- 판매위치
	sigoon_name varchar(30) not null,	-- 판매위치이름
	dong_code	varchar(30) not null,	-- 판매위치
	dong_name 	varchar(30) not null,	-- 판매위치이름
	price		int			not null,	-- 상품가격
	content 	text 		not null,	-- 상품설명
	writer_nickname varchar(20) not null,   -- 판매자 닉네임
	view_cnt 	int 		default 0,	-- 조회수
	zzim_cnt	int			default 0,	-- 찜 수
	reg_date 	date 		default current_timestamp,
	up_date 	date 		default current_timestamp,
	foreign key(type_id) references tb_danggeun_type(id) on delete cascade on update cascade,
	foreign key(sido_code) references tb_address_sido(code) on delete cascade on update cascade,
	foreign key(sigoon_code) references tb_address_sigoon(code) on delete cascade on update cascade,
	foreign key(dong_code) references tb_address_dong(code) on delete cascade on update cascade,
	foreign key(writer_nickname) references tb_user(user_nickname) on delete cascade on update cascade
);

drop table if exists tb_danggeun_zzim;
create table tb_danggeun_zzim(
	user_nickname varchar(20),
	danggeun_id Integer,
	primary key(user_nickname, danggeun_id),
	foreign key(user_nickname) references tb_user(user_nickname) on delete cascade on update cascade,
	foreign key(danggeun_id) references tb_danggeun_info(id) on delete cascade on update cascade
);

drop table if exists tb_danggeun_photo;
create table tb_danggeun_photo(
	address varchar(255) primary key,
	danggeun_id Integer not null,
	sequence int not null,
	foreign key(danggeun_id) references tb_danggeun_info(id) on delete cascade on update cascade
);

select * from tb_danggeun_info;
select * from tb_danggeun_photo;
select * from tb_danggeun_type;
select * from tb_danggeun_zzim; 
select * from tb_user;

insert into tb_danggeun_type values(0, '전체');
insert into tb_danggeun_type values(1, '사료/간식');
insert into tb_danggeun_type values(2, '영양제');
insert into tb_danggeun_type values(3, '산책 용품');
insert into tb_danggeun_type values(4, '집/방석');
insert into tb_danggeun_type values(5, '옷/악세사리');
insert into tb_danggeun_type values(6, '위생 용품');
insert into tb_danggeun_type values(7, '기타 용품');

-----------------------------------------
-----------------------------------------
-----------------------------------------

drop table if exists t_chat_userchatroom;
create table t_chat_userchatroom(
	chatroom_id integer not null,
	login_nickname varchar(20) not null,
	other_nickname varchar(20) not null,
	primary key(chatroom_id, login_nickname),
	foreign key(chatroom_id) references tb_chat_chatroom(id) on delete cascade on update cascade,
	foreign key(login_nickname) references tb_user(user_nickname) on delete cascade on update cascade,
	foreign key(other_nickname) references tb_user(user_nickname) on delete cascade on update cascade
);

drop table if exists tb_chat_chatroom;
create table tb_chat_chatroom(
	id serial primary key,
	recent_nickname varchar(20) not null,
	recent_chat text not null,
	recent_date timestamp default current_timestamp,
	unread_cnt int default 1,
	foreign key(recent_nickname) references tb_user(user_nickname) on delete cascade on update cascade 
);

drop table if exists tb_chat_chatting;
create table tb_chat_chatting(
	id serial primary key,
	sender_nickname varchar(20) not null,
	chatroom_id integer not null,
	chat text not null,
	chat_date timestamp default current_timestamp,
	check_read boolean default false,
	foreign key(chatroom_id) references tb_chat_chatroom(id) on delete cascade on update cascade,
	foreign key(sender_nickname) references tb_user(user_nickname) on delete cascade on update cascade
);




















