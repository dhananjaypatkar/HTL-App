--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.5
-- Dumped by pg_dump version 9.4.5
-- Started on 2015-12-16 00:32:44
CREATE DATABASE htl_db
  WITH OWNER = htl_admin
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;
	   
SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 7 (class 2615 OID 16396)
-- Name: htl_app; Type: SCHEMA; Schema: -; Owner: htl_admin
--

CREATE SCHEMA htl_app;


ALTER SCHEMA htl_app OWNER TO htl_admin;

SET search_path = htl_app, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 174 (class 1259 OID 16405)
-- Name: htl_event; Type: TABLE; Schema: htl_app; Owner: htl_admin; Tablespace: 
--

CREATE TABLE htl_event (
    id bigserial NOT NULL,
    description text,
    start_date timestamp NOT NULL,
    end_date timestamp NOT NULL,
    timeline_id bigint NOT NULL
);


ALTER TABLE htl_event OWNER TO htl_admin;

--
-- TOC entry 2021 (class 0 OID 0)
-- Dependencies: 174
-- Name: TABLE htl_event; Type: COMMENT; Schema: htl_app; Owner: htl_admin
--

COMMENT ON TABLE htl_event IS 'Events on timeline';


--
-- TOC entry 2022 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN htl_event.timeline_id; Type: COMMENT; Schema: htl_app; Owner: htl_admin
--

COMMENT ON COLUMN htl_event.timeline_id IS 'References timeline';


--
-- TOC entry 175 (class 1259 OID 16413)
-- Name: htl_media; Type: TABLE; Schema: htl_app; Owner: htl_admin; Tablespace: 
--

CREATE TABLE htl_media (
    id bigserial NOT NULL,
    url text,
    event_id bigint NOT NULL
);


ALTER TABLE htl_media OWNER TO htl_admin;

--
-- TOC entry 2024 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN htl_media.id; Type: COMMENT; Schema: htl_app; Owner: htl_admin
--

COMMENT ON COLUMN htl_media.id IS 'Media identifier';


--
-- TOC entry 2025 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN htl_media.url; Type: COMMENT; Schema: htl_app; Owner: htl_admin
--

COMMENT ON COLUMN htl_media.url IS 'url link to the scanned media';


--
-- TOC entry 2026 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN htl_media.event_id; Type: COMMENT; Schema: htl_app; Owner: htl_admin
--

COMMENT ON COLUMN htl_media.event_id IS 'Reference to the event';


--
-- TOC entry 173 (class 1259 OID 16397)
-- Name: htl_timeline; Type: TABLE; Schema: htl_app; Owner: htl_admin; Tablespace: 
--

CREATE TABLE htl_timeline (
    id bigserial NOT NULL,
    description text,
    user_id bigint NOT NULL
);


ALTER TABLE htl_timeline OWNER TO htl_admin;

--
-- TOC entry 2028 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN htl_timeline.user_id; Type: COMMENT; Schema: htl_app; Owner: htl_admin
--

COMMENT ON COLUMN htl_timeline.user_id IS 'foreign key reference of to the user id';


--
-- TOC entry 176 (class 1259 OID 16432)
-- Name: htl_user; Type: TABLE; Schema: htl_app; Owner: htl_admin; Tablespace: 
--

CREATE TABLE htl_user (
    id bigserial NOT NULL,
    fullname text,
    email text NOT NULL,
    last_login_date timestamp NOT NULL,
    city text,
    region text
);


ALTER TABLE htl_user OWNER TO htl_admin;

--
-- TOC entry 2030 (class 0 OID 0)
-- Dependencies: 176
-- Name: TABLE htl_user; Type: COMMENT; Schema: htl_app; Owner: htl_admin
--

COMMENT ON TABLE htl_user IS 'User information ';


--
-- TOC entry 2031 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN htl_user.id; Type: COMMENT; Schema: htl_app; Owner: htl_admin
--

COMMENT ON COLUMN htl_user.id IS 'Unique id for this table';


--
-- TOC entry 2032 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN htl_user.city; Type: COMMENT; Schema: htl_app; Owner: htl_admin
--

COMMENT ON COLUMN htl_user.city IS 'city information';


--
-- TOC entry 2033 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN htl_user.region; Type: COMMENT; Schema: htl_app; Owner: htl_admin
--

COMMENT ON COLUMN htl_user.region IS 'state of india';


--
-- TOC entry 1899 (class 2606 OID 16409)
-- Name: htl_event_pkey; Type: CONSTRAINT; Schema: htl_app; Owner: htl_admin; Tablespace: 
--

ALTER TABLE ONLY htl_event
    ADD CONSTRAINT htl_event_pkey PRIMARY KEY (id);


