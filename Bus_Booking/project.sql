create database Dat_Ve_Xe_Khach;
use Dat_Ve_Xe_Khach;
SET SQL_SAFE_UPDATES = 0;
create table Nhan_Vien(
	id_nv int PRIMARY KEY auto_increment,
    ten_nv varchar(50) not null,
	sdt_nv char(10) check(sdt_nv regexp '^0[0-9]{9}') not null unique,
    email_nv varchar(50) check(email_nv regexp '[[:alnum:]]+@[[:alnum:]]+\.[[:alnum:]]') not null unique,
	username_nv varchar(50) not null unique,
	password_nv varchar(50) not null
);
drop table Nhan_Vien;

create table Xe_Khach(
	id_xk int PRIMARY KEY auto_increment,
	ten_xk varchar(50) not null,
	bs_xk varchar(20) not null unique,
    cn_xk int check(cn_xk > 1) not null,
    giuong_xk int check(giuong_xk in(0,1)) not null,
    tivi_xk int check(tivi_xk in(0,1)) not null,
    wifi_xk int check(wifi_xk in(0,1)) not null
);
drop table Xe_Khach;

create table Khach_Hang(
	id_kh int PRIMARY KEY auto_increment,
    ten_kh varchar(50) not null,
    sdt_kh char(10) check(sdt_kh regexp '^0[0-9]{9}') not null,
    email_kh varchar(50) check(email_kh regexp '[[:alnum:]]+@[[:alnum:]]+\.[[:alnum:]]') not null,
    username_kh varchar(50) not null unique,
    password_kh varchar(50) not null
);
drop table Khach_Hang;

create table Tai_Xe(
	id_tx int PRIMARY KEY auto_increment,
    ten_tx varchar(50) not null,
    sdt_tx char(10) check(sdt_tx regexp '^0[0-9]{9}') not null unique,
    dchi_tx varchar(50) not null
);
drop table Tai_Xe;

create table Lich_Trinh(
	id_lt int PRIMARY KEY auto_increment,
    id_xk int, FOREIGN KEY(id_xk) references xe_khach(id_xk),
	id_tx int, FOREIGN KEY(id_tx) references tai_xe(id_tx),
	dcd_lt varchar(100) not null,
    dcc_lt varchar(100) not null,
    ngaykh_lt date not null,
    ngayd_lt date not null, constraint check (ngayd_lt >= ngaykh_lt),
    tgkh_lt time not null,
    tgd_lt time not null,
    gia_lt float check(gia_lt > 0) not null
);
drop table Lich_Trinh;

create table ChoNgoi_LichTrinh(
	id_cnlt int auto_increment,
    id_lt int, FOREIGN KEY(id_lt) references lich_trinh(id_lt),
    cn_lt int not null,
    PRIMARY KEY (id_cnlt , id_lt)
);
drop table ChoNgoi_LichTrinh;

delimiter $
create trigger capnhatcn_lt after insert on lich_trinh 
for each row
begin
	declare temp int;
    select xk.cn_xk into temp from xe_khach xk join lich_trinh lt on lt.id_xk = xk.id_xk where lt.id_lt = (select max(id_lt) from lich_trinh);
	insert into ChoNgoi_LichTrinh (id_lt, cn_lt) values (new.id_lt,temp);
end$
drop trigger capnhatcn_lt;

create table Dat_Ve(
	id_dv int PRIMARY KEY auto_increment,
	id_lt int, FOREIGN KEY(id_lt) references lich_trinh(id_lt),
	id_kh int, FOREIGN KEY(id_kh) references khach_hang(id_kh),
	cn_dv int check(cn_dv > 0) not null,
    ngay_dv date not null
);
drop table Dat_Ve;

delimiter $
create trigger capnhatcn_dv after insert on dat_ve 
for each row
begin
	update ChoNgoi_LichTrinh cnlt join dat_ve dv on cnlt.id_lt = dv.id_lt 
    set cnlt.cn_lt = cnlt.cn_lt - dv.cn_dv where dv.id_dv = (select max(id_dv) from dat_ve);
end$
drop trigger capnhatcn_dv;

create table Thanh_Toan(
	id_tt int PRIMARY KEY auto_increment,
	id_dv int, FOREIGN KEY(id_dv) references dat_ve(id_dv),
    giamgia_tt int check(giamgia_tt >= 0 and giamgia_tt <= 100) not null default 0,
    tht_tt float not null default 0
);
drop table Thanh_Toan;

insert into nhan_vien values(1,'Hồng Thị Hào Đỗ','0695305651','hdo@gmail.com','hongthihaodo','mysql');
insert into nhan_vien values(2,'Chi Xuân Văn','0395441754','xvan@gmail.com','chixuanvan','mysql');
insert into nhan_vien values(3,'Thi Cúc Văn','0755055646','cvan@gmail.com','thicucvan','mysql');
insert into nhan_vien values(4,'Đức Viện Đỗ','0809921095','vdo@gmail.com','ducviendo','mysql');
insert into nhan_vien values(5,'Hào Phượng Bùi','0917167151','bui@gmail.com','haophuongbui','mysql');

