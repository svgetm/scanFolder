-- begin SCANFOLDER_LIST_ATTRIBUTES
alter table SCANFOLDER_LIST_ATTRIBUTES add constraint FK_SCANFOLDER_LIST_ATTRIBUTES_ON_FILE_DESC foreign key (FILE_DESC_ID) references SYS_FILE(ID)^
create index IDX_SCANFOLDER_LIST_ATTRIBUTES_ON_FILE_DESC on SCANFOLDER_LIST_ATTRIBUTES (FILE_DESC_ID)^
-- end SCANFOLDER_LIST_ATTRIBUTES
-- begin SCANFOLDER_SETTINGS_FAIL_NAME_FILES_LINK
alter table SCANFOLDER_SETTINGS_FAIL_NAME_FILES_LINK add constraint FK_SETFAINAMFIL_ON_SETTINGS foreign key (SETTINGS_ID) references SCANFOLDER_SETTINGS(ID)^
alter table SCANFOLDER_SETTINGS_FAIL_NAME_FILES_LINK add constraint FK_SETFAINAMFIL_ON_FAIL_NAME_FILES foreign key (FAIL_NAME_FILES_ID) references SCANFOLDER_FAIL_NAME_FILES(ID)^
-- end SCANFOLDER_SETTINGS_FAIL_NAME_FILES_LINK
-- begin SCANFOLDER_SETTINGS_EXPANSION_SETTINGS_LINK
alter table SCANFOLDER_SETTINGS_EXPANSION_SETTINGS_LINK add constraint FK_SETEXPSET_ON_SETTINGS foreign key (SETTINGS_ID) references SCANFOLDER_SETTINGS(ID)^
alter table SCANFOLDER_SETTINGS_EXPANSION_SETTINGS_LINK add constraint FK_SETEXPSET_ON_EXPANSION_SETTINGS foreign key (EXPANSION_SETTINGS_ID) references SCANFOLDER_EXPANSION_SETTINGS(ID)^
-- end SCANFOLDER_SETTINGS_EXPANSION_SETTINGS_LINK