--
-- TOC entry 1897 (class 2606 OID 16404)
-- Name: htl_timeline_pkey; Type: CONSTRAINT; Schema: htl_app; Owner: htl_admin; Tablespace: 
--

ALTER TABLE ONLY htl_timeline
    ADD CONSTRAINT htl_timeline_pkey PRIMARY KEY (id);


--
-- TOC entry 1903 (class 2606 OID 16439)
-- Name: pk_htl_user; Type: CONSTRAINT; Schema: htl_app; Owner: htl_admin; Tablespace: 
--

ALTER TABLE ONLY htl_user
    ADD CONSTRAINT pk_htl_user PRIMARY KEY (id);


--
-- TOC entry 1901 (class 2606 OID 16425)
-- Name: pk_media; Type: CONSTRAINT; Schema: htl_app; Owner: htl_admin; Tablespace: 
--

ALTER TABLE ONLY htl_media
    ADD CONSTRAINT pk_media PRIMARY KEY (id);


--
-- TOC entry 1906 (class 2606 OID 16426)
-- Name: fk_event_to_media; Type: FK CONSTRAINT; Schema: htl_app; Owner: htl_admin
--

ALTER TABLE ONLY htl_media
    ADD CONSTRAINT fk_event_to_media FOREIGN KEY (event_id) REFERENCES htl_event(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1905 (class 2606 OID 16416)
-- Name: fk_event_to_timeline; Type: FK CONSTRAINT; Schema: htl_app; Owner: htl_admin
--

ALTER TABLE ONLY htl_event
    ADD CONSTRAINT fk_event_to_timeline FOREIGN KEY (timeline_id) REFERENCES htl_timeline(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1904 (class 2606 OID 16440)
-- Name: fk_user_to_timeline; Type: FK CONSTRAINT; Schema: htl_app; Owner: htl_admin
--

ALTER TABLE ONLY htl_timeline
    ADD CONSTRAINT fk_user_to_timeline FOREIGN KEY (user_id) REFERENCES htl_user(id);


--
-- TOC entry 2020 (class 0 OID 0)
-- Dependencies: 7
-- Name: htl_app; Type: ACL; Schema: -; Owner: htl_admin
--

REVOKE ALL ON SCHEMA htl_app FROM PUBLIC;
REVOKE ALL ON SCHEMA htl_app FROM htl_admin;
GRANT ALL ON SCHEMA htl_app TO htl_admin;
GRANT ALL ON SCHEMA htl_app TO htl_user;


--
-- TOC entry 2023 (class 0 OID 0)
-- Dependencies: 174
-- Name: htl_event; Type: ACL; Schema: htl_app; Owner: htl_admin
--

REVOKE ALL ON TABLE htl_event FROM PUBLIC;
REVOKE ALL ON TABLE htl_event FROM htl_admin;
GRANT ALL ON TABLE htl_event TO htl_admin;
GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,UPDATE ON TABLE htl_event TO htl_user;


--
-- TOC entry 2027 (class 0 OID 0)
-- Dependencies: 175
-- Name: htl_media; Type: ACL; Schema: htl_app; Owner: htl_admin
--

REVOKE ALL ON TABLE htl_media FROM PUBLIC;
REVOKE ALL ON TABLE htl_media FROM htl_admin;
GRANT ALL ON TABLE htl_media TO htl_admin;
GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,UPDATE ON TABLE htl_media TO htl_user;


--
-- TOC entry 2029 (class 0 OID 0)
-- Dependencies: 173
-- Name: htl_timeline; Type: ACL; Schema: htl_app; Owner: htl_admin
--

REVOKE ALL ON TABLE htl_timeline FROM PUBLIC;
REVOKE ALL ON TABLE htl_timeline FROM htl_admin;
GRANT ALL ON TABLE htl_timeline TO htl_admin;
GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,UPDATE ON TABLE htl_timeline TO htl_user;

GRANT ALL ON SCHEMA htl_app to htl_app;

GRANT ALL ON TABLE htl_app.htl_event TO htl_app;
GRANT ALL ON TABLE htl_app.htl_media TO htl_app;
GRANT ALL ON TABLE htl_app.htl_user TO htl_app;
GRANT ALL ON TABLE htl_app.htl_timeline TO htl_app;

GRANT ALL ON SEQUENCE htl_app.htl_user_id_seq to htl_app;
GRANT ALL ON SEQUENCE htl_app.htl_media_id_seq to htl_app;
GRANT ALL ON SEQUENCE htl_app.htl_event_id_seq to htl_app;
GRANT ALL ON SEQUENCE htl_app.htl_timeline_id_seq to htl_app;

-- Completed on 2015-12-16 00:32:48

--
-- PostgreSQL database dump complete
--

