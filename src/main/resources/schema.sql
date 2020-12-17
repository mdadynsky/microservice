CREATE TABLE party (
  id          INTEGER PRIMARY KEY auto_increment,
  name        VARCHAR(255) NOT NULL,
  create_date TIMESTAMP,
  version     INTEGER);

CREATE TABLE item (
  id          INTEGER PRIMARY KEY auto_increment,
  owner_id    INTEGER NOT NULL,
  parent_id   INTEGER  NULL,
  serial      VARCHAR(255),
  TYPE        INTEGER,
  CHILDREN_COUNT INTEGER,
  create_date TIMESTAMP);