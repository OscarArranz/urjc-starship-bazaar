create table users(
    id integer primary key not null unique,
    name text not null,
    nickname text not null unique,
    email text not null unique,
    password text not null unique,
    origin_planet text,
    species text
);

create table spaceships(
    id integer primary key not null unique,
    owner_id integer not null,
    spaceship_type text not null,
    register_num text not null unique,
    crew_num int not null,

    foreign key(owner_id) references users(id) on delete cascade on update cascade
);

create table propellers(
    id integer primary key not null unique,
    spaceship_id integer not null,
    propeller_type text not null,
    max_speed_megameters_per_hour real not null,

    foreign key(spaceship_id) references spaceships(id) on delete cascade on update cascade
);

create table weapons(
    id integer primary key not null unique,
    spaceship_id integer not null,
    weapon_type text not null,
    potency_gigajoules real not null,

    foreign key(spaceship_id) references spaceships(id) on delete cascade on update cascade
);

create table shields(
    id integer primary key not null unique,
    spaceship_id integer not null,
    max_damage_gigajoules real not null,
    required_energy_gigacoulombs real not null,

    foreig key(spaceship_id) references spaceships(id) on delete cascade on update cascade
);


create table armors(
    id integer primary key not null unique,
    spaceship_id integer not null,
    material text not null,
    weigth_tons real not null,

    foreign key(spaceship_id) references spaceships(id) on delete cascade on update cascade
);

create table transactions(
    id integer primary key not null unique,
    vendor_id integer not null,
    buyer_id integer not null,
    price_cents integer not null,

    foreign key(vendor_id, buyer_id) references users(id, id) on delete no action on update cascade
);

create table transaction_spaceship(
    transaction_id integer not null, 
    spaceship_id integer not null,

    foreign key(transaction_id) references transactions(id) on delete cascade on update cascade,
    foreign key(spaceship_id) references spaceships(id) on delete cascade on update cascade
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
    spaceship_id integer not null,

    foreign key(offer_id) references offers(id) on delete cascade on update cascade,
    foreign key(spaceship_id) references spaceships(id) on delete cascade on update cascade
);

create table review(
    id integer primary key not null unique,
    comment text not null,
    score real not null,
    vendor_id integer not null,
    writer_id integer not null,

    foreign key(vendor_id, writer_id) references users(id, id) on delete cascade on update cascade
);