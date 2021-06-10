UPDATE yi_ip_reservations 
SET date_reservation = ?, reservation_state = ?,  reservation_closed = ?
WHERE reservation_id = ?;