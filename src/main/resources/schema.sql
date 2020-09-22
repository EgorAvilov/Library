create  database task;
use task;
create database if not exists task;
create table if not exists books
(
	id int auto_increment
		primary key,
	cover blob null,
	title varchar(255) not null,
	authors varchar(255) not null,
	publisher varchar(255) not null,
	publish_date date not null,
	genres varchar(255) not null,
	page_count int not null,
	isbn varchar(60) not null,
	description varchar(255) null,
	total_amount int null,
	status int null,
	available_amount int not null,
	deleted_status tinyint(1) default 0 not null,
	constraint book_info_isbn_uindex
		unique (isbn)
);

create table if not exists roles
(
	id int auto_increment
		primary key,
	role varchar(255) not null,
	constraint roles_role_uindex
		unique (role)
);

create table if not exists users
(
	id int auto_increment
		primary key,
	first_name varchar(255) not null,
	email varchar(255) not null,
	date_of_registration date not null,
	phone_number varchar(255) not null,
	last_name varchar(255) not null,
	gender tinyint(1) default 0 not null,
	deleted_status tinyint(1) default 0 null,
	username varchar(255) not null,
	password varchar(255) not null,
	role_id int default 2 not null,
	constraint readers_username_uindex
		unique (username),
	constraint users_roles_id_fk
		foreign key (role_id) references roles (id)
);

create table if not exists borrow_records
(
	id int auto_increment
		primary key,
	user_id int not null,
	borrow_date date not null,
	due_date date not null,
	return_date date null,
	book_id int not null,
	status_id int null,
	comment varchar(255) null,
	first_remind tinyint(1) default 0 not null,
	second_remind tinyint(1) default 0 not null,
	constraint borrow_records_book_info_id_fk
		foreign key (book_id) references books (id)
			on update cascade on delete cascade,
	constraint borrow_records_readers_id_fk
		foreign key (user_id) references users (id)
);

create index borrow_records_borrow_record_statuses_id_fk
	on borrow_records (status_id);

create index readers_genders_id_fk
	on users (gender);

