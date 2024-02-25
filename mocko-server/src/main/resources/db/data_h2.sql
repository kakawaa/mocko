--
-- mocko数据库schema
--


--
-- 用户信息
--
insert into m_user (username, password)
select 'admin', 'admin'
from dual
where not exists(select 1 from m_user where username = 'admin');