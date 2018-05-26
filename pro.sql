create procedure pro_readrecord
(      
       userid in NVARCHAR2,
       articleno in NUMBER,
       typeno in NVARCHAR2
)
as
begin
  if(select count(userid) from readrecord r where r.userid=userid and r.articleno=articleno)>0)then
            dbms_output.put_line(1);
 else
   dbms_output.put_line(0);
   end if;
   end;
