CREATE DATABASE IF NOT EXISTS `gateway`
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

use `gateway`;

create table if not exists `routes`
(
  `id`               bigint(20) auto_increment primary key comment '路由ID',
  `name`             varchar(64) default ''   not null comment '名称即routeId',
  `uri`              varchar(128)             not null comment '代理路径',

  `filters`          text comment '对应RouteDefinition类中的filters',

  `predicates`       text comment '对应RouteDefinition类中的predicates',

  `metadata`         text comment '对应RouteDefinition类中的metadata',

  `order`            int(10)                  not null default 0 comment '顺序',

  `deleted`          bit         default b'0' not null
    comment '是否删除',
  `created`          bigint(20)  default '0'  not null
    comment '记录创建时间',
  `last_modified`    bigint(20)  default '0'  not null
    comment '记录更新时间',
  `created_by`       varchar(64) default ''   not null
    comment '记录创建人',
  `last_modified_by` varchar(64) default ''   not null
    comment '记录更新人',
  key IDX_NAME (`name`)
)
  engine = innodb
  default charset = utf8mb4
  comment = '网关路由';
