DELETE FROM ProblemProcedures;
DELETE FROM Problem;
SET IDENTITY_INSERT Problem ON;
INSERT INTO Problem (problem_id, title, contact, documentation, priority)
    VALUES  (1, 'Delete Problem', 'Delete Contact', NULL, 1);

INSERT INTO ProblemProcedures (problem_id, procedure_num, procedure_value)
    VALUES (1, 0, 'Delete Procedure 1');

INSERT INTO ProblemProcedures (problem_id, procedure_num, procedure_value)
    VALUES (1, 1, 'Delete Procedure 2');

INSERT INTO Problem (problem_id, title, contact, documentation, priority)
    VALUES  (2, 'Get Problem', 'Get Contact', 'Get Doc', 4);

INSERT INTO ProblemProcedures (problem_id, procedure_num, procedure_value)
    VALUES (2, 0, 'Get Procedure 1');

INSERT INTO ProblemProcedures (problem_id, procedure_num, procedure_value)
    VALUES (2, 1, 'Get Procedure 2');