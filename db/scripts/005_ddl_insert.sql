INSERT INTO rule (name) VALUES ('Article. 1');
INSERT INTO rule (name) VALUES ('Article. 2');
INSERT INTO rule (name) VALUES ('Article. 3');

INSERT INTO type (name) VALUES ('Two vehicles');
INSERT INTO type (name) VALUES ('Vehicle and human');
INSERT INTO type (name) VALUES ('Vehicle and bike');

INSERT INTO accident (name, text, address, type_id)
VALUES ('accident 1', 'Running a red light or disobeying a sign', '09267 Anniversary Place', 1);
INSERT INTO accident (name, text, address, type_id)
VALUES ('accident 2', 'Driving under the influence', '48649 Norway Maple Alley', 2);
INSERT INTO accident (name, text, address, type_id)
VALUES ('accident 3', 'Texting while driving', '57945 Shoshone Park', 3);
INSERT INTO accident (name, text, address, type_id)
VALUES ('accident 4', 'Running a red light or disobeying a sign', '88 Mesta Point', 2);

INSERT INTO accident_rule (accident_id, rule_id) VALUES (1, 2);
INSERT INTO accident_rule (accident_id, rule_id) VALUES (2, 3);
INSERT INTO accident_rule (accident_id, rule_id) VALUES (3, 1);
INSERT INTO accident_rule (accident_id, rule_id) VALUES (4, 1);
INSERT INTO accident_rule (accident_id, rule_id) VALUES (4, 2);
INSERT INTO accident_rule (accident_id, rule_id) VALUES (4, 3);