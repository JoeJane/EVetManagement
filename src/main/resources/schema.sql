CREATE SCHEMA IF NOT EXISTS vmsdatabase;

use vmsdatabase;
create table if not exists address
(
    id          int auto_increment
    primary key,
    address_no  varchar(255) null,
    city        varchar(255) null,
    country     varchar(255) null,
    postal_code varchar(255) null,
    province    varchar(255) not null,
    street      varchar(255) null
    );

create table if not exists clinic
(
    id         int auto_increment
    primary key,
    created_at datetime     null,
    deleted    bit          null,
    name       varchar(255) null,
    updated_at datetime     null,
    address_id int          null,
    constraint UK_4oksleusu4wksqjpftwjqbj0m
    unique (name),
    constraint FK15djxm3mj8bmp7mv7gdm6m1du
    foreign key (address_id) references address (id)
    );

create table if not exists prescription
(
    id            int auto_increment
    primary key,
    created_at    datetime     null,
    prescriptions varchar(255) null
    );

create table if not exists users
(
    userid       int auto_increment
    primary key,
    created_at   datetime     null,
    dob          date         not null,
    deleted      bit          null,
    email        varchar(255) null,
    first_name   varchar(255) null,
    gender       varchar(255) null,
    last_name    varchar(255) null,
    password     varchar(255) null,
    phone_number varchar(255) null,
    role         varchar(255) not null,
    updated_at   datetime     null,
    username     varchar(255) null,
    address_id   int          null,
    clinic_id    int          null,
    constraint UK_r43af9ap4edm43mmtq01oddj6
    unique (username),
    constraint FK8hdqnq3fxk0buerbo6f2xpjem
    foreign key (clinic_id) references clinic (id),
    constraint FKditu6lr4ek16tkxtdsne0gxib
    foreign key (address_id) references address (id)
    );

create table if not exists lab_assistant
(
    userid int not null
    primary key,
    constraint FKiiovqx785tqbvi5gf5cie9d8n
    foreign key (userid) references users (userid)
    );

create table if not exists owner
(
    userid int not null
    primary key,
    constraint FKjkfdqeh7qlkc8331riboa35y5
    foreign key (userid) references users (userid)
    );

create table if not exists pet
(
    id         int auto_increment
    primary key,
    age        int          not null,
    breed      varchar(255) null,
    created_at datetime     null,
    deleted    bit          null,
    name       varchar(255) null,
    type       varchar(255) not null,
    updated_at datetime     null,
    weight     int          not null,
    owner_id   int          null,
    constraint FK7qfti9yba86tgfe9oobeqxfxg
    foreign key (owner_id) references owner (userid)
    );

create table if not exists receptionist
(
    userid int not null
    primary key,
    constraint FK6hdavkh3apa5b52hru2p8jjtn
    foreign key (userid) references users (userid)
    );

create table if not exists veterinarian
(
    speciality varchar(255) null,
    userid     int          not null
    primary key,
    constraint FKm24sfoao08st01kv6l11nfibb
    foreign key (userid) references users (userid)
    );

create table if not exists appointment
(
    id               int auto_increment
    primary key,
    appointment_date date         not null,
    appointment_time time         not null,
    booked_at        datetime     null,
    reason           varchar(255) null,
    status           varchar(255) null,
    pet_id           int          null,
    receptionist_id  int          null,
    veterinarian_id  int          null,
    constraint FK8y0it8yrd322ps2jklm5f8e07
    foreign key (pet_id) references pet (id),
    constraint FKncjuaf6od1tnu16glvyctox3h
    foreign key (veterinarian_id) references veterinarian (userid),
    constraint FKp21kdgv8pmv91os0mod1n08u2
    foreign key (receptionist_id) references receptionist (userid)
    );

create table if not exists diagnosis
(
    id              int auto_increment
    primary key,
    created_at      datetime     null,
    description     varchar(255) null,
    name            varchar(255) null,
    appointment_id  int          null,
    prescription_id int          null,
    constraint FKe86rgx4u8dh6ksjt3cno14qp4
    foreign key (appointment_id) references appointment (id),
    constraint FKqkmh3qbtjxunngaislpma8j4o
    foreign key (prescription_id) references prescription (id)
    );

create table if not exists report
(
    id               int auto_increment
    primary key,
    file             longblob     null,
    file_name        varchar(255) null,
    file_type        varchar(255) null,
    appointment_id   int          null,
    lab_assistant_id int          null,
    pet_id           int          null,
    constraint FKdcumpolsje2daom1mp4gqc2wm
    foreign key (pet_id) references pet (id),
    constraint FKebayyd9okoy9hyh326tk16svd
    foreign key (lab_assistant_id) references lab_assistant (userid),
    constraint FKorag1ww0f2a059d8w1rkwb8j2
    foreign key (appointment_id) references appointment (id)
    );


