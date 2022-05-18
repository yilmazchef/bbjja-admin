CREATE DATABASE bbjjadb
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

GRANT ALL ON TABLE public.user_roles TO postgres;
