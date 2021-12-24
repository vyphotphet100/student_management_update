package com.window_programming_api.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import java.io.FileInputStream;
import java.io.IOException;

public class POIUtil {
	public static void setRun(XWPFRun run, int fontSize, String text, boolean bold) {
		run.setFontFamily("Times New Roman");
		run.setFontSize(fontSize);
		// run.setColor(colorRGB);
		run.setText(text);
		run.setBold(bold);
		//if (addBreak)
		//	run.addBreak();
	}
	
	public static void addParagraphToTableCell(XWPFTableCell tableCell, int fontSize, String text, boolean bold) {
		POIUtil.setRun(tableCell.addParagraph().createRun(), fontSize, text, bold);
		tableCell.removeParagraph(0);
	}
	
	public static void addPictureToTableCell(XWPFTableCell tableCell, String picturePath){
		// table is a XWPFTable object instantiated earlier in the code
		XWPFParagraph paragraph = tableCell.addParagraph();
		XWPFRun run = paragraph.createRun();
		FileInputStream fis;
		try {
			fis = new FileInputStream(picturePath);
			run.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(150));
			//System.out.println(picture.getCTPicture());
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tableCell.removeParagraph(0);
	}
	
	public static void splitBreakInCell(XWPFTableCell cell) {
		if (cell.getText() != null && cell.getText().contains("\n")) {
			for (XWPFParagraph p : cell.getParagraphs()) {
				for (XWPFRun run : p.getRuns()) {// XWPFRun object defines a text area with a set of public properties
					if (run.getText(0) != null && run.getText(0).contains("\n")) {
						String[] lines = run.getText(0).split("\n");
						if (lines.length > 0) {
							run.setText(lines[0], 0); // set first line into XWPFRun
							for (int i = 1; i < lines.length; i++) {
								// add break and insert new text
								run.addBreak();// interrupt
								// run.addCarriageReturn();//carriage return, but does not work
								run.setText(lines[i]);
							}
						}
					}
				}
			}
		}
	}
}
