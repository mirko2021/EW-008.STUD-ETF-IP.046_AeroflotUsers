USE yatospace_db; 

ALTER TABLE yi_ip_reservations ADD COLUMN date_reservation TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE yi_ip_reservations ADD COLUMN reservation_state SET('NEW', 'ACCEPT', 'FORBIDEN') DEFAULT 'NEW';
ALTER TABLE yi_ip_reservations ADD COLUMN reservation_closed SET('YES_CANCELED', 'NO_CANCELED') DEFAULT 'NO_CANCELED';