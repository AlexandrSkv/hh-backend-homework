create table if not exists employer (
  employer_id text primary key,
  employer_name text not null,
  data_create text not null,
  description text,
  area_id text not null,
  areaName text not null,
  comment text,
  views_count integer not null
);

create table if not exists vacancy (
  vacancy_id text primary key,
  vacancy_name text not null,
  data_create text not null,
  area_id text not null,
  areaName text not null,
  salary_from integer,
  salary_to integer,
  salary_currency text,
  salary_gross boolean,
  created_at text not null,
  employer_id text not null,
  employer_name text not null,
  comment text,
  views_count integer not null
);
