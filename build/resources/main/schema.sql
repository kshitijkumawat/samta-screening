drop table if exists question_modal;

create table question_modal(
    id int primary key ,
    answer varchar ,
    question varchar ,
    question_value int ,
    airdate varchar ,
    created_at varchar ,
    updated_at varchar ,
    category_id int ,
    game_id int ,
    invalid_count int
);