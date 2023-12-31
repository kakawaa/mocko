--
-- mocko数据库管理
--

--
-- 应用信息表
--
create table mocko.m_user
(
    id          int unsigned not null default 0 auto_increment comment 'id',

    nick_name   varchar(32)  not null default '' comment '昵称',
    username    varchar(32)  not null default '' comment '用户名',
    password    varchar(32)  not null default '' comment '密码',

    operator_id int          not null default 0 comment '操作人ID',
    deleted     int          not null default 0 comment '删除标记',
    create_time datetime     not null default now() comment '创建时间',
    update_time timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) engine = myisam
  character set = utf8mb4
  collate = utf8mb4_bin comment = '退款信息总览表';

--
-- 应用信息表
--
create table mocko.m_app
(
    id          int unsigned not null default 0 auto_increment comment 'id',

    app_id      varchar(32)  not null default '' comment '应用ID',
    app_name    varchar(32)  not null default '' comment '应用名称',

    operator_id int          not null default 0 comment '操作人ID',
    deleted     int          not null default 0 comment '删除标记',
    create_time datetime     not null default now() comment '创建时间',
    update_time timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) engine = myisam
  character set = utf8mb4
  collate = utf8mb4_bin comment = '退款信息总览表';


--
-- 组件信息表
--
create table mocko.m_component
(
    id          int unsigned not null default 0 auto_increment comment 'id',

    app_id      varchar(32)  not null default '' comment '应用ID',
    cmp_id      varchar(32)  not null default '' comment '组件ID',
    cmp_alias   varchar(32)  not null default '' comment '组件别名',
    class_name  varchar(64)  not null default '' comment '全限定类名',

    operator_id int          not null default 0 comment '操作人ID',
    deleted     int          not null default 0 comment '删除标记',
    create_time datetime     not null default now() comment '创建时间',
    update_time timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) engine = myisam
  character set = utf8mb4
  collate = utf8mb4_bin comment = '退款信息总览表';


