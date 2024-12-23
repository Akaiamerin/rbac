INSERT INTO rbac.user (id, username, password, status, create_time, update_time) VALUES (1, 'user1', 'a', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.user (id, username, password, status, create_time, update_time) VALUES (2, 'user2', 'a', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.user (id, username, password, status, create_time, update_time) VALUES (3, 'user3', 'a', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');

INSERT INTO rbac.role (id, name, status, create_time, update_time) VALUES (1, '角色1', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role (id, name, status, create_time, update_time) VALUES (2, '角色2', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role (id, name, status, create_time, update_time) VALUES (3, '角色3', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role (id, name, status, create_time, update_time) VALUES (4, '角色4', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role (id, name, status, create_time, update_time) VALUES (5, '角色5', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role (id, name, status, create_time, update_time) VALUES (6, '角色6', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role (id, name, status, create_time, update_time) VALUES (7, '角色7', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role (id, name, status, create_time, update_time) VALUES (8, '角色8', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role (id, name, status, create_time, update_time) VALUES (9, '角色9', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role (id, name, status, create_time, update_time) VALUES (10, '角色10', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');

INSERT INTO rbac.permission (id, name, status, create_time, update_time) VALUES (1, '权限1', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.permission (id, name, status, create_time, update_time) VALUES (2, '权限2', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.permission (id, name, status, create_time, update_time) VALUES (3, '权限3', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.permission (id, name, status, create_time, update_time) VALUES (4, '权限4', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.permission (id, name, status, create_time, update_time) VALUES (5, '权限5', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.permission (id, name, status, create_time, update_time) VALUES (6, '权限6', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.permission (id, name, status, create_time, update_time) VALUES (7, '权限7', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.permission (id, name, status, create_time, update_time) VALUES (8, '权限8', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.permission (id, name, status, create_time, update_time) VALUES (9, '权限9', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.permission (id, name, status, create_time, update_time) VALUES (10, '权限10', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');

INSERT INTO rbac.user_role (id, user_id, role_id, status, create_time, update_time) VALUES (1, 1, 1, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.user_role (id, user_id, role_id, status, create_time, update_time) VALUES (2, 1, 2, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.user_role (id, user_id, role_id, status, create_time, update_time) VALUES (3, 1, 3, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.user_role (id, user_id, role_id, status, create_time, update_time) VALUES (4, 2, 4, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.user_role (id, user_id, role_id, status, create_time, update_time) VALUES (5, 2, 5, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.user_role (id, user_id, role_id, status, create_time, update_time) VALUES (6, 2, 6, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.user_role (id, user_id, role_id, status, create_time, update_time) VALUES (7, 3, 7, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.user_role (id, user_id, role_id, status, create_time, update_time) VALUES (8, 3, 8, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.user_role (id, user_id, role_id, status, create_time, update_time) VALUES (9, 3, 9, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.user_role (id, user_id, role_id, status, create_time, update_time) VALUES (10, 3, 10, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');

INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (1, 1, 1, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (2, 1, 2, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (3, 1, 3, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (4, 2, 4, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (5, 2, 5, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (6, 2, 6, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (7, 3, 7, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (8, 3, 8, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (9, 3, 9, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (10, 3, 10, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (11, 4, 1, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (12, 4, 2, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (13, 4, 3, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (14, 5, 4, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (15, 5, 5, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (16, 5, 6, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (17, 6, 7, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (18, 6, 8, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (19, 6, 9, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (20, 6, 10, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (21, 7, 1, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (22, 7, 2, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (23, 7, 3, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (24, 8, 4, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (25, 8, 5, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (26, 8, 6, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (27, 9, 7, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (28, 9, 8, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (29, 9, 9, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (30, 9, 10, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (31, 10, 1, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (32, 10, 2, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (33, 10, 3, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (34, 10, 4, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (35, 10, 5, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (36, 10, 6, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (37, 10, 7, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (38, 10, 8, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (39, 10, 9, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO rbac.role_permission (id, role_id, permission_id, status, create_time, update_time) VALUES (40, 10, 10, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');