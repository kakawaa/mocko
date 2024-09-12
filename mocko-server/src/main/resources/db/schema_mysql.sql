--
-- mocko数据库schema
--

--
-- 用户信息表
--
create table if not exists m_user
(
    id            int         not null default 0 auto_increment comment 'id',

    nick_name     varchar(32) not null default '' comment '昵称',
    username      varchar(32) not null default '' comment '用户名',
    password      varchar(32) not null default '' comment '密码',

    operator_code varchar(32) not null default 0 comment '操作人ID',
    deleted       tinyint     not null default 0 comment '删除标记',
    create_time   datetime    not null default now() comment '创建时间',
    update_time   timestamp   not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
);


--
-- 应用信息表
--
create table if not exists m_app
(
    id            int         not null default 0 auto_increment comment 'id',

    app_id        varchar(32) not null default '' comment '应用ID',
    app_name      varchar(32) not null default '' comment '应用名称',

    operator_code varchar(32) not null default 0 comment '操作人ID',
    deleted       tinyint     not null default 0 comment '删除标记',
    create_time   datetime    not null default now() comment '创建时间',
    update_time   timestamp   not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
);


--
-- 类信息表
--
create table if not exists m_type
(
    id            int           not null default 0 auto_increment comment 'id',

    app_id        varchar(32)   not null default '' comment '应用ID',

    type_id       varchar(32)   not null default '' comment '类ID',
    type_alias    varchar(32)   not null default '' comment '类别名',
    type_name     varchar(32)   not null default '' comment '类名',
    full_name     varchar(64)   not null default '' comment '全限定类名',
    remark        varchar(1024) not null default '' comment '备注',

    operator_code varchar(32)   not null default 0 comment '操作人ID',
    deleted       tinyint       not null default 0 comment '删除标记',
    create_time   datetime      not null default now() comment '创建时间',
    update_time   timestamp     not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id)
);


--
-- 方法信息表
--
create table if not exists m_method
(
    id            int          not null default 0 auto_increment comment 'id',

    app_id        varchar(32)  not null default '' comment '应用ID',
    type_id       varchar(32)  not null default '' comment '类ID',
    method_id     varchar(32)  not null default '' comment '方法ID',
    method_alias  varchar(32)  not null default '' comment '方法别名',
    method_name   varchar(64)  not null default '' comment '方法名称: 全限定类名#方法名',
    args_type     varchar(128) not null default '' comment '参数信息',
    response_type varchar(128) not null default '' comment '返回值类型',

    operator_code int          not null default 0 comment '操作人ID',
    deleted       int          not null default 0 comment '删除标记',
    create_time   datetime     not null default now() comment '创建时间',
    update_time   timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
);


--
-- 方法mock策略表
--
create table if not exists m_method_rule
(
    id                int          not null default 0 auto_increment comment 'id',

    method_id         varchar(32)  not null default '' comment '方法ID',
    rule_title        varchar(64)  not null default '' comment '标题',
    rule_exp          varchar(128) not null default '' comment '策略规则',
    response          tinytext     not null default '' comment 'mock的返回值',
    remark            mediumtext   not null default '' comment '备注',
    last_request_time datetime     not null default '1970-01-01 08:00:00' comment '上次请求时间',

    operator_code     int          not null default 0 comment '操作人ID',
    deleted           int          not null default 0 comment '删除标记',
    create_time       datetime     not null default now() comment '创建时间',
    update_time       timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)

)
