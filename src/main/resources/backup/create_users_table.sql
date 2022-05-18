-- Table: public.application_user

-- DROP TABLE IF EXISTS public.application_user;

CREATE TABLE IF NOT EXISTS public.users
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    hashed_password character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    profile_picture_url oid,
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT application_user_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.application_user
    OWNER to postgres;

GRANT ALL ON TABLE public.users TO postgres;
