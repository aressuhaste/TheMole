create table Answer (
	id int identity(1,1) not null primary key,
	answer varchar(50),
	properties text
);

create table QuestionType (
	id int identity(1,1) not null primary key,
	type varchar(50)
);

insert QuestionType (type) values('SINGLESELECT');
insert QuestionType (type) values('PICTURESINGLESELECT');

create table Question (
	id int identity(1,1) not null primary key,
	question varchar(255),
	typeId int
);

alter table Question
add constraint fk_Question_QuestionType foreign key(typeId) references QuestionType(id);

create table Person (
	id int identity(1,1) not null primary key,
	name varchar(50),
	email varchar(100)
);

create table AnswerPossibility (
	questionId int not null,
	answerId int not null,
);

alter table AnswerPossibility
add constraint fk_AnswerPossibility_Question foreign key(questionId) references Question(id);

alter table AnswerPossibility
add constraint fk_AnswerPossibility_Answer foreign key(answerId) references Answer(id);

alter table AnswerPossibility
add constraint pk_AnswerPossibilityId primary key(questionId,answerId);

create table CorrectAnswer (
	personId int not null,
	questionId int not null,
	answerId int not null,
);

alter table CorrectAnswer
add constraint fk_CorrectAnswer_Person foreign key(personId) references Person(id);

alter table CorrectAnswer
add constraint fk_CorrectAnswer_AnswerPossibility foreign key(questionId,answerId) references AnswerPossibility(questionId,answerId);

alter table CorrectAnswer
add constraint pk_CorrectAnswerId primary key(personId,questionId,answerId);

create table Game (
	id int identity(1,1) not null primary key,
	mol int
);

alter table Game
add constraint fk_Game_Mol foreign key(mol) references Person(id);

create table Form (
	id int identity(1,1) not null primary key,
	gameId int
);

alter table Form
add constraint fk_Form_Game foreign key(gameId) references Game(id);

create table FormQuestion (
	formId int not null,
	questionId int not null
);

alter table FormQuestion
add constraint fk_Form_Form foreign key(formId) references Form(id);

alter table FormQuestion
add constraint fk_Form_Question foreign key(questionId) references Question(id);

alter table FormQuestion
add constraint pk_FormQuestionId primary key(formId,questionId);

create table FormQuestionAnswer (
	personId int not null,
	formId int not null,
	questionId int not null
);

alter table FormQuestionAnswer
add constraint fk_FormQuestionAnswer_Person foreign key(personId) references Person(id);

alter table FormQuestionAnswer
add constraint fk_FormQuestionAnswer_FormQuestion foreign key(formId,questionId) references FormQuestion(formId,questionId);

alter table FormQuestionAnswer
add constraint pk_FormQuestionAnswerId primary key(personId,formId,questionId);



--drop table FormQuestionAnswer;
--drop table FormQuestion;
--drop table Form;
--drop table Game;
--drop table CorrectAnswer;
--drop table AnswerPossibility;
--drop table Person;
--drop table Question;
--drop table QuestionType;
--drop table Answer;