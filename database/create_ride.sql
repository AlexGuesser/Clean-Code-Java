drop schema if exists ccca cascade;

create schema ccca;

create table ccca.ride (
	ride_id uuid primary key,
	passenger_id uuid,
	driver_id uuid,
	status text,
	fare float,
	distance float,
	from_lat float,
	from_long float,
	to_lat float,
	to_long float,
	created_at bigint,
	straight_distance float
);

create table ccca.position (
	position_id uuid primary key,
	ride_id uuid,
	lat float,
	long float,
	created_at bigint
);
