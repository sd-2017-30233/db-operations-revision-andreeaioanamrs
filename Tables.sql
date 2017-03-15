Create database if not exists Management;
Create table if not exists Student(
Student_ID int primary key,
Nume char(15),
Birth_Date date,
Adress char(35));

Create table if not exists Course(
Course_ID int primary key,
Nume char(15),
Teacher char(15),
Study_Year int);

create table if not exists Class(
Class_ID int primary key,
Course_ID int ,
Student_ID int);

alter table Class
add constraint fk_Student_Class
foreign key (Student_ID)
references Student(Student_ID)
on update cascade
on delete cascade;

alter table Class
add constraint fk_Course_Class
foreign key (Course_ID)
references Course(Course_ID)
on update cascade
on delete cascade;

