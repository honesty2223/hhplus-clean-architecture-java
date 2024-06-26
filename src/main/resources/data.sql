insert into lecture(lecture_id, lecture_name, lecture_date, max_capacity, current_enrollment) values(1, '자바', '2024-06-29', 30, 0);
insert into lecture(lecture_id, lecture_name, lecture_date, max_capacity, current_enrollment) values(2, '파이썬', '2024-06-29', 30, 0);
insert into lecture(lecture_id, lecture_name, lecture_date, max_capacity, current_enrollment) values(3, '면접', '2024-07-06', 30, 0);
insert into lecture(lecture_id, lecture_name, lecture_date, max_capacity, current_enrollment) values(4, '클린 아키텍처', '2024-06-29', 20, 0);
insert into lecture(lecture_id, lecture_name, lecture_date, max_capacity, current_enrollment) values(5, 'TDD', '2024-07-06', 30, 0);

insert into student(student_id, student_name) values(1, '전우치');
insert into student(student_id, student_name) values(2, '조승우');
insert into student(student_id, student_name) values(3, '홍길동');
insert into student(student_id, student_name) values(4, '류선재');
insert into student(student_id, student_name) values(5, '임 솔');
insert into student(student_id, student_name) values(6, '김희지');
insert into student(student_id, student_name) values(7, '이유미');
insert into student(student_id, student_name) values(8, '한가인');
insert into student(student_id, student_name) values(9, '김남길');
insert into student(student_id, student_name) values(10, '강호동');

insert into registration(student_id, lecture_id, registered_at) values(1, 1, '2024-06-26');
insert into registration(student_id, lecture_id, registered_at) values(1, 2, '2024-06-26');
insert into registration(student_id, lecture_id, registered_at) values(2, 1, '2024-06-26');
insert into registration(student_id, lecture_id, registered_at) values(2, 2, '2024-06-26');