create index IX_8B5D7FE4 on OSB_LicenseEntry (productEntryId, portalVersionMin);
create unique index IX_7576438B on OSB_LicenseEntry (productEntryId, type_[$COLUMN_LENGTH:75$]);

create index IX_D9E7AC39 on OSB_LicenseKey (accountEntryId);
create index IX_5D0EB94A on OSB_LicenseKey (assetReceiptLicenseUuid[$COLUMN_LENGTH:75$], active_);
create index IX_F432AF3A on OSB_LicenseKey (assetReceiptLicenseUuid[$COLUMN_LENGTH:75$], complimentary, active_);
create index IX_C31F329C on OSB_LicenseKey (assetReceiptLicenseUuid[$COLUMN_LENGTH:75$], productId[$COLUMN_LENGTH:75$], serverId[$COLUMN_LENGTH:4000$], active_);
create index IX_4CCFA67E on OSB_LicenseKey (koroneikiAccountKey[$COLUMN_LENGTH:75$], productEntryId);
create index IX_6BE3F33C on OSB_LicenseKey (koroneikiProductPurchaseKey[$COLUMN_LENGTH:75$], clusterId);
create index IX_3B9F5AEA on OSB_LicenseKey (licenseKeySetId);
create index IX_AEC46A56 on OSB_LicenseKey (offeringEntryId, clusterId, active_);
create index IX_34C23F6D on OSB_LicenseKey (offeringEntryId, complimentary, active_);
create index IX_8472FBAC on OSB_LicenseKey (offeringEntryId, licenseEntryType[$COLUMN_LENGTH:75$], complimentary, active_);
create index IX_DE309610 on OSB_LicenseKey (productEntryName[$COLUMN_LENGTH:75$], serverId[$COLUMN_LENGTH:4000$], active_);
create index IX_AFB6E8E7 on OSB_LicenseKey (productId[$COLUMN_LENGTH:75$], serverId[$COLUMN_LENGTH:4000$]);
create index IX_A7B2FB73 on OSB_LicenseKey (userId, accountEntryId);
create index IX_1A89947B on OSB_LicenseKey (userId, productId[$COLUMN_LENGTH:75$]);
create index IX_F6FA838F on OSB_LicenseKey (uuid_[$COLUMN_LENGTH:75$]);

create index IX_AA77CE1D on OSB_LicenseKeySet (accountEntryId);
create index IX_484515A7 on OSB_LicenseKeySet (koroneikiAccountKey[$COLUMN_LENGTH:75$], name[$COLUMN_LENGTH:75$]);
create index IX_62FEABD6 on OSB_LicenseKeySet (userId, accountEntryId, name[$COLUMN_LENGTH:75$]);