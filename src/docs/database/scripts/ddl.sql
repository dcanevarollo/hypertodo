begin;

create table "user"
(
    id 				serial not null
							constraint user_pk
								primary key,
    name 			varchar(255) not null,
    email 			varchar(255) not null,
    password 	varchar(255) not null
);

create unique index user_email_uindex
    on "user" (email);

create table task
(
    id          			serial not null
								constraint task_pk
									primary key,
    user_id     		integer
								constraint task_user_fk
									references "user"
										on update cascade on delete cascade,
    title       			varchar(255) not null,
    description 	text,
    is_done     		boolean default false
);

create table role
(
    id   		integer not null
					constraint role_pk
						primary key,
    name 	varchar(255) not null
);

create table role_user
(
    role_id 	integer not null
						constraint role_user_role_id_fk
							references role
								on update cascade on delete cascade,
    user_id 	integer not null
						constraint role_user_user_id_fk
							references "user"
								on update cascade on delete cascade,
    constraint role_user_pk
        primary key (role_id, user_id)
);

commit;