CREATE DATABASE IF NOT EXISTS rbac;

USE rbac;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    username VARCHAR(20) NOT NULL COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码',
    PRIMARY KEY (id),
    UNIQUE KEY (username)
) COMMENT '用户表';

TRUNCATE TABLE user;

DROP TABLE IF EXISTS role;

CREATE TABLE role (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    name VARCHAR(20) NOT NULL COMMENT '角色名',
    PRIMARY KEY (id),
    UNIQUE KEY (name)
) COMMENT '角色表';

TRUNCATE TABLE role;

DROP TABLE IF EXISTS permission;

CREATE TABLE permission (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    name VARCHAR(20) NOT NULL COMMENT '权限名',
    PRIMARY KEY (id),
    UNIQUE KEY (name)
) COMMENT '权限表';

TRUNCATE TABLE permission;

DROP TABLE IF EXISTS user_role;

CREATE TABLE user_role (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    user_id INT NOT NULL COMMENT '用户 id',
    role_id INT NOT NULL COMMENT '角色 id',
    PRIMARY KEY (id)
) COMMENT '用户角色表';

TRUNCATE TABLE user_role;

DROP TABLE IF EXISTS role_permission;

CREATE TABLE role_permission (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    role_id INT NOT NULL COMMENT '角色 id',
    permission_id INT NOT NULL COMMENT '权限 id',
    PRIMARY KEY (id)
) COMMENT '角色权限表';

TRUNCATE TABLE role_permission;

SET FOREIGN_KEY_CHECKS = 1;