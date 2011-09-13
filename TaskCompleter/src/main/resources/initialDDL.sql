DROP TABLE IF EXISTS Problem;
CREATE TABLE Problem (
    problem_id      BIGINT UNSIGNED     NOT NULL    AUTO_INCREMENT,
    title           VARCHAR(45)         NOT NULL,
    contact         VARCHAR(25)         NOT NULL,
    documentation   VARCHAR(255),
    priority        TINYINT UNSIGNED    NOT NULL,
    PRIMARY KEY (problem_id)
);

DROP TABLE IF EXISTS ProblemProcedures;
CREATE TABLE ProblemProcedures (
    problem_id      BIGINT UNSIGNED     NOT NULL,
    procedure_num   INTEGER UNSIGNED    NOT NULL,
    procedure_value VARCHAR(255)        NOT NULL,
    PRIMARY KEY (problem_id, procedure_num)
);

DROP TABLE IF EXISTS Task;
CREATE TABLE Task (
    task_id         BIGINT UNSIGNED     NOT NULL    AUTO_INCREMENT,
    title           VARCHAR(50)         NOT NULL,
    details         VARCHAR(150),
    scheduled_time  TIME,
    documentation   VARCHAR(255),
    input_type      TINYINT UNSIGNED,
    problem_id      BIGINT UNSIGNED,
    start_date      DATE,
    end_date        DATE,
    days            SMALLINT UNSIGNED,
    weeks           TINYINT UNSIGNED,
    months          SMALLINT UNSIGNED,
    PRIMARY KEY(task_id)
);

DROP TABLE IF EXISTS TaskGroup;
CREATE TABLE TaskGroup (
    group_name      VARCHAR(20)         NOT NULL,
    display_name    VARCHAR(20)         NOT NULL,
    PRIMARY KEY(group_name)
);

DROP TABLE IF EXISTS Task_TaskGroup;
CREATE TABLE Task_TaskGroup (
    task_id         BIGINT UNSIGNED     NOT NULL,
    group_name      VARCHAR(20)         NOT NULL,
    order_num       INTEGER UNSIGNED    NOT NULL,
    PRIMARY KEY(task_id, group_name, order_num)
);

DROP TABLE IF EXISTS CompleteInfo;
CREATE TABLE CompleteInfo (
    task_id         BIGINT UNSIGNED     NOT NULL,
    scheduled_date  DATE                NOT NULL,
    completed_time  DATETIME            NOT NULL,
    complete_info   VARCHAR(15)         NOT NULL,
    complete_user   VARCHAR(15)         NOT NULL,
    PRIMARY KEY(task_id, scheduled_date)
);

ALTER TABLE CompleteInfo
    ADD INDEX       FK_CompleteInfo_Task    (task_id),
    ADD CONSTRAINT  FK_CompleteInfo_Task
        FOREIGN KEY (task_id)
        REFERENCES  Task (task_id);

ALTER TABLE ProblemProcedures
    ADD INDEX       FK_ProblemProcedures_Problem    (problem_id),
    ADD CONSTRAINT  FK_ProblemProcedures_Problem
        FOREIGN KEY (problem_id)
        REFERENCES  Problem (problem_id);

ALTER TABLE Task
    ADD INDEX       FK_Task_Problem (problem_id),
    ADD CONSTRAINT  FK_Task_Problem
        FOREIGN KEY (problem_id)
        REFERENCES  Problem (problem_id);

ALTER TABLE Task_TaskGroup
    ADD INDEX       FK_Task_TaskGroup_Task  (task_id),
    ADD CONSTRAINT  FK_Task_TaskGroup_Task
        FOREIGN KEY (task_id)
        REFERENCES  Task (task_id);

ALTER TABLE Task_TaskGroup
    ADD INDEX       FK_Task_TaskGroup_TaskGroup (group_name),
    ADD CONSTRAINT  FK_Task_TaskGroup_TaskGroup
        FOREIGN KEY (group_name)
        REFERENCES  TaskGroup (group_name);

ALTER TABLE Problem
    ADD CONSTRAINT  CHK_Problem_priority
        CHECK       (priority <= 5);

ALTER TABLE Task
    ADD CONSTRAINT  CHK_Task_days
        CHECK       (days <= 254);

ALTER TABLE Task
    ADD CONSTRAINT  CHK_Task_weeks
        CHECK       (weeks <= 62);

ALTER TABLE Task
    ADD CONSTRAINT  CHK_Task_months
        CHECK       (months <= 8190);