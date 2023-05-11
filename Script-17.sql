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
	price		int			not null,	-- 상품가격
	content 	text 		not null,	-- 상품설명
	writer 		varchar(50) not null,	-- 판매자
	view_cnt 	int 		default 0,	-- 조회수
	add_cnt		int			default 0,	-- 찜 수
	reg_date 	date 		default current_timestamp,
	up_date 	date 		default current_timestamp,
	foreign key (type_id) references danggeun_type(id),
	foreign key (writer) references dang_member(email)
);

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

------------------------------------------------------------------------------------

select *
from t_board;

select *
from t_user;

insert into t_user(id, pwd, name, email, birth, sns, reg_date)
values('earth', '06290629', 'lee', 'gg', '2010-03-04', 'youtube', now());


drop table if exists uuser;
create table uuser(
	id varchar(10) primary key,
	name varchar(10) not null
);

drop table if exists ppruduct;
create table ppruduct(
	id varchar(10) primary key,
	name varchar(10) not null,
	zzim_cnt int default 0
);

drop table if exists zzim;
create table zzim(
	user_id varchar(10),
	product_id varchar(10),
	primary key(user_id, product_id),
	foreign key(user_id) references uuser(id),
	foreign key(product_id) references ppruduct(id)
);

insert into uuser values('1', 'earth1');
insert into uuser values('2', 'earth2');
insert into uuser values('3', 'earth3');
insert into uuser values('4', 'earth4');
insert into uuser values('5', 'earth5');

insert into ppruduct values('1', 'sun1');
insert into ppruduct values('2', 'sun2');
insert into ppruduct values('3', 'sun3');
insert into ppruduct values('4', 'sun4');
insert into ppruduct values('5', 'sun5');

insert into zzim values('1', '1');
insert into zzim values('1', '2');
insert into zzim values('1', '3');
insert into zzim values('2', '5');
insert into zzim values('3', '4');
insert into zzim values('3', '5');
insert into zzim values('4', '1');
insert into zzim values('5', '1');
insert into zzim values('5', '2');

select *
from uuser;

select *
from ppruduct
order by id;

select *
from zzim;


update ppruduct
set zzim_cnt = zzim_cnt + (
	select count(*)
	from zzim
	where product_id = '1')
where id = '1';


-- 검색
-- (%, _)

select *
from t_board
order by reg_date desc, bno desc;

select *
from t_board
where title like concat('Pioneering12', '%') or writer like concat('earth1', '%');




SELECT bno, title, content, writer, view_cnt, comment_cnt, reg_date FROM t_board where true 
AND (title LIKE concat('%', 'T', '%') OR content LIKE concat('%', 'T', '%')) order by reg_date 
desc, bno desc limit 10 offset 0;

--
--
--
--
--
--
--

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
	id serial primary key,
	name varchar(50) not null
);

drop table if exists location;
create table location(
	id serial primary key,
	name varchar(50) not null
);

drop table if exists danggeun;
create table danggeun(
	id 			serial 		primary key,-- 상품아이디
	title		varchar(50) not null,	-- 상품게시물제목
	name 		varchar(50) not null,	-- 상품이름
	type_id		Integer		not null,	-- 상품타입
	local_id	Integer		not null,	-- 상품판매지역
	price		int			not null,	-- 상품가격
	content 	text 		not null,	-- 상품설명
	writer 		varchar(50) not null,	-- 판매자
	view_cnt 	int 		default 0,	-- 조회수
	add_cnt		int			default 0,	-- 찜 수
	reg_date 	date 		default current_timestamp,
	up_date 	date 		default current_timestamp,
	foreign key (type_id) references danggeun_type(id),
	foreign key (writer) references dang_member(email),
	foreign key (local_id) references location(id)
);

select *
from dang_board;

select *
from dang_member;

select *
from danggeun;

select *
from danggeun_type;

select *
from location;

insert into dang_board(title, writer, content)
values('title', 'writer', 'abcdefg');

insert into dang_member 
values('gojhkim123@naver.com', '12345', 'kim');

