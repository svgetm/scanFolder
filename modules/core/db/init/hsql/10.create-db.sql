-- begin SCANFOLDER_SETTINGS
create table SCANFOLDER_SETTINGS (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FOLEDER_PATH varchar(255),
    LAST_TIME_UPDATE timestamp,
    --
    primary key (ID)
)^
-- end SCANFOLDER_SETTINGS
-- begin SCANFOLDER_TAKE_DESCRIPTOR_FROM_FOLDER
create table SCANFOLDER_TAKE_DESCRIPTOR_FROM_FOLDER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FILE_LIST_NAME_ID varchar(36),
    NAME varchar(255),
    EXTENSION varchar(255),
    SIZE_ bigint,
    DATA_CREATE timestamp,
    --
    primary key (ID)
)^
-- end SCANFOLDER_TAKE_DESCRIPTOR_FROM_FOLDER
