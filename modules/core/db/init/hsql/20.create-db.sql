-- begin SCANFOLDER_TAKE_DESCRIPTOR_FROM_FOLDER
alter table SCANFOLDER_TAKE_DESCRIPTOR_FROM_FOLDER add constraint FK_SCANFOLDER_TAKE_DESCRIPTOR_FROM_FOLDER_ON_FILE_LIST_NAME foreign key (FILE_LIST_NAME_ID) references SYS_FILE(ID)^
create index IDX_SCANFOLDER_TAKE_DESCRIPTOR_FROM_FOLDER_ON_FILE_LIST_NAME on SCANFOLDER_TAKE_DESCRIPTOR_FROM_FOLDER (FILE_LIST_NAME_ID)^
-- end SCANFOLDER_TAKE_DESCRIPTOR_FROM_FOLDER