insert into xe_khach values(1,'Phương Trang','47B.021.99',29,1,1,1);
insert into xe_khach values(2,'Vũ Linh','3C-6996',29,1,0,1);
insert into xe_khach values(3,'Văn Lang','95B.001.04',16,1,1,0);
insert into xe_khach values(4,'Phương Trang','47B.022.98',34,1,1,1);
insert into xe_khach values(5,'Hùng Cường','66B.009.11',16,0,0,0);

insert into khach_hang values(1,'La Thanh Trọng','0901248021','trongb2014957@student.ctu.edu.vn','lathanhtrong','mysql');
insert into khach_hang values(2,'Lê Minh Mẫn','0939482924','manb2003791@student.ctu.edu.vn','leminhman','mysql');
insert into khach_hang values(3,'Hồ Minh Nhựt','0774825887','nhutb2005889@student.ctu.edu.vn','hominhnhut','mysql');
insert into khach_hang values(4,'Nguyễn Duy Khang','0766811976','khangb2005843@student.ctu.edu.vn','nguyenduykhang','mysql');
insert into khach_hang values(5,'Trần Gia Hưng','0968600062','hungb2014981@student.ctu.edu.vn','trangiahung','mysql');

insert into tai_xe values(1,'Trần Nguyệt Anh','0946158120','49 Lê Trung Nghĩa, thành phố Hồ Chí Minh');
insert into tai_xe values(2,'Trần Sơn Khánh','0719149325','60 Hàng Gai, thành phố Hà Nội');
insert into tai_xe values(3,'Phan Thị Châu','0973855131','49 Xuân Khánh, thành phố Cần Thơ');

insert into lich_trinh values(1,1,1,'Cần Thơ','Sài Gòn',STR_TO_DATE('10-10-2022','%d-%m-%Y'),STR_TO_DATE('13-10-2022','%d-%m-%Y'),'21:00:00','03:00:00',340000);
insert into lich_trinh values(2,2,2,'Cần Thơ','Hà Nội',STR_TO_DATE('11-10-2022','%d-%m-%Y'),STR_TO_DATE('16-10-2022','%d-%m-%Y'),'00:00:00','12:00:00',200000);
insert into lich_trinh values(3,3,3,'Sài Gòn','Hà Nội',STR_TO_DATE('12-10-2022','%d-%m-%Y'),STR_TO_DATE('15-10-2022','%d-%m-%Y'),'08:00:00','15:00:00',25000);
insert into lich_trinh values(4,4,1,'Hà Nội','Sài Gòn',STR_TO_DATE('13-10-2022','%d-%m-%Y'),STR_TO_DATE('16-10-2022','%d-%m-%Y'),'10:30:00','11:00:00',340000);
insert into lich_trinh values(5,5,2,'Hà Nội','Cần Thơ',STR_TO_DATE('14-10-2022','%d-%m-%Y'),STR_TO_DATE('19-10-2022','%d-%m-%Y'),'22:00:00','03:00:00',100000);

insert into dat_ve values(1,1,1,3,STR_TO_DATE('09-10-2022','%d-%m-%Y'));
insert into dat_ve values(2,2,2,5,STR_TO_DATE('09-04-2022','%d-%m-%Y'));
insert into dat_ve values(3,3,3,2,STR_TO_DATE('10-11-2022','%d-%m-%Y'));
insert into dat_ve values(4,4,4,3,STR_TO_DATE('11-12-2022','%d-%m-%Y'));
insert into dat_ve values(5,5,5,4,STR_TO_DATE('13-12-2022','%d-%m-%Y'));

delimiter $     
create procedure capnhat_tt(pid int)
begin
	declare temp float;
    select (dv.cn_dv * lt.gia_lt - (dv.cn_dv * lt.gia_lt)*tt.giamgia_tt/100) 
	from lich_trinh lt join dat_ve dv on lt.id_lt = dv.id_lt 
	join thanh_toan tt on tt.id_dv = dv.id_dv where id_tt = pid into temp;
    
	update thanh_toan set tht_tt = temp where id_tt = pid;
end$
drop procedure if exists capnhat_tt;

insert into thanh_toan (id_tt,id_dv,giamgia_tt) values(1,1,0);
call capnhat_tt(1);
insert into thanh_toan (id_tt,id_dv,giamgia_tt) values(2,2,0);
call capnhat_tt(2);
insert into thanh_toan (id_tt,id_dv,giamgia_tt) values(3,3,0);
call capnhat_tt(3);
insert into thanh_toan (id_tt,id_dv,giamgia_tt) values(4,4,10);
call capnhat_tt(4);
insert into thanh_toan (id_tt,id_dv,giamgia_tt) values(5,5,5);
call capnhat_tt(5);

select * from nhan_vien;
select * from xe_khach;
select * from khach_hang;
select * from tai_xe;
select lt.id_lt,id_xk,id_tx, cnlt.cn_lt, dcd_lt, dcc_lt, DATE_FORMAT(ngaykh_lt, '%d-%m-%Y') as ngaykh_lt,
	   DATE_FORMAT(ngayd_lt, '%d-%m-%Y') as ngayd_lt,
       tgkh_lt,tgd_lt, gia_lt from lich_trinh lt join chongoi_lichtrinh cnlt on lt.id_lt = cnlt.id_lt;
