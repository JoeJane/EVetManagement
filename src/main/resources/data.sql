insert into vmsdatabase.address(id, street, address_no, city, province, postal_code, country)
values
(1, '', '', '', '', '', '');

insert into vmsdatabase.clinic (id, created_at, deleted, name, updated_at, address_id)
values
(1, now(), false, 'Admin', now(), 1);

INSERT INTO vmsdatabase.users (userid, email, username, password, first_name, last_name,  gender, phone_number, created_at, updated_at, dob, deleted, role, address_id, clinic_id)
values
(1, 'admin@admin.com', 'admin', '$2a$10$bWCIFlbNWUT1GpOUn8TM6.MFseh9l56d9js4JeUBnRL5WIjb.UToG', 'Aarthy', 'Joseph', 'Female', '1234567890', now(), now(), '1991-03-12', 0, 'ADMIN', 1, 1);
