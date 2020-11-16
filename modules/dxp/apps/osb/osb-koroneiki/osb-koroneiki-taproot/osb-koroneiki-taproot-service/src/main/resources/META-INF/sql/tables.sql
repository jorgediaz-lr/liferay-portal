create table Koroneiki_Account (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	accountId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	accountKey VARCHAR(75) null,
	parentAccountId LONG,
	name VARCHAR(150) null,
	code_ VARCHAR(75) null,
	description STRING null,
	logoId LONG,
	contactEmailAddress VARCHAR(75) null,
	profileEmailAddress VARCHAR(75) null,
	phoneNumber VARCHAR(75) null,
	faxNumber VARCHAR(75) null,
	website VARCHAR(75) null,
	tier VARCHAR(75) null,
	region VARCHAR(75) null,
	language VARCHAR(75) null,
	internal_ BOOLEAN,
	status VARCHAR(75) null
);

create table Koroneiki_AccountNote (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	accountNoteId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	creatorUID VARCHAR(75) null,
	creatorName VARCHAR(75) null,
	modifiedDate DATE null,
	modifierUID VARCHAR(75) null,
	modifierName VARCHAR(75) null,
	accountNoteKey VARCHAR(75) null,
	accountId LONG,
	type_ VARCHAR(75) null,
	priority INTEGER,
	content STRING null,
	format VARCHAR(75) null,
	status VARCHAR(75) null
);

create table Koroneiki_Contact (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	contactId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	contactKey VARCHAR(75) null,
	firstName VARCHAR(75) null,
	middleName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	emailAddress VARCHAR(75) null,
	languageId VARCHAR(75) null,
	emailAddressVerified BOOLEAN
);

create table Koroneiki_ContactAccountRole (
	mvccVersion LONG default 0 not null,
	contactId LONG not null,
	accountId LONG not null,
	contactRoleId LONG not null,
	primary key (contactId, accountId, contactRoleId)
);

create table Koroneiki_ContactRole (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	contactRoleId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	contactRoleKey VARCHAR(75) null,
	name VARCHAR(75) null,
	description STRING null,
	type_ VARCHAR(75) null,
	system_ BOOLEAN
);

create table Koroneiki_ContactTeamRole (
	mvccVersion LONG default 0 not null,
	contactId LONG not null,
	teamId LONG not null,
	contactRoleId LONG not null,
	primary key (contactId, teamId, contactRoleId)
);

create table Koroneiki_Team (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	teamId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	teamKey VARCHAR(75) null,
	accountId LONG,
	name VARCHAR(150) null,
	system_ BOOLEAN
);

create table Koroneiki_TeamAccountRole (
	mvccVersion LONG default 0 not null,
	teamId LONG not null,
	accountId LONG not null,
	teamRoleId LONG not null,
	primary key (teamId, accountId, teamRoleId)
);

create table Koroneiki_TeamRole (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	teamRoleId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	teamRoleKey VARCHAR(75) null,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	type_ VARCHAR(75) null
);