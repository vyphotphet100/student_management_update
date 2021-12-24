package com.window_programming_api.file.service.impl;

import com.window_programming_api.dto.RegisterDTO;
import com.window_programming_api.entity.RegisterEntity;
import com.window_programming_api.entity.SectionClassEntity;
import com.window_programming_api.file.service.IRegisterFileService;
import com.window_programming_api.repository.RegisterRepository;
import com.window_programming_api.repository.SectionClassRepository;
import com.window_programming_api.utils.MyFileUtil;
import com.window_programming_api.utils.POIUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.util.Date;
import java.util.List;

@Service
public class RegisterFileService extends BaseFileService implements IRegisterFileService {

	@Autowired
	private RegisterRepository registerRepo;

	@Autowired
	private SectionClassRepository sectionClassRepo;

	@SuppressWarnings("resource")
	@Override
	public RegisterDTO printRegisterList() {
		RegisterDTO registerDto = new RegisterDTO();
		List<RegisterEntity> registerEntities = registerRepo.findAll();

		// Create document
		// Blank Document
		XWPFDocument document = new XWPFDocument();

		// Write the Document in file system
		FileOutputStream out;
		try {
			File pathDoc = new File("src/main/resources/sources/register");
			if (!pathDoc.exists())
				pathDoc.mkdirs();

			out = new FileOutputStream(new File(pathDoc.getAbsolutePath() + "/list_of_register.docx"));

			// create Paragraph
			POIUtil.setRun(document.createParagraph().createRun(), 14, "DANH SÁCH ĐIỂM CỦA SINH VIÊN: ", true);

			// create table
			XWPFTable table = document.createTable();
			// create first row
			XWPFTableRow tableRow = table.getRow(0);
			POIUtil.addParagraphToTableCell(tableRow.getCell(0), 14, "Student ID ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Course ID ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Course label ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Score ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Description ", true);

			for (int i = 0; i < registerEntities.size(); i++) {
				// create row after
				tableRow = table.createRow();
				POIUtil.addParagraphToTableCell(tableRow.getCell(0), 14, registerEntities.get(i).getStudent().getId(),
						false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(1), 14,
						registerEntities.get(i).getSectionClass().getId(), false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(2), 14,
						registerEntities.get(i).getSectionClass().getName(), false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(3), 14,
						String.valueOf(registerEntities.get(i).getEndTermMark()), false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(4), 14, registerEntities.get(i).getDescription(),
						false);
			}

			document.write(out);
			out.close();

			System.out.println("list_of_register.docx written successully");
			String linkDocFile = "/api/file/register/list_of_register.docx";
			registerDto.getListResult().add(linkDocFile);
			registerDto.setMessage("list_of_register.docx written successully.");
			return registerDto;

		} catch (IOException e) {
			e.printStackTrace();
			return (RegisterDTO) this.ExceptionObject(registerDto, e.toString());
		}
	}

	@Override
	public byte[] getFile(String source) {
		String fileName = source.split("/api/file/register/")[1];
		File rootPath = new File(MyFileUtil.removeDoubleSlash("src/main/resources/sources/register/" + fileName));
		return MyFileUtil.getByteBySource(rootPath.getAbsolutePath());
	}

