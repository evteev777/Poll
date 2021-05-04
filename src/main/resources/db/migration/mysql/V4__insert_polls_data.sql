INSERT INTO polls (name, description, start_date, end_date)
VALUES ('Времена года', 'Опрос о временах года', '2021-05-01', '2021-05-11');

INSERT INTO questions (pool_id, questionType, name)
VALUES ('1', '0', 'Вам нравится весна?');

INSERT INTO answer_variants (question_id, text)
VALUES ('1', 'да');

INSERT INTO answer_variants (question_id, text)
VALUES ('1', 'нет');

INSERT INTO answer_variants (question_id, text)
VALUES ('1', 'не знаю');

INSERT INTO questions (pool_id, questionType, name)
VALUES ('1', '1', 'Какие времена года вам нравятся?');

INSERT INTO answer_variants (question_id, text)
VALUES ('2', 'весна');

INSERT INTO answer_variants (question_id, text)
VALUES ('2', 'лето');

INSERT INTO answer_variants (question_id, text)
VALUES ('2', 'осень');

INSERT INTO answer_variants (question_id, text)
VALUES ('2', 'зима');

INSERT INTO questions (pool_id, questionType, name)
VALUES ('1', '2', 'Расскажите о любимом времени года');

INSERT INTO polls (name, description, start_date, end_date)
VALUES ('Путешествия', 'Опрос о путешествиях', '2021-05-01', '2021-05-05');

INSERT INTO questions (pool_id, questionType, name)
VALUES ('2', '0', 'Планируете куда-нибудь поехать в ближайшее время?');

INSERT INTO answer_variants (question_id, text)
VALUES ('4', 'да');

INSERT INTO answer_variants (question_id, text)
VALUES ('4', 'нет');

INSERT INTO answer_variants (question_id, text)
VALUES ('4', 'не знаю');

INSERT INTO questions (pool_id, questionType, name)
VALUES ('2', '1', 'Как любите проводить время в путешествиях?');

INSERT INTO answer_variants (question_id, text)
VALUES ('5', 'Экскурсии и осмотр достопримечательностей');

INSERT INTO answer_variants (question_id, text)
VALUES ('5', 'Пляжный отдых');

INSERT INTO answer_variants (question_id, text)
VALUES ('5', 'Активный отдых');

INSERT INTO answer_variants (question_id, text)
VALUES ('5', 'Экстрим');

INSERT INTO questions (pool_id, questionType, name)
VALUES ('2', '2', 'Расскажите о лучшем путешествии в жизни');
