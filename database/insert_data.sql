INSERT INTO role(code, name)
VALUES ("ADMIN", "Administrator");
INSERT INTO role(code, name)
VALUES ("STUDENT", "Student");
INSERT INTO role(code, name)
VALUES ("LECTURER", "Lecturer");


INSERT INTO education_training(username, password, address, token_code, role_code, first_name, last_name, phone_number,
                               email, picture)
VALUES ("admin", "123456", "Thành phố Hồ Chí Minh", "kjflhaskdfjayoiuyiuyiupiouoiyuytidmnfsfdsgdbhsldkfjas", "ADMIN",
        "Phòng", "Đào tạo", "0123456789", "phongdaotao@gmail.com", "/api/file/education_training/admin/avatar.png");

INSERT INTO `student` (`id`, `address`, `birthday`, `createdby`, `createddate`, `first_name`, `fullname`, `gender`,
                       `last_name`, `modifiedby`, `modifieddate`, `password`, `phone_number`, `picture`, `start_year`,
                       `token_code`, `username`, `role_code`)
VALUES ('19110143', 'Quảng Bình', '2001-04-15 00:00:00', 'admin', '2021-05-10 02:55:25', 'Cao Đinh Sỹ',
        'Cao Đinh Sỹ Vỹ', 'Male', 'Vỹ', 'admin', '2021-05-10 02:55:25', '19110143', '0975543975',
        '/api/file/student/19110143/avatar.png', 2021,
        'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImJpcnRoZGF5IjoiU3VuIEFwciAxNSAwNzowMDowMCBJQ1QgMjAwMSIsImxhc3ROYW1lIjoiVuG7uSIsInRva2VuQ29kZSI6bnVsbCwibGlzdFJlc3VsdCI6W10sImFkZHJlc3MiOiJRdeG6o25nIELDrG5oIiwiZ2VuZGVyIjoiTWFsZSIsInN0YXJ0WWVhciI6MjAyMSwibWVzc2FnZSI6bnVsbCwicmVnaXN0ZXJJZHMiOltdLCJwaWN0dXJlIjoiXC9hcGlcL2ZpbGVcL3N0dWRlbnRcLzE5MTEwMTQzXC9hdmF0YXIucG5nIiwiYXV0aG9yaXRpZXMiOltdLCJmaXJzdE5hbWUiOiJDYW8gxJBpbmggU-G7uSIsInBhc3N3b3JkIjoiMTkxMTAxNDMiLCJwaG9uZU51bWJlciI6IjA5NzU1NDM5NzUiLCJjcmVhdGVkRGF0ZSI6bnVsbCwiY3JlYXRlZEJ5IjpudWxsLCJyb2xlQ29kZSI6IlNUVURFTlQiLCJodHRwU3RhdHVzIjoiT0siLCJtb2RpZmllZERhdGUiOm51bGwsImxpc3RSZXF1ZXN0IjpbXSwibW9kaWZpZWRCeSI6bnVsbCwiaWQiOiIxOTExMDE0MyIsImZ1bGxuYW1lIjoiQ2FvIMSQaW5oIFPhu7kgVuG7uSIsInVzZXJuYW1lIjoiMTkxMTAxNDMifX0.PXXWDVpAAqr1Ko0pOcd52Aaed2eXz6Aoljy2h8fVkvg',
        '19110143', 'STUDENT'),
       ('19110144', 'Quảng Bình', '2001-04-20 00:00:00', 'admin', '2021-05-10 02:57:05', 'Đinh Thị Thùy',
        'Đinh Thị Thùy Linh', 'Female', 'Linh', 'admin', '2021-05-10 02:57:05', '19110144', '0987654321',
        '/api/file/student/19110144/avatar.png', 2021,
        'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImJpcnRoZGF5IjoiRnJpIEFwciAyMCAwNzowMDowMCBJQ1QgMjAwMSIsImxhc3ROYW1lIjoiTGluaCIsInRva2VuQ29kZSI6bnVsbCwibGlzdFJlc3VsdCI6W10sImFkZHJlc3MiOiJRdeG6o25nIELDrG5oIiwiZ2VuZGVyIjoiRmVtYWxlIiwic3RhcnRZZWFyIjoyMDIxLCJtZXNzYWdlIjpudWxsLCJyZWdpc3RlcklkcyI6W10sInBpY3R1cmUiOiJcL2FwaVwvZmlsZVwvc3R1ZGVudFwvMTkxMTAxNDRcL2F2YXRhci5wbmciLCJhdXRob3JpdGllcyI6W10sImZpcnN0TmFtZSI6IsSQaW5oIFRo4buLIFRow7l5IiwicGFzc3dvcmQiOiIxOTExMDE0NCIsInBob25lTnVtYmVyIjoiMDk4NzY1NDMyMSIsImNyZWF0ZWREYXRlIjpudWxsLCJjcmVhdGVkQnkiOm51bGwsInJvbGVDb2RlIjoiU1RVREVOVCIsImh0dHBTdGF0dXMiOiJPSyIsIm1vZGlmaWVkRGF0ZSI6bnVsbCwibGlzdFJlcXVlc3QiOltdLCJtb2RpZmllZEJ5IjpudWxsLCJpZCI6IjE5MTEwMTQ0IiwiZnVsbG5hbWUiOiLEkGluaCBUaOG7iyBUaMO5eSBMaW5oIiwidXNlcm5hbWUiOiIxOTExMDE0NCJ9fQ.Kbh3CLdJw4mXrPrvi2Uys1YJPQ2Hqs5YLBur5UFSk_E',
        '19110144', 'STUDENT'),
       ('19110145', 'Quảng Bình', '2001-04-25 00:00:00', 'admin', '2021-05-10 02:57:05', 'Cao Quý', 'Cao Quý Nhân',
        'Male', 'Nhân', 'admin', '2021-05-10 02:57:05', '19110145', '0983754321',
        '/api/file/student/19110145/avatar.png', 2021,
        'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImJpcnRoZGF5IjoiRnJpIEFwciAyMCAwNzowMDowMCBJQ1QgMjAwMSIsImxhc3ROYW1lIjoiTGluaCIsInRva2VuQ29kZSI6bnVsbCwibGlzdFJlc3VsdCI6W10sImFkZHJlc3MiOiJRdeG6o25nIELDrG5oIiwiZ2VuZGVyIjoiRmVtYWxlIiwic3RhcnRZZWFyIjoyMDIxLCJtZXNzYWdlIjpudWxsLCJyZWdpc3RlcklkcyI6W10sInBpY3R1cmUiOiJcL2FwaVwvZmlsZVwvc3R1ZGVudFwvMTkxMTAxNDRcL2F2YXRhci5wbmciLCJhdXRob3JpdGllcyI6W10sImZpcnN0TmFtZSI6IsSQaW5oIFRo4buLIFRow7l5IiwicGFzc3dvcmQiOiIxOTExMDE0NCIsInBob25lTnVtYmVyIjoiMDk4NzY1NDMyMSIsImNyZWF0ZWREYXRlIjpudWxsLCJjcmVhdGVkQnkiOm51bGwsInJvbGVDb2RlIjoiU1RVREVOVCIsImh0dHBTdGF0dXMiOiJPSyIsIm1vZGlmaWVkRGF0ZSI6bnVsbCwibGlzdFJlcXVlc3QiOltdLCJtb2RpZmllZEJ5IjpudWxsLCJpZCI6IjE5MTEwMTQ0IiwiZnVsbG5hbWUiOiLEkGluaCBUaOG7iyBUaMO5eSBMaW5oIiwidXNlcm5hbWUiOiIxOTExMDE0NCJ9fQ.Kbh3CLdJw4mXrPrvi2Uys1YJPQ',
        '19110145', 'STUDENT'),
       ('19110146', 'Quảng Bình', '2001-04-30 00:00:00', 'admin', '2021-05-10 02:57:05', 'Cao Như', 'Cao Như Thuần',
        'Male', 'Thuần', 'admin', '2021-05-10 02:57:05', '19110146', '0807654321',
        '/api/file/student/19110146/avatar.png', 2021,
        'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImJpcnRoZGF5IjoiRnJpIEFwciAyMCAwNzowMDowMCBJQ1QgMjAwMSIsImxhc3ROYW1lIjoiTGluaCIsInRva2VuQ29kZSI6bnVsbCwibGlzdFJlc3VsdCI6W10sImFkZHJlc3MiOiJRdeG6o25nIELDrG5oIiwiZ2VuZGVyIjoiRmVtYWxlIiwic3RhcnRZZWFyIjoyMDIxLCJtZXNzYWdlIjpudWxsLCJyZWdpc3RlcklkcyI6W10sInBpY3R1cmUiOiJcL2FwaVwvZmlsZVwvc3R1ZGVudFwvMTkxMTAxNDRcL2F2YXRhci5wbmciLCJhdXRob3JpdGllcyI6W10sImZpcnN0TmFtZSI6IsSQaW5oIFRo4buLIFRow7l5IiwicGFzc3dvcmQiOiIxOTExMDE0NCIsInBob25lTnVtYmVyIjoiMDk4NzY1NDMyMSIsImNyZWF0ZWREYXRlIjpudWxsLCJjcmVhdGVkQnkiOm51bGwsInJvbGVDb2RlIjoiU1RVREVOVCIsImh0dHBTdGF0dXMiOiJPSyIsIm1vZGlmaWVkRGF0ZSI6bnVsbCwibGlzdFJlcXVlc3QiOltdLCJtb2RpZmllZEJ5IjpudWxsLCJpZCI6IjE5MTEwMTQ0IiwiZnVsbG5hbWUiOiLEkGluaCBUaOG7iyBUaMO5eSBMaW5oIiwidXNlcm5hbWUiOiIxOTExMDE02Hqs5YLBur5UFSk_E',
        '19110146', 'STUDENT');;

