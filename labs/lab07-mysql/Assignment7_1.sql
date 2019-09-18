show databases;
create database assignment;
use assignment;
Show tables;

drop table Chatters;
drop table Message;
drop table Notification_for;
drop table Posted_on;
drop table Follower;
drop table Admin;
drop table Request;
drop table Tag;
drop table Like1;
drop table Comment_on_by;
drop table Comment_Reply;
drop table Reply;
drop table Posted_By;
drop table Person_auths;
drop table Person;
drop table Post;
drop table Group_or_Wall;
drop table Chatbox;
drop table Notification;
drop table Comment;
drop table ids;

create table ids
	(
		Table1		varchar(30),
		ID 			int
	);
    
insert into ids values('Person', 0);
insert into ids values('Person_auths', 0);
insert into ids values('Post', 0);
insert into ids values('Group_or_Wall', 0);
insert into ids values('Comment', 0);
insert into ids values('Reply', 0);
insert into ids values('Chatbox', 0);
insert into ids values('Notification', 0);
    
create table Person
	(
		Person_ID		int auto_increment comment 'personId PK',
		First_name		varchar(50),
		Last_name		varchar(50),
		Date_of_birth		timestamp,
		Gender		numeric(2,0) NOT NULL,
		Mobile_no 	numeric(11,0) NOT NULL,
		Email		varchar(256) NOT NULL,
		Username		varchar(256) NOT NULL,
		Password		varchar(256) NOT NULL,
		Profile_picture		varchar(256),
		Address		varchar(256),
		Other_details		varchar(256),
		primary key (Person_ID)
	);
    
create table Person_auths
	(
		Auths_ID		int auto_increment comment 'authsId PK',
		Person_ID		int,
		Credential		varchar(256) NOT NULL,
		Identity_type	varchar(50) NOT NULL,
		Identifier		varchar(50) NOT NULL,
		primary key (Auths_ID),
        foreign key (Person_ID) references Person
			on delete Cascade
	);    
    
create table Post
	(
		Post_ID		int auto_increment comment 'postId PK',
		Text1		varchar(256),
		Image		varchar(256),
		Video		varchar(256),
		Link		varchar(256),
		Privacy		numeric(1,0),
		Time1		timestamp,
		primary key (Post_ID)
	);
    
create table Posted_By
	(
		Post_ID			int,
		Person_ID		int,
		primary key (Post_ID),
		foreign key (Person_ID) references Person
			on delete Cascade,
		foreign key (Post_ID) references Post
			on delete Cascade
	);
    
create table Group_or_Wall
	(
		Group_or_wall_id		int auto_increment comment 'groupOrWallIdPK',
		Name		varchar(50),
		Type		numeric(1,0),
		Group_description		varchar(256),
		Group_photo		varchar(100),
		primary key (Group_or_wall_id)
	);
    
create table Posted_on
	(
		Post_ID					int,
		Group_or_wall_id		int,	
		primary key (Post_ID),
		foreign key (Post_ID) references Post
			on delete Cascade,
		foreign key (Group_or_wall_id) references Group_or_Wall
			on delete Cascade	
	);
    
create table Follower
	(
		Person_ID		int	,
		Group_or_wall_id		int,	
		primary key (Person_ID, Group_or_wall_id),
		foreign key (Person_ID) references Person
			on delete Cascade,
		foreign key (Group_or_wall_id) references Group_or_Wall
			on delete Cascade	
	);
    
create table Admin
	(
		Person_ID		int,
		Group_or_wall_id		int,	
		primary key (Person_ID, Group_or_wall_id),
		foreign key (Person_ID) references Person
			on delete Cascade,
		foreign key (Group_or_wall_id) references Group_or_Wall
			on delete Cascade	
	);
    
create table Request
	(
		Person_ID		int,
		Group_or_wall_id		int,	
		primary key (Person_ID, Group_or_wall_id),
		foreign key (Person_ID) references Person
			on delete Cascade,
		foreign key (Group_or_wall_id) references Group_or_Wall
			on delete Cascade	
	);
    
create table Tag
	(
		Person_ID		int,
		Post_ID		int,
		primary key (Person_ID, Post_ID),
		foreign key (Person_ID) references Person
			on delete Cascade,
		foreign key (Post_ID) references Post
			on delete Cascade	
	);
    
create table Like1
	(
		Person_ID		int,
		Post_ID		int,
		primary key (Person_ID, Post_ID),
		foreign key (Person_ID) references Person
			on delete Cascade,
		foreign key (Post_ID) references Post
			on delete Cascade
	);

create table Comment
	(
		Comment_ID		int auto_increment comment 'commentId PK',
		Text1		varchar(256),
		Time1		timestamp,
		primary key (Comment_ID)
	);

create table Comment_on_by
	(
		Person_ID 	int,
		Comment_ID 	int,
		Post_ID		int,
		primary key (Comment_ID),
		foreign key (Person_ID) references Person
			on delete Cascade,
		foreign key (Comment_ID) references Comment
			on delete Cascade,
		foreign key (Post_ID) references Post
			on delete Cascade	
	);

create table Reply
	(
		Reply_ID		int auto_increment comment 'replyIdPK',
		Text1		varchar(256),
		Time1		timestamp,
		primary key (Reply_ID)
	);

create table Comment_Reply
	(
		Person_ID		int,
		Reply_ID		int,
		Comment_ID		int,
		primary key (Reply_ID),
		foreign key (Person_ID) references Person
			on delete Cascade,
		foreign key (Reply_ID) references Reply
			on delete Cascade,	
		foreign key (Comment_ID) references Comment
			on delete Cascade
	);

create table Chatbox
	(
		Chatbox_ID  int auto_increment comment 'chatBoxIdPK',
		Name  		varchar(256),
		primary key (chatbox_ID)
	);

create table Chatters 
	(
		Chatbox_ID 	int,
		Person_ID 	int,
		primary key (Chatbox_ID, Person_ID),
		foreign key (Chatbox_ID) references Chatbox
			on delete Cascade,
		foreign key (Person_ID) references Person
			on delete Cascade	
	);

create table Message 
	(
		Chatbox_ID 	int,
		Person_ID 	int,
		Time1 	timestamp,
		Message_text	varchar(256),
		primary key (Chatbox_ID , Person_ID, Time1),
		foreign key (Chatbox_ID) references Chatbox
			on delete Cascade,
		foreign key (Person_ID) references Person
			on delete Cascade	
	);

create table Notification
	(
		Notification_ID 	int auto_increment comment 'notificationId PK',
		Text1 				varchar(256),
		Seen_unseen 		numeric(1,0),
		Time1 				timestamp,
		primary key (Notification_ID)
	);

create table Notification_for
	(
		Notification_ID 	int,
		Person_ID 			int,
		primary key (Notification_ID) ,
		foreign key (Notification_ID) references Notification
			on delete Cascade,
		foreign key (Person_ID) references Person
			on delete Cascade
	);    
