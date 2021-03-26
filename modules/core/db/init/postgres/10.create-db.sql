-- begin SCANFOLDER_SETTINGS
create table SCANFOLDER_SETTINGS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    FOLEDER_PATH varchar(255),
    WORK_ boolean,
    SCANING boolean not null,
    LAST_TIME_UPDATE timestamp,
    --
    primary key (ID)
)^
-- end SCANFOLDER_SETTINGS

-- begin SCANFOLDER_LIST_ATTRIBUTES
create table SCANFOLDER_LIST_ATTRIBUTES (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FILE_DESC_ID uuid,
    NAME varchar(255),
    EXTENSION varchar(255),
    SIZE_ bigint,
    DATA_CREATE timestamp,
    DATA_MOD timestamp,
    LINK_DOWNLOAD varchar(255),
    --
    primary key (ID)
)^
-- end SCANFOLDER_LIST_ATTRIBUTES
-- begin SCANFOLDER_EXPANSION_SETTINGS
create table SCANFOLDER_EXPANSION_SETTINGS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    EXPANSION varchar(255),
    --
    primary key (ID)
)^
-- end SCANFOLDER_EXPANSION_SETTINGS
-- begin SCANFOLDER_FAIL_NAME_FILES
create table SCANFOLDER_FAIL_NAME_FILES (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME_FILE varchar(255),
    --
    primary key (ID)
)^
-- end SCANFOLDER_FAIL_NAME_FILES
-- begin SCANFOLDER_SETTINGS_LOGGING
create table SCANFOLDER_SETTINGS_LOGGING (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    KEY_ varchar(255),
    KEY_LOCAL varchar(255),
    TEXT varchar(255),
    --
    primary key (ID)
)^
-- end SCANFOLDER_SETTINGS_LOGGING
-- begin SCANFOLDER_SETTINGS_FAIL_NAME_FILES_LINK
create table SCANFOLDER_SETTINGS_FAIL_NAME_FILES_LINK (
    SETTINGS_ID uuid,
    FAIL_NAME_FILES_ID uuid,
    primary key (SETTINGS_ID, FAIL_NAME_FILES_ID)
)^
-- end SCANFOLDER_SETTINGS_FAIL_NAME_FILES_LINK
-- begin SCANFOLDER_SETTINGS_EXPANSION_SETTINGS_LINK
create table SCANFOLDER_SETTINGS_EXPANSION_SETTINGS_LINK (
    SETTINGS_ID uuid,
    EXPANSION_SETTINGS_ID uuid,
    primary key (SETTINGS_ID, EXPANSION_SETTINGS_ID)
)^
-- end SCANFOLDER_SETTINGS_EXPANSION_SETTINGS_LINK