	@SuppressWarnings({ "resource", "deprecation" })
	@Override
	public RegisterDTO printRegisterBySectionClass(String sectionClassId) {
		RegisterDTO registerDto = new RegisterDTO();
		List<RegisterEntity> registerEntities = registerRepo.findAllBySectionClassId(sectionClassId);

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
			File pathDoc = new File("src/main/resources/sources/register/section_class_id/" + sectionClassId);
			if (!pathDoc.exists())
				pathDoc.mkdirs();

			out = new FileOutputStream(new File(pathDoc.getAbsolutePath() + "/list_of_register.docx"));

			// create Paragraph
			POIUtil.setRun(document.createParagraph().createRun(), 14, "TRANSCRIPT OF STUDENTS: ", true);
			SectionClassEntity sectionClassEntity = sectionClassRepo.findById(sectionClassId).orElse(null);
			POIUtil.setRun(document.createParagraph().createRun(), 14,
					"Section class name: " + sectionClassEntity.getName(), false);
			POIUtil.setRun(document.createParagraph().createRun(), 14,
					"Section class ID: " + sectionClassEntity.getId(), false);
			POIUtil.setRun(document.createParagraph().createRun(), 14,
					"Lecturer: " + sectionClassEntity.getLecturer().getFullname(), false);

			// create table
			XWPFTable table = document.createTable();
			// create first row
			XWPFTableRow tableRow = table.getRow(0);
			POIUtil.addParagraphToTableCell(tableRow.getCell(0), 14, "Student ID ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "First name ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Last name ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Birthday ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Mid-term score ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "End-term score ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Average score ", true);

			for (int i = 0; i < registerEntities.size(); i++) {
				// create row after
				tableRow = table.createRow();
				POIUtil.addParagraphToTableCell(tableRow.getCell(0), 14, registerEntities.get(i).getStudent().getId(),
						false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(1), 14,
						registerEntities.get(i).getStudent().getFirstName(), false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(2), 14,
						registerEntities.get(i).getStudent().getLastName(), false);
				Date birthday = registerEntities.get(i).getStudent().getBirthday();
				String birthdayStr = birthday.getDate() + "/" + (birthday.getMonth() + 1) + "/"
						+ (birthday.getYear() + 1900);
				POIUtil.addParagraphToTableCell(tableRow.getCell(3), 14, birthdayStr, false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(4), 14,
						String.valueOf(registerEntities.get(i).getMidTermMark()), false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(5), 14,
						String.valueOf(registerEntities.get(i).getEndTermMark()), false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(6), 14, String.valueOf(
						(registerEntities.get(i).getEndTermMark() + registerEntities.get(i).getMidTermMark()) / 2),
						false);
			}

			document.write(out);
			out.close();

			System.out.println("list_of_register.docx written successully");
			String linkDocFile = "/api/file/register/section_class_id/" + sectionClassId + "/list_of_register.docx";
			registerDto.getListResult().add(linkDocFile);
			registerDto.setMessage("list_of_register.docx written successully.");
			return registerDto;

		} catch (IOException e) {
			e.printStackTrace();
			return (RegisterDTO) this.ExceptionObject(registerDto, e.toString());
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public RegisterDTO printRegisterBySectionClass2(String sectionClassId) {
		RegisterDTO registerDto = new RegisterDTO();
		List<RegisterEntity> registerEntities = registerRepo.findAllBySectionClassId(sectionClassId);

		// Create document
		// Blank Document
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Transcript");

		// Write the Document in file system
		FileOutputStream out;
		try {
			File pathExcel = new File("src/main/resources/sources/register/section_class_id/" + sectionClassId);
			if (!pathExcel.exists())
				pathExcel.mkdirs();

			out = new FileOutputStream(new File(pathExcel.getAbsolutePath() + "/list_of_register.xlsx"));
			int rowCount = 0;
			
			Row row0 = sheet.createRow(rowCount++);
			Cell cell0 = row0.createCell(0);
			XSSFFont font= workbook.createFont();
			font.setBold(true);
			CellStyle style = workbook.createCellStyle();
			style.setFont(font);
			cell0.setCellStyle(style);
			cell0.setCellValue("TRANSCRIPT OF STUDENTS ");
			
			SectionClassEntity sectionClassEntity = sectionClassRepo.findById(sectionClassId).orElse(null);
			sheet.createRow(rowCount++).createCell(0).setCellValue("Section class name: " + sectionClassEntity.getName());
			sheet.getRow(rowCount-1).createCell(5).setCellValue("Section class ID: " + sectionClassEntity.getId());
			sheet.createRow(rowCount++).createCell(0).setCellValue("Lecturer: " + sectionClassEntity.getLecturer().getFullname());
			
			rowCount++;
			Row headerRow = sheet.createRow(rowCount++);
			int cellCount = 0;
			Cell headerCell = headerRow.createCell(cellCount++);
			headerCell.setCellStyle(style);
			headerCell.setCellValue("Student ID");

			headerCell = headerRow.createCell(cellCount++);
			headerCell.setCellStyle(style);
			headerCell.setCellValue("First name");

			headerCell = headerRow.createCell(cellCount++);
			headerCell.setCellStyle(style);
			headerCell.setCellValue("Last name");

			headerCell = headerRow.createCell(cellCount++);
			headerCell.setCellStyle(style);
			headerCell.setCellValue("Birthday");

			headerCell = headerRow.createCell(cellCount++);
			headerCell.setCellStyle(style);
			headerCell.setCellValue("Mid-term mark");

			headerCell = headerRow.createCell(cellCount++);
			headerCell.setCellStyle(style);
			headerCell.setCellValue("End-term mark");

			headerCell = headerRow.createCell(cellCount++);
			headerCell.setCellStyle(style);
			headerCell.setCellValue("Average mark");

			for (int i = 0; i < registerEntities.size(); i++) {
				Row row = sheet.createRow(rowCount++);

				int columnCount = 0;
				Cell cell = row.createCell(columnCount++);
				cell.setCellValue(registerEntities.get(i).getStudent().getId());

				cell = row.createCell(columnCount++);
				cell.setCellValue(registerEntities.get(i).getStudent().getFirstName());

				cell = row.createCell(columnCount++);
				cell.setCellValue(registerEntities.get(i).getStudent().getLastName());

				cell = row.createCell(columnCount++);
				Date birthday = registerEntities.get(i).getStudent().getBirthday();
				String birthdayStr = birthday.getDate() + "/" + (birthday.getMonth() + 1) + "/"
						+ (birthday.getYear() + 1900);
				cell.setCellValue(birthdayStr);

				cell = row.createCell(columnCount++);
				cell.setCellValue(String.valueOf(registerEntities.get(i).getMidTermMark()));

				cell = row.createCell(columnCount++);
				cell.setCellValue(String.valueOf(registerEntities.get(i).getEndTermMark()));

				cell = row.createCell(columnCount++);
				cell.setCellValue(String.valueOf(
						(registerEntities.get(i).getEndTermMark() + registerEntities.get(i).getMidTermMark()) / 2));
			}

			FileOutputStream outputStream = out;
            workbook.write(outputStream);
            workbook.close();

			System.out.println("list_of_register.xlsx written successully");
			String linkExcelFile = "/api/file/register/section_class_id/" + sectionClassId + "/list_of_register.xlsx";
			registerDto.getListResult().add(linkExcelFile);
			registerDto.setMessage("list_of_register.xlsx written successully.");
			return registerDto;

		} catch (IOException e) {
			e.printStackTrace();
			return (RegisterDTO) this.ExceptionObject(registerDto, e.toString());
		}
	}

}
