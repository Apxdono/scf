DROP SCHEMA security;
DROP SCHEMA test;

CREATE SCHEMA security;
CREATE SCHEMA test;

DROP TABLE security.security_action;

CREATE TABLE security.security_action
(
  id                 CHARACTER VARYING(36) NOT NULL,
  action             CHARACTER VARYING(255),
  create_date        TIMESTAMP WITHOUT TIME ZONE,
  deleted            BOOLEAN,
  display_name       CHARACTER VARYING(256),
  last_modified_date TIMESTAMP WITHOUT TIME ZONE,
  system_name        CHARACTER VARYING(256),
  target_class_name  CHARACTER VARYING(255),
  target_object_id   CHARACTER VARYING(255),
  version            BIGINT,
  CONSTRAINT security_action_pkey PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);

DROP TABLE security.security_descriptors;

CREATE TABLE security.security_descriptors
(
  id                 CHARACTER VARYING(36) NOT NULL,
  create_date        TIMESTAMP WITHOUT TIME ZONE,
  deleted            BOOLEAN,
  display_name       CHARACTER VARYING(256),
  last_modified_date TIMESTAMP WITHOUT TIME ZONE,
  priority           INTEGER,
  system_name        CHARACTER VARYING(256),
  version            BIGINT,
  CONSTRAINT security_descriptors_pkey PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);

DROP TABLE security.users;

CREATE TABLE security.users
(
  id                 CHARACTER VARYING(36)  NOT NULL,
  create_date        TIMESTAMP WITHOUT TIME ZONE,
  deleted            BOOLEAN,
  username           CHARACTER VARYING(255),
  last_modified_date TIMESTAMP WITHOUT TIME ZONE,
  password           CHARACTER VARYING(255),
  login              CHARACTER VARYING(255) NOT NULL,
  version            BIGINT,
  CONSTRAINT users_pkey PRIMARY KEY (id),
  CONSTRAINT users_login_key UNIQUE (login)
)
WITH (
OIDS =FALSE
);

DROP TABLE security.users_security_descriptors;

CREATE TABLE security.users_security_descriptors
(
  object_id              CHARACTER VARYING(36) NOT NULL,
  security_descriptor_id CHARACTER VARYING(36) NOT NULL,
  CONSTRAINT users_security_descriptors_pkey PRIMARY KEY (object_id, security_descriptor_id),
  CONSTRAINT fk_users_security_descriptors_object_id FOREIGN KEY (object_id)
  REFERENCES security.users (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_users_security_descriptors_security_descriptor_id FOREIGN KEY (security_descriptor_id)
  REFERENCES security.security_descriptors (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS =FALSE
);
