-- schema owner
CREATE USER jpa_relations WITH password 'jpa_relations';

-- schema user
CREATE USER jpa_relations_ms WITH password 'jpa_relations_ms';

-- create schema
CREATE SCHEMA jpa_relations AUTHORIZATION jpa_relations;

GRANT USAGE ON SCHEMA jpa_relations TO jpa_relations_ms;

ALTER DEFAULT PRIVILEGES FOR USER jpa_relations IN SCHEMA jpa_relations GRANT SELECT, INSERT, UPDATE, DELETE, TRUNCATE ON TABLES TO jpa_relations_ms;
ALTER DEFAULT PRIVILEGES FOR USER jpa_relations IN SCHEMA jpa_relations GRANT USAGE ON SEQUENCES TO jpa_relations_ms;
ALTER DEFAULT PRIVILEGES FOR USER jpa_relations IN SCHEMA jpa_relations GRANT EXECUTE ON FUNCTIONS TO jpa_relations_ms;

alter user jpa_relations_ms set search_path = 'jpa_relations';