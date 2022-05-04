CREATE TABLE recruiter
(
  recruiter_id bigserial primary key
);

CREATE TYPE recommendation_form_status AS ENUM
(
    'OPEN',
    'PENDING',
    'CLOSED'
);

CREATE TABLE recommendation_form
(
  recommendation_form_id bigserial primary key,
  recruiter_id bigserial not null references recruiter(recruiter_id),
  creation_time timestamp default now(),
  update_time timestamp default now(),
  status recommendation_form_status not null default 'OPEN',
  candidate_name varchar (150) not null,
  recommender_email varchar (100) not null
);

CREATE TABLE recommendation_form_question
(
  recommendation_form_question_id bigserial primary key,
  recommendation_form_id bigserial not null references recommendation_form(recommendation_form_id),
  question text not null,
  answer text
);

CREATE INDEX recruiter_id_index ON recommendation_form (recruiter_id);
CREATE INDEX recommendation_form_id_index ON recommendation_form_question (recommendation_form_id);


