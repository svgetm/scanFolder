insert into SCANFOLDER_SETTINGS
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, NAME, FOLEDER_PATH, WORK_, LAST_TIME_UPDATE)
values ('ae8da5ca-1578-d70f-f407-b8bda0f02471', 1, '2020-03-11 10:46:40', 'admin', '2021-02-11 09:27:47', 'admin', null, null, 'pzs/pzsi', '~', false, '2020-03-11 00:00:00');

insert into SCANFOLDER_EXPANSION_SETTINGS
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, EXPANSION)
values ('24a52302-87df-9f4c-3a71-87dbc90752e1', 1, '2020-10-16 09:26:16', 'admin', '2020-10-16 09:32:30', 'admin', null, null, 'xml');

insert into SCANFOLDER_SETTINGS_LOGGING
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, KEY_, KEY_LOCAL, TEXT)
values ('714b01f4-60e4-4280-366a-851a01ec80be', 1, '2020-11-02 13:58:00', 'admin', '2020-11-02 15:23:46', 'admin', null, null, 'Outsider', 'Сообщение о постороннем файле', 'Не соответствует заданному формату');

insert into SCANFOLDER_SETTINGS_LOGGING
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, KEY_, KEY_LOCAL, TEXT)
values ('fede7722-f2fb-cf99-fee3-175e4c80966d', 1, '2020-11-02 13:57:51', 'admin', '2020-11-02 15:23:51', 'admin', null, null, 'Duplicate', 'Сообщение о дубликате', 'Файл с таким именем уже существует');

insert into SCANFOLDER_SETTINGS_LOGGING
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, KEY_, KEY_LOCAL, TEXT)
values ('f5eb56ea-8fc8-48a5-eff1-ceb4c7005bbb', 1, '2020-11-02 14:30:55', 'admin', '2020-11-02 15:31:51', 'admin', null, null, 'Error', 'Сообщение об ошибке', 'Ошибка');

insert into SCANFOLDER_SETTINGS_LOGGING
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, KEY_, KEY_LOCAL, TEXT)
values ('e64f20c6-3e18-c7e7-4193-c10e67ffad8c', 1, '2020-11-02 13:57:12', 'admin', '2020-11-02 15:31:55', 'admin', null, null, 'Success', 'Сообщение об успехе', 'Импорт прошел успешно');
