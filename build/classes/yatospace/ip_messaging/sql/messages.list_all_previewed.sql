SELECT message_id,  message_value, vreme, vreme_promene, 
       username, observed, email
FROM  yi_users_messages_ip_aeroline_company
WHERE observed = TRUE
ORDER BY vreme ASC, message_id ASC;  