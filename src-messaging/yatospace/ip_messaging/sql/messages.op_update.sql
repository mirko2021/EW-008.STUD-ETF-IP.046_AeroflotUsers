UPDATE yi_users_messages_ip_aeroline_company 
       SET message_id = ?,
           message_value = ?, 
           vreme_promene = ?, 
           username = ?, 
           email = ?
WHERE message_id = ?; 