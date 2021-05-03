create table users(
    id integer primary key not null unique,
    name text not null,
    nickname text not null unique,
    email text not null unique,
    password text not null,
    origin_planet text,
    species text,
    spacial_license text unique
);

create table spaceships(
    id integer primary key not null unique,
    owner_id integer not null,
    spaceship_type text not null,
    register_num text not null unique,
    crew_num int not null,
    max_load_tons real,
    max_passengers int,

    foreign key(owner_id) references users(id) on delete cascade on update cascade
);

create table propellers(
    id integer primary key not null unique,
    spaceship_register_num text not null,
    propeller_type text not null,
    max_speed_kmh real not null,

    foreign key(spaceship_register_num) references spaceships(register_num) on delete cascade on update cascade
);

create table weapons(
    id integer primary key not null unique,
    spaceship_register_num text not null,
    weapon_type text not null,
    potency_gigajoules real not null,

    foreign key(spaceship_register_num) references spaceships(register_num) on delete cascade on update cascade
);

create table shields(
    id integer primary key not null unique,
    spaceship_register_num text not null,
    max_damage_gigajoules real not null,
    required_energy_gigacoulombs real not null,

    foreign key(spaceship_register_num) references spaceships(register_num) on delete cascade on update cascade
);


create table armors(
    id integer primary key not null unique,
    spaceship_register_num text not null,
    max_damage_gigajoules real not null,
    material text not null,
    weight_tons real not null,

    foreign key(spaceship_register_num) references spaceships(register_num) on delete cascade on update cascade
);

create table transactions(
    id integer primary key not null unique,
    vendor_id integer not null,
    buyer_id integer not null,
    price_cents integer not null,

    foreign key(vendor_id) references users(id) on delete no action on update cascade,
    foreign key(buyer_id) references users(id) on delete no action on update cascade
);

create table transaction_spaceship(
    transaction_id integer not null, 
    spaceship_register_num text not null,

    foreign key(transaction_id) references transactions(id) on delete cascade on update cascade,
    foreign key(spaceship_register_num) references spaceships(register_num) on delete cascade on update cascade
);

create table station_spaceship(
    station_register_num integer not null,
    spaceship_register_num text not null,

    foreign key(station_register_num) references spaceships(register_num) on delete cascade on update cascade,
    foreign key(spaceship_register_num) references spaceships(register_num) on delete cascade on update cascade
);

create table offers(
    id integer primary key not null unique,
    vendor_id integer not null,
    price_cents integer not null,
    deadline text not null,

    foreign key(vendor_id) references users(id) on delete cascade on update cascade
);

create table offer_spaceship(
    offer_id integer not null, 
    spaceship_register_num text not null,

    foreign key(offer_id) references offers(id) on delete cascade on update cascade,
    foreign key(spaceship_register_num) references spaceships(register_num) on delete cascade on update cascade
);

create table reviews(
    id integer primary key not null unique,
    comment text not null,
    score real not null,
    vendor_id integer not null,
    buyer_id integer not null,

    foreign key(vendor_id) references users(id) on delete cascade on update cascade,
    foreign key(buyer_id) references users(id) on delete cascade on update cascade
);