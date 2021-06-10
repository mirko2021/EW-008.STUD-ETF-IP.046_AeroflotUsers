SELECT  location_id, location_ref, username, 
        location_address, location_country, location_note
FROM yi_users_locations_ip_aerolines_company 
WHERE username = ?; 