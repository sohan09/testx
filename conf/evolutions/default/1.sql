# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table product (
  id                        bigint not null,
  name                      varchar(256),
  description               varchar(756),
  user_id                   bigint,
  created_date              timestamp,
  last_modified_date        timestamp,
  constraint pk_product primary key (id))
;

create table p_user (
  id                        bigint not null,
  first_name                varchar(50),
  last_name                 varchar(50),
  email                     varchar(100),
  user_id                   varchar(200),
  created_date              timestamp,
  last_modified_date        timestamp,
  constraint pk_p_user primary key (id))
;

create sequence product_seq;

create sequence p_user_seq;




# --- !Downs

drop table if exists product cascade;

drop table if exists p_user cascade;

drop sequence if exists product_seq;

drop sequence if exists p_user_seq;