insert into danggeun_type(name) values('사료/간식');
insert into danggeun_type(name) values('영양제');
insert into danggeun_type(name) values('산책 용품');
insert into danggeun_type(name) values('집/방석');
insert into danggeun_type(name) values('옷/악세사리');
insert into danggeun_type(name) values('위생 용품');
insert into danggeun_type(name) values('기타 용품');

insert into location(name) values('경기도 수원시 영통구 영통동');

select a.name, b.name
from danggeun a, location b
where a.id = 1 and a.local_id = b.id;


UPDATE danggeun
SET local_name = (	
					SELECT b.name
					FROM danggeun a, location b
					WHERE a.id = 1 and a.local_id = b.id
)
WHERE id = 1;

-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
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

drop table if exists location_si;
create table location_si (
	id int primary key,
	name varchar(50) not null
);

drop table if exists location_gungu;
create table location_gungu (
	id int primary key,
	si_id int not null,
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
	danggeun_id Integer
);

select *
from danggeun;

SELECT id, title, name, type_id, local_id, price, content, writer_email, view_cnt, zzim_cnt, reg_date, up_date
FROM danggeun;

select *
from dang_member;

select *
from zzim_danggeun;

select *
from danggeun_type;

select *
from location_si;

select *
from location_gungu;

select *
from danggeun_type;

select id, name
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


SELECT id, title, name, type_id, local_id, price, content, writer_email, view_cnt, zzim_cnt, reg_date, up_date
FROM public.danggeun;




















-- ***************************
-- ************중요************
-- ***************************
--페이지 들어가면 세션에 저장된 이메일로 찜 테이블에서 찜한 상품 검색함
--검색한 상품의 찜 버튼이 빨강색이 되도록 함
--
--찜 버튼 클릭하면 빨간색이 됨
--찜 테이블에 세션에 저장된 이메일과 찜버튼을 누른 상품 아이디를 이용해 저장함 

insert into store_danggeun values('gojhkim123@naver.com', '1');
insert into store_danggeun values('gojhkim123@naver.com', '3');


select b.*
from store_danggeun a, danggeun b
where a.member_email = 'gojhkim123@naver.com' and a.danggeun_id = b.id;

truncate t_board restart identity;
truncate t_comment restart identity; 

insert into t_comment(bno, pcno, comment, commenter) 
values(1, 1, 'earth', 'earth1234');
insert into t_comment(bno, pcno, comment, commenter) 
values(1, 1, 'earth', 'earth1234');
insert into t_comment(bno, pcno, comment, commenter) 
values(1, 1, 'earth', 'earth1234');
insert into t_comment(bno, pcno, comment, commenter) 
values(1, 1, 'earth', 'earth1234');
insert into t_comment(bno, pcno, comment, commenter) 
values(1, 1, 'earth', 'earth1234');
insert into t_comment(bno, pcno, comment, commenter) 
values(1, 1, 'earth', 'earth1234');
insert into t_comment(bno, pcno, comment, commenter) 
values(1, 1, 'earth', 'earth1234');
insert into t_comment(bno, pcno, comment, commenter) 
values(1, 1, 'earth', 'earth1234');
insert into t_comment(bno, pcno, comment, commenter) s
values(1, 1, 'earth', 'earth1234');
insert into t_comment(bno, pcno, comment, commenter) 
values(1, 1, 'earth', 'earth1234');

select *
from t_board;

select *
from t_comment;

select *
from t_comment
where bno = 1;

DELETE FROM t_comment WHERE bno = 1;

DROP TABLE if exists t_comment;
CREATE TABLE public.t_comment (
	cno serial4 NOT NULL,
	bno int4 NOT NULL,
	pcno int4 NULL,
	"comment" varchar(3000) NULL,
	commenter varchar(30) NULL,
	reg_date date NULL DEFAULT now(),
	up_date date NULL DEFAULT now(),
	CONSTRAINT t_comment_pkey PRIMARY KEY (cno)
);


truncate t_comment restart identity; 
truncate t_board restart identity; 

select *
from t_board;

select *
from t_comment;




-- 찜 테이블에 가서 로그인 한 유저가 찜한 상품 아이디 목록을 가져옴
select danggeun_id
from store_danggeun
where member_email = 'gojhkim123@naver.com';

select *
from danggeun;

-- 모든 상품을 가져올 때
-- for 문 돌려