INSERT INTO lecturer(username, password, fullname, birthday, phone_number, address, token_code, picture, role_code)
VALUES ("lecturer1", "123456", "Nguyễn Văn Hiển", "1974-07-15 00:00:00", "0875543975", "Hồ Chí Minh",
        "zsdfasdfcvzutuyruytetrewerytuiyoppuioyiuoyoiucxvzx", "/api/file/lecturer/1/avatar.png", "LECTURER"),
       ("lecturer2", "123456", "Nguyễn Trần Thi Văn", "1981-09-20 00:00:00", "0375543976", "Hồ Chí Minh",
        "vxzcvaefwefmnvcxnvvcxnbvbxfdshfdhrtdhtuetrertdjwfecs", "/api/file/lecturer/2/avatar.png", "LECTURER"),
       ("lecturer3", "123456", "Trần Thị Phú", "1984-06-15 00:00:00", "0875283976", "Hồ Chí Minh",
        "vxzcvaefwefmnvcxnvvcakjflhasdxnbvbxfdshfdhrtdhtuetrertdjwfecs", "/api/file/lecturer/3/avatar.png", "LECTURER");


INSERT INTO course(id, name, number_of_credit, fee)
VALUES ("MATH01", "Toán 1", 3, 2250000),
       ("MATH02", "Toán 2", 3, 2250000),
       ("PHYSICS01", "Lý 1", 4, 3000000),
       ("WDPG", "Lập trình Window", 3, 2250000);


