package com.window_programming_api.file.service.impl;

import com.window_programming_api.dto.MyFileDTO;
import com.window_programming_api.dto.StudentDTO;
import com.window_programming_api.entity.RegisterEntity;
import com.window_programming_api.entity.SectionClassEntity;
import com.window_programming_api.entity.StudentEntity;
import com.window_programming_api.file.service.IStudentFileService;
import com.window_programming_api.repository.RegisterRepository;
import com.window_programming_api.repository.SectionClassRepository;
import com.window_programming_api.repository.StudentRepository;
import com.window_programming_api.utils.MyFileUtil;
import com.window_programming_api.utils.POIUtil;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class StudentFileService extends BaseFileService implements IStudentFileService {

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private RegisterRepository registerRepo;

	@Autowired
	private SectionClassRepository sectionClassRepo;

	@Override
	public byte[] getFile(String source) {
		String fileName = source.split("/api/file/student/")[1];
		File rootPath = new File(MyFileUtil.removeDoubleSlash("src/main/resources/sources/student/" + fileName));
		return MyFileUtil.getByteBySource(rootPath.getAbsolutePath());
	}

	@Override
	public StudentDTO findOneByFileName(String fileName) {
		StudentDTO studentDto = new StudentDTO();
		File rootPath = new File(MyFileUtil.removeDoubleSlash("src/main/resources/sources/student/" + fileName));
		try {
			byte[] fileByte = MyFileUtil.getByteBySource(rootPath.getAbsolutePath());
			MyFileDTO fileDto = new MyFileDTO();
			fileDto.setFileName(fileName.split("\\.")[0]);
			fileDto.setFileType(fileName.split("\\.")[1]);
			fileDto.setBase64String(Base64.getEncoder().encodeToString(fileByte));

			studentDto.getListResult().add(fileDto);
			studentDto.setMessage("Load file to base64String successfully.");
			return studentDto;
		} catch (Exception ex) {
			return (StudentDTO) this.ExceptionObject(studentDto, "File is not found.");
		}
	}

	@Override
	public StudentDTO save(MyFileDTO fileDto) {
		StudentDTO studentDto = new StudentDTO();

		File filePath = new File(MyFileUtil.removeDoubleSlash(
				"src/main/resources/sources/student/" + fileDto.getFileName() + "." + fileDto.getFileType()));
		if (!filePath.exists()) {
			fileDto.setFileName("student/" + fileDto.getFileName());
			studentDto.getListResult().add(
					MyFileUtil.upFile(fileDto.getFileName() + "." + fileDto.getFileType(), fileDto.getBase64String()));
			studentDto.setMessage("Upload file successfully.");
			return studentDto;
		}

		return (StudentDTO) this.ExceptionObject(studentDto, "This file exists already.");
	}

	@Override
	public StudentDTO update(MyFileDTO fileDto) {
		StudentDTO studentDto = new StudentDTO();

		File filePath = new File(MyFileUtil.removeDoubleSlash(
				"src/main/resources/sources/student/" + fileDto.getFileName() + "." + fileDto.getFileType()));
		if (filePath.exists()) {
			fileDto.setFileName("student/" + fileDto.getFileName());
			studentDto.getListResult().add(
					MyFileUtil.upFile(fileDto.getFileName() + "." + fileDto.getFileType(), fileDto.getBase64String()));
			studentDto.setMessage("Update file successfully.");
			return studentDto;
		}

		return (StudentDTO) this.ExceptionObject(studentDto, "This file does not exists.");
	}

	@Override
	public StudentDTO delete(MyFileDTO fileDto) {
		StudentDTO studentDto = new StudentDTO();

		File filePath = new File(MyFileUtil.removeDoubleSlash(
				"src/main/resources/sources/student/" + fileDto.getFileName() + "." + fileDto.getFileType()));

		if (filePath.exists()) {
			try {
				FileUtils.forceDelete(filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			studentDto.setMessage("Delete file successfully.");
			return studentDto;
		}

		return (StudentDTO) this.ExceptionObject(studentDto, "File does not exist.");
	}

	@Override
	public StudentDTO deleteAll(String studentId) {
		StudentDTO studentDto = new StudentDTO();

		File studentDir = new File(MyFileUtil.removeDoubleSlash("src/main/resources/sources/student/" + studentId));
		try {
			FileUtils.deleteDirectory(studentDir);
			studentDto.setMessage("Delete all file(s) of student successfully.");
			return studentDto;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (StudentDTO) this.ExceptionObject(studentDto, "Delete all file(s) of student fail.");
	}

	@SuppressWarnings("deprecation")
	@Override
	public StudentDTO printStudentList() {
		StudentDTO studentDto = new StudentDTO();
		List<StudentEntity> studentEntities = studentRepo.findAll();

		// Create document
		// Blank Document
		@SuppressWarnings("resource")
		XWPFDocument document = new XWPFDocument();

		// set orientation to landscape
		CTBody body = document.getDocument().getBody();
		if (!body.isSetSectPr()) {
			body.addNewSectPr();
		}
		CTSectPr section = body.getSectPr();
		if (!section.isSetPgSz()) {
			section.addNewPgSz();
		}
		CTPageSz pageSize = section.getPgSz();
		pageSize.setOrient(STPageOrientation.LANDSCAPE);
		// A4 = 595x842 / multiply 20 since BigInteger represents 1/20 Point
		pageSize.setW(BigInteger.valueOf(16840));
		pageSize.setH(BigInteger.valueOf(11900));

		// Write the Document in file system
		FileOutputStream out;
		try {
			File pathDoc = new File("src/main/resources/sources/student");
			if (!pathDoc.exists())
				pathDoc.mkdirs();

			out = new FileOutputStream(new File(pathDoc.getAbsolutePath() + "/list_of_student.docx"));

			// create Paragraph
			POIUtil.setRun(document.createParagraph().createRun(), 14, "DANH SÁCH HỌC SINH: ", true);

			// create table
			XWPFTable table = document.createTable();
			// create first row
			XWPFTableRow tableRow = table.getRow(0);
			POIUtil.addParagraphToTableCell(tableRow.getCell(0), 14, "Student ID ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "First name ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Last name ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Address ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Birthday ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Gender ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Phone ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Picture ", true);

			for (int i = 0; i < studentEntities.size(); i++) {
				// create row after
				tableRow = table.createRow();
				POIUtil.addParagraphToTableCell(tableRow.getCell(0), 14, studentEntities.get(i).getId(), false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(1), 14, studentEntities.get(i).getFirstName(), false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(2), 14, studentEntities.get(i).getLastName(), false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(3), 14, studentEntities.get(i).getAddress(), false);
				Date birthday = studentEntities.get(i).getBirthday();
				String birthdayStr = birthday.getDate() + "/" + (birthday.getMonth() + 1) + "/"
						+ (birthday.getYear() + 1900);
				POIUtil.addParagraphToTableCell(tableRow.getCell(4), 14, birthdayStr, false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(5), 14, studentEntities.get(i).getGender(), false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(6), 14, studentEntities.get(i).getPhoneNumber(),
						false);
				POIUtil.addPictureToTableCell(tableRow.getCell(7),
						"src/main/resources/sources/student/" + studentEntities.get(i).getId() + "/avatar.png");
			}

			document.write(out);
			out.close();

			System.out.println("list_of_student.docx written successully");
			String linkDocFile = "/api/file/student/list_of_student.docx";
			studentDto.getListResult().add(linkDocFile);
			studentDto.setMessage("list_of_student.docx written successully.");
			return studentDto;

		} catch (IOException e) {
			e.printStackTrace();
			return (StudentDTO) this.ExceptionObject(studentDto, e.toString());
		}
	}

	@Override
	public StudentDTO findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public StudentDTO printResult(String studentId) {
		StudentDTO studentDto = new StudentDTO();
		StudentEntity studentEntity = studentRepo.findById(studentId).orElse(null);
		List<RegisterEntity> registeredEntities = registerRepo.findAllByStudentId(studentId);
		List<SectionClassEntity> registeredSectionClassEntities = new ArrayList<SectionClassEntity>();
		for (RegisterEntity registeredEntity : registeredEntities) {
			SectionClassEntity sectionClassEntity = sectionClassRepo
					.findById(registeredEntity.getSectionClass().getId()).orElse(null);
			registeredSectionClassEntities.add(sectionClassEntity);
		}

		// Create document
		// Blank Document
		@SuppressWarnings("resource")
		XWPFDocument document = new XWPFDocument();

		// set orientation to landscape
		CTBody body = document.getDocument().getBody();
		if (!body.isSetSectPr()) {
			body.addNewSectPr();
		}
		CTSectPr section = body.getSectPr();
		if (!section.isSetPgSz()) {
			section.addNewPgSz();
		}
		CTPageSz pageSize = section.getPgSz();
		pageSize.setOrient(STPageOrientation.LANDSCAPE);
		// A4 = 595x842 / multiply 20 since BigInteger represents 1/20 Point
		pageSize.setW(BigInteger.valueOf(16840));
		pageSize.setH(BigInteger.valueOf(11900));

		// Write the Document in file system
		FileOutputStream out;
		try {
			File pathDoc = new File("src/main/resources/sources/student/" + studentId);
			if (!pathDoc.exists())
				pathDoc.mkdirs();

			out = new FileOutputStream(new File(pathDoc.getAbsolutePath() + "/result.docx"));

			// create Paragraph
			POIUtil.setRun(document.createParagraph().createRun(), 17, "STUDENT RESULT: ", true);

			// Student's information
			POIUtil.setRun(document.createParagraph().createRun(), 14, "Student's information: ", true);

			// create table
			XWPFTable table = document.createTable();
			// create first row
			XWPFTableRow tableRow = table.getRow(0);
			POIUtil.addParagraphToTableCell(tableRow.getCell(0), 14, "Student ID ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "First name ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Last name ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Address ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Birthday ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Gender ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Phone ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Picture ", true);

			tableRow = table.createRow();
			POIUtil.addParagraphToTableCell(tableRow.getCell(0), 14, studentEntity.getId(), false);
			POIUtil.addParagraphToTableCell(tableRow.getCell(1), 14, studentEntity.getFirstName(), false);
			POIUtil.addParagraphToTableCell(tableRow.getCell(2), 14, studentEntity.getLastName(), false);
			POIUtil.addParagraphToTableCell(tableRow.getCell(3), 14, studentEntity.getAddress(), false);
			Date birthday = studentEntity.getBirthday();
			String birthdayStr = birthday.getDate() + "/" + (birthday.getMonth() + 1) + "/"
					+ (birthday.getYear() + 1900);
			POIUtil.addParagraphToTableCell(tableRow.getCell(4), 14, birthdayStr, false);
			POIUtil.addParagraphToTableCell(tableRow.getCell(5), 14, studentEntity.getGender(), false);
			POIUtil.addParagraphToTableCell(tableRow.getCell(6), 14, studentEntity.getPhoneNumber(), false);
			POIUtil.addPictureToTableCell(tableRow.getCell(7),
					"src/main/resources/sources/student/" + studentEntity.getId() + "/avatar.png");

			// KẾT QUẢ
			POIUtil.setRun(document.createParagraph().createRun(), 14, " ", true);
			POIUtil.setRun(document.createParagraph().createRun(), 14, "Result: ", true);

			// create table
			XWPFTable table2 = document.createTable();
			// create first row
			XWPFTableRow tableRow2 = table2.getRow(0);
			POIUtil.addParagraphToTableCell(tableRow2.getCell(0), 14, "Course ", true);
			POIUtil.addParagraphToTableCell(tableRow2.addNewTableCell(), 14, "Score ", true);
			POIUtil.addParagraphToTableCell(tableRow2.addNewTableCell(), 14, "Result ", true);

			double totalScore = 0;
			for (RegisterEntity registeredEntity : registeredEntities) {
				tableRow2 = table2.createRow();
				SectionClassEntity sectionClassEntity = sectionClassRepo
						.findById(registeredEntity.getSectionClass().getId()).orElse(null);
				POIUtil.addParagraphToTableCell(tableRow2.getCell(0), 14, sectionClassEntity.getName(), false);
				POIUtil.addParagraphToTableCell(tableRow2.getCell(1), 14,
						String.valueOf(registeredEntity.getEndTermMark()), false);
				totalScore += registeredEntity.getEndTermMark();
				POIUtil.addParagraphToTableCell(tableRow2.getCell(2), 14, registeredEntity.getDescription(), false);
			}
			double avgScore = totalScore / registeredEntities.size();
			POIUtil.setRun(document.createParagraph().createRun(), 14, " ", true);
			DecimalFormat df = new DecimalFormat("#.00");
			POIUtil.setRun(document.createParagraph().createRun(), 14, "Average score: " + df.format(avgScore), true);
			if (avgScore < 5)
				POIUtil.setRun(document.createParagraph().createRun(), 14, "Result: Fail", true);
			else
				POIUtil.setRun(document.createParagraph().createRun(), 14, "Result: Pass", true);

			document.write(out);
			out.close();

			System.out.println("result.docx written successully");
			String linkDocFile = "/api/file/student/" + studentId + "/result.docx";
			studentDto.getListResult().add(linkDocFile);
			studentDto.setMessage("result.docx written successully.");
			return studentDto;

		} catch (IOException e) {
			e.printStackTrace();
			return (StudentDTO) this.ExceptionObject(studentDto, e.toString());
		}
	}

	@SuppressWarnings({ "resource" })
	@Override
	public StudentDTO printTimetable(String studentId) {
		StudentDTO studentDto = new StudentDTO();
		List<RegisterEntity> registerEntities = registerRepo.findAllByStudentId(studentId);

		// Create document
		// Blank Document
		XWPFDocument document = new XWPFDocument();

		// set orientation to landscape
		CTBody body = document.getDocument().getBody();
		if (!body.isSetSectPr()) {
			body.addNewSectPr();
		}
		CTSectPr section = body.getSectPr();
		if (!section.isSetPgSz()) {
			section.addNewPgSz();
		}
		CTPageSz pageSize = section.getPgSz();
		pageSize.setOrient(STPageOrientation.LANDSCAPE);
		// A4 = 595x842 / multiply 20 since BigInteger represents 1/20 Point
		pageSize.setW(BigInteger.valueOf(16840));
		pageSize.setH(BigInteger.valueOf(11900));

		// Write the Document in file system
		FileOutputStream out;
		try {
			File pathDoc = new File("src/main/resources/sources/student/" + studentId);
			if (!pathDoc.exists())
				pathDoc.mkdirs();

			out = new FileOutputStream(new File(pathDoc.getAbsolutePath() + "/timetable.docx"));

			// create Paragraph
			POIUtil.setRun(document.createParagraph().createRun(), 14, "TIMETABLE ", true);
			StudentEntity studentEntity = studentRepo.findById(studentId).orElse(null);
			POIUtil.setRun(document.createParagraph().createRun(), 14, "Student name: " + studentEntity.getFullname(),
					false);
			POIUtil.setRun(document.createParagraph().createRun(), 14, "Student ID: " + studentEntity.getId(), false);

			// create table
			XWPFTable table = document.createTable();
			// create first row
			XWPFTableRow tableRow = table.getRow(0);
			POIUtil.addParagraphToTableCell(tableRow.getCell(0), 14, "Monday ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Tuesday ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Wednesday ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Thursday ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Friday ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Saturday ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Sunday ", true);

			List<SectionClassEntity> sectionClassEntities = new ArrayList<SectionClassEntity>();
			for (RegisterEntity registerEntity : registerEntities)
				sectionClassEntities.add(registerEntity.getSectionClass());

			for (int weekday = 2; weekday <= 8; weekday++) {
				// get section classes of current day
				List<SectionClassEntity> curSectionClassEntities = new ArrayList<SectionClassEntity>();
				for (SectionClassEntity sectionClassEntity : sectionClassEntities) {
					if (sectionClassEntity.getWeekday() == weekday && weekday != 8)
						curSectionClassEntities.add(sectionClassEntity);
				}

				// sort section classes of current day
				for (int i = 0; i < curSectionClassEntities.size() - 1; i++) {
					for (int j = i + 1; j < curSectionClassEntities.size(); j++) {
						if (curSectionClassEntities.get(i).getStartTime().getTime() > curSectionClassEntities.get(j)
								.getStartTime().getTime()) {
							SectionClassEntity tmp = curSectionClassEntities.get(i);
							curSectionClassEntities.set(i, curSectionClassEntities.get(j));
							curSectionClassEntities.set(j, tmp);
						}
					}
				}

				for (int i = 0; i < curSectionClassEntities.size(); i++) {
					SectionClassEntity sectionClassEntity = curSectionClassEntities.get(i);
					if (table.getRow(i + 1) == null)
						table.createRow();

					tableRow = table.getRow(i + 1);
					StringBuilder content = new StringBuilder();
					content.append("Section class: " + sectionClassEntity.getName() + "\n");
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
					String startTime = simpleDateFormat.format(sectionClassEntity.getStartTime().getTime() - 7*3600*1000);
					String endTime = simpleDateFormat.format(sectionClassEntity.getEndTime().getTime() - 7*3600*1000);
					content.append("Start time: " + startTime + "\n");
					content.append("End time: " + endTime + "\n");
					content.append("Room: " + sectionClassEntity.getRoom() + "\n");
					content.append("Lecturer: " + sectionClassEntity.getLecturer().getFullname());
					POIUtil.addParagraphToTableCell(tableRow.getCell(weekday - 2), 14, content.toString(), false);
					POIUtil.splitBreakInCell(tableRow.getCell(weekday - 2));
				}

			}

			document.write(out);
			out.close();

			System.out.println("timetable.docx written successully");
			String linkDocFile = "/api/file/student/" + studentId + "/timetable.docx";
			studentDto.getListResult().add(linkDocFile);
			studentDto.setMessage("timetable.docx written successully.");
			return studentDto;

		} catch (IOException e) {
			e.printStackTrace();
			return (StudentDTO) this.ExceptionObject(studentDto, e.toString());
		}
	}

}
