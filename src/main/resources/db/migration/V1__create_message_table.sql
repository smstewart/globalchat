CREATE SEQUENCE message_seq;

CREATE TABLE message (
  id BIGINT PRIMARY KEY,
  author TEXT NOT NULL,
  content TEXT NOT NULL,
  submit_time BIGINT NOT NULL
);

CREATE INDEX message_submit_time_index ON message(submit_time);