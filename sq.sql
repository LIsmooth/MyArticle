prompt PL/SQL Developer import file
prompt Created on Saturday, November 19, 2016 by yudi.liu
set feedback off
set define off
prompt Creating USERINFO...
create table USERINFO
(
  userid   NVARCHAR2(15) not null,
  userpwd  NVARCHAR2(8) not null,
  nickname NVARCHAR2(30) not null
)
;
alter table USERINFO
  add constraint PK_USERINFO primary key (USERID);

prompt Creating FAVORITES...
create table FAVORITES
(
  articleno NUMBER(10) not null,
  userid    NVARCHAR2(15) not null,
  spare     NVARCHAR2(10)
)
;
alter table FAVORITES
  add constraint FK_FAVORITE_ARTICLENO foreign key (ARTICLENO)
  references ARTICLES (ARTICLENO);
alter table FAVORITES
  add constraint FK_FAVORITE_USERID foreign key (USERID)
  references USERINFO (USERID);

prompt Creating TYPEINFO...
create table TYPEINFO
(
  typeno   NVARCHAR2(5) not null,
  typename NVARCHAR2(30) not null
)
;
alter table TYPEINFO
  add constraint PK_TYPE primary key (TYPENO);

prompt Disabling triggers for USERINFO...
alter table USERINFO disable all triggers;
prompt Disabling triggers for FAVORITES...
alter table FAVORITES disable all triggers;
prompt Disabling triggers for TYPEINFO...
alter table TYPEINFO disable all triggers;
prompt Disabling foreign key constraints for FAVORITES...
alter table FAVORITES disable constraint FK_FAVORITE_ARTICLENO;
alter table FAVORITES disable constraint FK_FAVORITE_USERID;
prompt Deleting TYPEINFO...
delete from TYPEINFO;
commit;
prompt Deleting FAVORITES...
delete from FAVORITES;
commit;
prompt Deleting USERINFO...
delete from USERINFO;
commit;
prompt Loading USERINFO...
insert into USERINFO (userid, userpwd, nickname)
values ('xiaoming', '123', '小鸣');
insert into USERINFO (userid, userpwd, nickname)
values ('yudi_liu', '123', 'Leif');
commit;
prompt 2 records loaded
prompt Loading FAVORITES...
insert into FAVORITES (articleno, userid, spare)
values (30, 'xiaoming', null);
insert into FAVORITES (articleno, userid, spare)
values (3, 'yudi_liu', null);
insert into FAVORITES (articleno, userid, spare)
values (5, 'yudi_liu', null);
insert into FAVORITES (articleno, userid, spare)
values (2, 'yudi_liu', null);
insert into FAVORITES (articleno, userid, spare)
values (30, 'yudi_liu', null);
insert into FAVORITES (articleno, userid, spare)
values (43, 'yudi_liu', null);
insert into FAVORITES (articleno, userid, spare)
values (49, 'yudi_liu', null);
commit;
prompt 7 records loaded
prompt Loading TYPEINFO...
insert into TYPEINFO (typeno, typename)
values ('bk', '百科');
insert into TYPEINFO (typeno, typename)
values ('wh', '文化');
insert into TYPEINFO (typeno, typename)
values ('zz', '政治');
insert into TYPEINFO (typeno, typename)
values ('jj', '经济');
insert into TYPEINFO (typeno, typename)
values ('kx', '科学');
commit;
prompt 5 records loaded
prompt Enabling foreign key constraints for FAVORITES...
alter table FAVORITES enable constraint FK_FAVORITE_ARTICLENO;
alter table FAVORITES enable constraint FK_FAVORITE_USERID;
prompt Enabling triggers for USERINFO...
alter table USERINFO enable all triggers;
prompt Enabling triggers for FAVORITES...
alter table FAVORITES enable all triggers;
prompt Enabling triggers for TYPEINFO...
alter table TYPEINFO enable all triggers;
set feedback on
set define on
prompt Done.
