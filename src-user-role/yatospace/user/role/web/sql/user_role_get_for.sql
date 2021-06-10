SELECT `username`, `application`, `key`, `value`
FROM yi_users, yi_users_role
WHERE username = ? AND `user` = `id_user`;