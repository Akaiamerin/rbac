CREATE SCHEMA IF NOT EXISTS rbac;

SET SCHEMA rbac;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS rbac_user;

CREATE TABLE rbac_user (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    username VARCHAR(20) NOT NULL COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码',
    status INT NOT NULL COMMENT '状态',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY (username)
) COMMENT '用户表';

TRUNCATE TABLE rbac_user;

DROP TABLE IF EXISTS rbac_role;

CREATE TABLE rbac_role (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    name VARCHAR(20) NOT NULL COMMENT '角色名',
    status INT NOT NULL COMMENT '状态',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY (name)
) COMMENT '角色表';

TRUNCATE TABLE rbac_role;

DROP TABLE IF EXISTS rbac_permission;

CREATE TABLE rbac_permission (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    name VARCHAR(20) NOT NULL COMMENT '权限名',
    status INT NOT NULL COMMENT '状态',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY (name)
) COMMENT '权限表';

TRUNCATE TABLE rbac_permission;

DROP TABLE IF EXISTS rbac_user_role;

CREATE TABLE rbac_user_role (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    user_id INT NOT NULL COMMENT '用户 id',
    role_id INT NOT NULL COMMENT '角色 id',
    status INT NOT NULL COMMENT '状态',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY (user_id, role_id)
) COMMENT '用户角色表';

TRUNCATE TABLE rbac_user_role;

DROP TABLE IF EXISTS rbac_role_permission;

CREATE TABLE rbac_role_permission (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    role_id INT NOT NULL COMMENT '角色 id',
    permission_id INT NOT NULL COMMENT '权限 id',
    status INT NOT NULL COMMENT '状态',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY (role_id, permission_id)
) COMMENT '角色权限表';

TRUNCATE TABLE rbac_role_permission;

SET FOREIGN_KEY_CHECKS = 1;