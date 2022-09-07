create table if not exists users(
    id bigserial primary key,
    login text not null,
    password text not null
);

insert into users (login, password)
values ('qik', '$2a$10$FI5vDjgCO4xaA3YeIi8VJuFsIQEVCCRlgudUoVrB9ArPsNYT.ISJS'); -- encoded 'the-password'
