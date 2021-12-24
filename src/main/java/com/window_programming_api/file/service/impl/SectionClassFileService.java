package com.window_programming_api.file.service.impl;

import com.window_programming_api.dto.SectionClassDTO;
import com.window_programming_api.entity.SectionClassEntity;
import com.window_programming_api.file.service.ISectionClassFileService;
import com.window_programming_api.repository.SectionClassRepository;
import com.window_programming_api.utils.MyFileUtil;
import com.window_programming_api.utils.POIUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class SectionClassFileService extends BaseFileService implements ISectionClassFileService{

	@Autowired
	private SectionClassRepository sectionClassRepo;
	
	@SuppressWarnings("resource")
	@Override
	public SectionClassDTO printSectionClassList() {
		SectionClassDTO sectionClassDto = new SectionClassDTO();
		List<SectionClassEntity> sectionClassEntities = sectionClassRepo.findAll();

		// Create document
		// Blank Document
		XWPFDocument document = new XWPFDocument();

		// Write the Document in file system
		FileOutputStream out;
		try {
			File pathDoc = new File("src/main/resources/sources/section_class");
			if (!pathDoc.exists())
				pathDoc.mkdirs();

			out = new FileOutputStream(new File(pathDoc.getAbsolutePath() + "/list_of_section_class.docx"));

			// create Paragraph
			POIUtil.setRun(document.createParagraph().createRun(), 14, "DANH SÁCH CÁC LỚP HỌC PHẦN: ", true);

			// create table
			XWPFTable table = document.createTable();
			// create first row
			XWPFTableRow tableRow = table.getRow(0);
			POIUtil.addParagraphToTableCell(tableRow.getCell(0), 14, "Course ID ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Label ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Period ", true);
			POIUtil.addParagraphToTableCell(tableRow.addNewTableCell(), 14, "Description ", true);

			for (int i = 0; i < sectionClassEntities.size(); i++) {
				// create row after
				tableRow = table.createRow();
				POIUtil.addParagraphToTableCell(tableRow.getCell(0), 14, sectionClassEntities.get(i).getId(), false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(1), 14, sectionClassEntities.get(i).getName(), false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(2), 14, sectionClassEntities.get(i).getPeriod().toString(),
						false);
				POIUtil.addParagraphToTableCell(tableRow.getCell(3), 14, sectionClassEntities.get(i).getDescription(), false);
			}

			document.write(out);
			out.close();

			System.out.println("list_of_section_class.docx written successully");
			String linkDocFile = "/api/file/section_class/list_of_section_class.docx";
			sectionClassDto.getListResult().add(linkDocFile);
			sectionClassDto.setMessage("list_of_section_class.docx written successully.");
			return sectionClassDto;

		} catch (IOException e) {
			e.printStackTrace();
			return (SectionClassDTO) this.ExceptionObject(sectionClassDto, e.toString());
		}
	}

	@Override
	public byte[] getFile(String source) {
		String fileName = source.split("/api/file/section_class/")[1];
		File rootPath = new File(MyFileUtil.removeDoubleSlash("src/main/resources/sources/section_class/" + fileName));
		return MyFileUtil.getByteBySource(rootPath.getAbsolutePath());
	}

	
}
