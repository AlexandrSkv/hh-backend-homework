create table if not exists employer (
  employer_id Integer primary key,
  employer_name text not null,
  data_create timestamp not null,
  description text,
  area_id integer not null,
  areaName text not null,
  comment text,
  views_count integer not null
);

create table if not exists vacancy (
  vacancy_id integer primary key,
  vacancy_name text not null,
  data_create date not null,
  area_id integer not null,
  areaName text not null,
  salary_from integer,
  salary_to integer,
  salary_currency text,
  salary_gross boolean,
  created_at text not null,
  employer integer not null,
  comment text,
  views_count integer not null
);