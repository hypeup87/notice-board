create table if not exists notice_board
(
    nb_id          int auto_increment
    primary key,
    nb_title       varchar(50)                         null,
    nb_contents    varchar(200)                        null,
    nb_view_cnt    int       default 0                 not null,
    nb_start_date  datetime                            null,
    nb_end_date    datetime                            null,
    nb_ins_manager varchar(10)                         not null,
    nb_ins_date    datetime  default CURRENT_TIMESTAMP not null,
    nb_mod_date    datetime                            null,
    nb_is_del      binary(1) default 0x31              null
    );

create table if not exists notice_file
(
    nf_id        int auto_increment
    primary key,
    nb_id        int          not null,
    nf_file_name varchar(150) not null,
    nf_save_name varchar(200) not null,
    nf_ins_date  datetime     not null,
    constraint notice_file_notice_board_nb_id_fk
    foreign key (nb_id) references notice_board (nb_id)
    on delete cascade
    );


