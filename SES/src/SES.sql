create table Human (
	name varchar2(20) not null,
	age number(3) not null,
	jumin varchar2(20) primary key,
	type varchar2(20) not null
)
select * from HUMAN
select * from professor

create table professor(
	jumin varchar2(20) not null,
	major varchar2(30) not null,
	constraint professor_jumin_fk foreign key (jumin) references human(jumin) 
)

create table trainee(
	jumin varchar2(20) not null,
	stdNo varchar2(20) not null,
	constraint trainee_jumin_fk foreign key (jumin) references human(jumin)
)

create table staff(
	jumin varchar2(20) not null,
	field varchar2(20) not null,
	constraint staff_jumin_fk foreign key (jumin) references human(jumin)
)

drop table professor
drop table trainee
drop table staff
drop table human
select * from human
delete from human
select * from professor
commit