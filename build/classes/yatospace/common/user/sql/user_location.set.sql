UPDATE yi_users_locations_ip_aerolines_company SET
	location_id = ?, 
	location_ref = ?, 
	username = ?, 
	location_address = ?,
	location_country = ?,
	location_note = ?
WHERE username = ?;