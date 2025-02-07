drop schema if exists ccca cascade;

create schema ccca;

create table ccca.ride (
	ride_id uuid primary key,
	passenger_id uuid,
	driver_id uuid,
	status text,
	fare numeric,
	distance numeric,
	from_lat numeric,
	from_long numeric,
	to_lat numeric,
	to_long numeric,
	created_at bigint
);

create table ccca.position (
	position_id uuid primary key,
	ride_id uuid,
	lat numeric,
	long numeric,
	created_at bigint
);
