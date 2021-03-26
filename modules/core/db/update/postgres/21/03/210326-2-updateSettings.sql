alter table SCANFOLDER_SETTINGS add column SCANING boolean ^
update SCANFOLDER_SETTINGS set SCANING = false where SCANING is null ;
alter table SCANFOLDER_SETTINGS alter column SCANING set not null ;
