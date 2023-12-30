--
-- mocko数据库管理
--

-- 应用信息表

create table mocko.m_app
(
    id          int unsigned not null default 0 auto_increment comment 'id',

    app_id      varchar(32)  not null default '' comment '应用ID',
    app_name    varchar(32)  not null default '' comment '应用名称',

    deleted     int                   default 0,
    create_time datetime     not null default now() comment '创建时间',
    update_time timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) engine = myisam
  character set = utf8mb4
  collate = utf8mb4_bin comment = '退款信息总览表';


