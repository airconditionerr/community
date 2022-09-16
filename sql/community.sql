drop table if exists user;
create table user
(
    id           bigint unsigned auto_increment comment '自增主键' primary key,
    account_id   varchar(100) null comment '对应Github Id',
    name         varchar(50) null comment '用户昵称',
    token        char(36) null,
    gmt_create   timestamp null comment '创建时间',
    gmt_modified timestamp null comment '修改时间',
    bio          varchar(256) null comment '个人简介',
    avatar_url   varchar(256) null comment '头像url（对应Github头像）',
    type         varchar(10) default 'github' null,
    disable      int unsigned default 0 null
);

drop table if exists question;
create table question
(
    id            bigint unsigned auto_increment comment '自增主键' primary key,
    title         varchar(50) null comment '问题名称',
    description   text null comment '问题描述',
    gmt_create    timestamp null comment '创建时间',
    gmt_modified  timestamp null comment '修改时间',
    creator       bigint unsigned not null comment '提问者id',
    comment_count int default 0 null comment '当前问题评论数',
    view_count    int default 0 null comment '当前问题浏览数',
    follow_count  int default 0 null,
    tag           varchar(256) null comment '问题标签',
    sticky        int(1) default 0 null
);

drop table if exists comment;
create table comment
(
    id            bigint unsigned auto_increment comment '自增主键' primary key,
    parent_id     bigint unsigned not null comment '评论的问题或评论的id',
    type          tinyint(1) unsigned not null comment '回复类型：1-评论问题，2-评论评论',
    commentator   bigint unsigned not null comment '评论者id',
    gmt_create    timestamp default CURRENT_TIMESTAMP     not null comment '创建时间',
    gmt_modified  timestamp default '0000-00-00 00:00:00' not null comment '修改时间',
    like_count    int(20) default 0 null,
    content       varchar(1024) null comment '评论内容',
    comment_count int       default 0 null comment '评论数'
);

drop table if exists notification;
create table notification
(
    id            bigint unsigned auto_increment comment '自增主键' primary key,
    notifier      bigint                              not null comment '通知的人的id',
    receiver      bigint                              not null comment '接收消息的人id',
    outer_id      int(20) not null comment '评论的 问题或评论 的id',
    type          tinyint(1) not null comment '回复类型：1-评论问题，2-评论评论',
    gmt_create    timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
    is_read       tinyint(1) default 0 not null comment '是否已读：0-未读，1-已读',
    notifier_name varchar(100) null comment '通知的人的 昵称',
    outer_title   varchar(256) null comment '评论的 问题或评论 的title'
);