select id_dv,id_lt,id_kh,cn_dv, 
	   DATE_FORMAT(ngay_dv, '%d-%m-%Y') as ngay_dv from dat_ve;
select * from thanh_toan;
select * from chongoi_lichtrinh;

delimiter $
create procedure them_kh(pten varchar(50), psdt char(10), pemail varchar(50), pusername varchar(50), ppassword varchar(50))
begin
	insert into khach_hang(ten_kh, sdt_kh, email_kh, username_kh, password_kh) values (pten, psdt, pemail, pusername, ppassword);
end$
drop procedure if exists them_kh;

delimiter $
create procedure xoa_kh(pid int)
begin
	-- Delete Customer by deleting data from Payment and Booking in sequence
    delete from thanh_toan where id_dv in(select dv.id_dv from dat_ve dv join khach_hang kh on kh.id_kh = dv.id_kh where kh.id_kh = pid);
    delete from dat_ve where id_kh = pid;
    delete from khach_hang where id_kh = pid;
end$
drop procedure if exists xoa_kh;

delimiter $
create procedure xoa_tx(pid int)
begin
	-- Delete Driver by deleting data from Payment, Booking, Seat Schedule, Schedule in sequence
    delete from thanh_toan where id_dv in(select dv.id_dv from dat_ve dv join lich_trinh lt on lt.id_lt = dv.id_lt where lt.id_tx = pid);
    delete from dat_ve where id_lt in(select id_lt from lich_trinh where id_tx = pid);
    delete from chongoi_lichtrinh where id_lt in(select id_lt from lich_trinh where id_tx = pid);
    delete from lich_trinh where id_tx = pid;
	delete from tai_xe where id_tx = pid;
end$
drop procedure if exists xoa_tx;

delimiter $
create procedure xoa_xk(pid int)
begin
	-- Delete Bus by deleting data from Payment, Booking, Seat Schedule, Schedule in sequence
    delete from thanh_toan where id_dv in(select dv.id_dv from dat_ve dv join lich_trinh lt on lt.id_lt = dv.id_lt where lt.id_xk = pid);
    delete from dat_ve where id_lt in(select id_lt from lich_trinh where id_xk = pid);
    delete from chongoi_lichtrinh where id_lt in(select id_lt from lich_trinh where id_xk = pid);
    delete from lich_trinh where id_xk = pid;
	delete from xe_khach where id_xk = pid;
end$
drop procedure if exists xoa_xk;

delimiter $
create procedure xoa_lt(pid int)
begin
	-- Delete Schedule by deleting data from Payment, Booking, and Seat Schedule in sequence
    delete from thanh_toan where id_dv in (select id_dv from dat_ve where id_lt = pid);
    delete from dat_ve where id_lt = pid;
    delete from chongoi_lichtrinh where id_lt = pid;
	delete from lich_trinh where id_lt = pid;
end$
drop procedure if exists xoa_lt;

delimiter $
create procedure xoa_dv(pid int)
begin
	-- Delete Booking by deleting data from Payment
    delete from thanh_toan where id_dv = pid;
	delete from dat_ve where id_dv = pid;
end$
drop procedure if exists xoa_dv;

delimiter $
create procedure xoa_tt(pid int)
begin
	delete from thanh_toan where id_tt = pid;
end$
drop procedure if exists xoa_tt;

delimiter $
create procedure sua_mk(pusername varchar(50), ppassword varchar(50))
begin
	update khach_hang set password_kh = ppassword where username_kh = pusername;
end$
drop procedure if exists sua_mk;

select dv.id_dv, dv.ngay_dv, xk.ten_xk, dv.cn_dv, lt.dcd_lt, lt.dcc_lt, lt.ngaykh_lt, lt.ngayd_lt, lt.gia_lt, tt.giamgia_tt, tt.tht_tt
from lich_trinh lt join dat_ve dv on lt.id_lt = dv.id_lt
				   join xe_khach xk on xk.id_xk = lt.id_xk
                   join thanh_toan tt on tt.id_dv = dv.id_dv
where dv.id_kh = 1;

select month(dv.ngay_dv), sum(tt.tht_tt) from thanh_toan tt join dat_ve dv on tt.id_dv = dv.id_dv where year(dv.ngay_dv) = "2022" group by month(dv.ngay_dv);

SELECT lt.id_lt,xk.ten_xk,tx.ten_tx, cnlt.cn_lt, dcd_lt, dcc_lt, ngaykh_lt, ngayd_lt, tgkh_lt,tgd_lt, gia_lt 
FROM lich_trinh lt join chongoi_lichtrinh cnlt on lt.id_lt = cnlt.id_lt
				   join xe_khach xk on xk.id_xk = lt.id_xk
                   join tai_xe tx on tx.id_tx = lt.id_tx
order by lt.id_lt;

select cn_lt from chongoi_lichtrinh where id_lt = 3;

