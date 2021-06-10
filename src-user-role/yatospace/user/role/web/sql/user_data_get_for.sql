SELECT first_name, second_name, user_notes
FROM yi_users, yi_users_info
WHERE username = ? AND user_data = yi_user_data;