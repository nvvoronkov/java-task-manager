INSERT INTO users (email, first_name, last_name, password, position)
VALUES ('test1@mail.com', 'test', 'test', 'test', 'test'),
       ('test2@mail.com', 'test', 'test', 'test', 'test');
INSERT INTO tasks (description, priority, status, title, author_id)
VALUES ('test', 'test', 'test', 'test1', 1),
       ('test', 'test', 'test', 'test2', 2);
INSERT INTO tasks_executor (task_id, executor_id)
VALUES (1, 1),
       (1, 2),
       (2, 2);
INSERT INTO comments (comment, author_id)
VALUES ('test', 1),
       ('test', 2);
INSERT INTO tasks_comment (task_id, comment_id)
VALUES (1, 1),
       (2, 2)
