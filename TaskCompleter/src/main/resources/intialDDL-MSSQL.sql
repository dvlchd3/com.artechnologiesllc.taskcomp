IF EXISTS(SELECT 1 FROM sys.objects WHERE OBJECT_ID = OBJECT_ID(N'Problem') AND type = (N'U'))
    DROP TABLE Problem;
CREATE TABLE Problem (
    problem_id      INTEGER         NOT NULL    IDENTITY,
    title           VARCHAR(45)     NOT NULL,
    contact         VARCHAR(25)     NOT NULL,
    documentation   VARCHAR(255),
    priority        TINYINT         NOT NULL,
    PRIMARY KEY (problem_id)
);

IF EXISTS(SELECT 1 FROM sys.objects WHERE OBJECT_ID = OBJECT_ID(N'ProblemProcedures') AND type = (N'U'))
    DROP TABLE ProblemProcedures;
CREATE TABLE ProblemProcedures (
    problem_id      INTEGER         NOT NULL,
    procedure_num   INTEGER         NOT NULL,
    procedure_value VARCHAR(255)    NOT NULL,
    PRIMARY KEY (problem_id, procedure_num)
);

IF EXISTS(SELECT 1 FROM sys.objects WHERE OBJECT_ID = OBJECT_ID(N'Task') AND type = (N'U'))
    DROP TABLE Task;
CREATE TABLE Task (
    task_id         INTEGER         NOT NULL    IDENTITY,
    title           VARCHAR(50)     NOT NULL,
    details         VARCHAR(150),
    scheduled_time  TIME,
    documentation   VARCHAR(255),
    input_type      TINYINT,
    problem_id      INTEGER,
    start_date      DATE,
    end_date        DATE,
    days            SMALLINT,
    weeks           TINYINT,
    months          SMALLINT,
    PRIMARY KEY(task_id)
);

IF EXISTS(SELECT 1 FROM sys.objects WHERE OBJECT_ID = OBJECT_ID(N'TaskGroup') AND type = (N'U'))
    DROP TABLE TaskGroup;
CREATE TABLE TaskGroup (
    group_name      VARCHAR(20)         NOT NULL,
    display_name    VARCHAR(20)         NOT NULL,
    PRIMARY KEY(group_name)
);

IF EXISTS(SELECT 1 FROM sys.objects WHERE OBJECT_ID = OBJECT_ID(N'Task_TaskGroup') AND type = (N'U'))
    DROP TABLE Task_TaskGroup;
CREATE TABLE Task_TaskGroup (
    task_id         INTEGER     NOT NULL,
    group_name      VARCHAR(20) NOT NULL,
    order_num       INTEGER     NOT NULL,
    PRIMARY KEY(task_id, group_name, order_num)
);

IF EXISTS(SELECT 1 FROM sys.objects WHERE OBJECT_ID = OBJECT_ID(N'CompleteInfo') AND type = (N'U'))
    DROP TABLE CompleteInfo;
CREATE TABLE CompleteInfo (
    task_id         INTEGER         NOT NULL,
    scheduled_date  DATE            NOT NULL,
    completed_time  DATETIME        NOT NULL,
    complete_info   VARCHAR(15)     NOT NULL,
    complete_user   VARCHAR(15)     NOT NULL,
    PRIMARY KEY(task_id, scheduled_date)
);

ALTER TABLE CompleteInfo
    ADD CONSTRAINT  FK_CompleteInfo_Task
        FOREIGN KEY (task_id)
        REFERENCES  Task (task_id);

ALTER TABLE ProblemProcedures
    ADD CONSTRAINT  FK_ProblemProcedures_Problem
        FOREIGN KEY (problem_id)
        REFERENCES  Problem (problem_id);

ALTER TABLE Task
    ADD CONSTRAINT  FK_Task_Problem
        FOREIGN KEY (problem_id)
        REFERENCES  Problem (problem_id);

ALTER TABLE Task_TaskGroup
    ADD CONSTRAINT  FK_Task_TaskGroup_Task
        FOREIGN KEY (task_id)
        REFERENCES  Task (task_id);

ALTER TABLE Task_TaskGroup
    ADD CONSTRAINT  FK_Task_TaskGroup_TaskGroup
        FOREIGN KEY (group_name)
        REFERENCES  TaskGroup (group_name);

ALTER TABLE Problem
    ADD CONSTRAINT  CHK_Problem_priority
        CHECK       (priority > 0 AND priority <= 5);

ALTER TABLE Task
    ADD CONSTRAINT  CHK_Task_days
        CHECK       (days >= 0 AND days <= 254);

ALTER TABLE Task
    ADD CONSTRAINT  CHK_Task_weeks
        CHECK       (weeks >= 0 AND weeks <= 62);

ALTER TABLE Task
    ADD CONSTRAINT  CHK_Task_months
        CHECK       (months >= 0 AND months <= 8190);