INSERT INTO section_class(id, name, course_id, room, lecturer_id, period, description)
VALUES ("MATH01_20_01", "Toán 1 - Nhóm 1", "MATH01", "A3-303", 1, 15, "Đây là Nhóm 1 của môn Toán 1."),
       ("MATH01_20_02", "Toán 1 - Nhóm 2", "MATH01", "A3-304", 2, 15, "Đây là Nhóm 2 của môn Toán 1."),
       ("MATH01_20_03", "Toán 1 - Nhóm 3", "MATH01", "A3-305", 3, 15, "Đây là Nhóm 3 của môn Toán 1."),
       ("MATH02_20_01", "Toán 2 - Nhóm 1", "MATH02", "A2-301", 1, 14, "Đây là Nhóm 1 của môn Toán 2."),
       ("MATH02_20_02", "Toán 2 - Nhóm 2", "MATH02", "A2-302", 2, 14, "Đây là Nhóm 2 của môn Toán 2."),
       ("PHYSICS01_20_01", "Lý 1 - Nhóm 1", "PHYSICS01", "A2-303", 3, 15, "Đây là Nhóm 1 của môn Lý 1."),
       ("PHYSICS01_20_02", "Lý 1 - Nhóm 2", "PHYSICS01", "A2-304", 1, 15, "Đây là Nhóm 2 của môn Lý 1."),
       ("WDPG_20_01", "Lập trình Window - Nhóm 1", "WDPG", "A2-305", 2, 15, "Đây là Nhóm 1 của môn Lập trình Window."),
       ("WDPG_20_02", "Lập trình Window - Nhóm 2", "WDPG", "A2-306", 3, 15, "Đây là Nhóm 2 của môn Lập trình Window.");


INSERT INTO register(student_id, section_class_id, midterm_mark, endterm_mark)
VALUES ("19110143", "MATH01_20_01", 9.5, 7.5),
       ("19110143", "MATH02_20_02", 5.5, 9.5),
       ("19110143", "PHYSICS01_20_01", 6, 8),
       ("19110143", "WDPG_20_02", 8.5, 9),

       ("19110144", "MATH01_20_02", 5, 8),
       ("19110144", "MATH02_20_01", 7, 6),
       ("19110144", "PHYSICS01_20_02", 7, 9),
       ("19110144", "WDPG_20_01", 8.5, 10),

       ("19110145", "MATH01_20_03", 8, 5),
       ("19110145", "MATH02_20_01", 6, 7),
       ("19110145", "PHYSICS01_20_01", 9, 7),
       ("19110145", "WDPG_20_01", 10, 8.5),

       ("19110146", "MATH01_20_02", 8, 5),
       ("19110146", "MATH02_20_02", 6, 7),
       ("19110146", "PHYSICS01_20_02", 9, 7),
       ("19110146", "WDPG_20_02", 10, 8.5);


INSERT INTO notification(title, short_description, content)
VALUES ("Thông báo 1", "Đây là mô tả ngắn của thông báo 1", "Đây là content của thông báo 1"),
       ("Thông báo 2", "Đây là mô tả ngắn của thông báo 2", "Đây là content của thông báo 2");






