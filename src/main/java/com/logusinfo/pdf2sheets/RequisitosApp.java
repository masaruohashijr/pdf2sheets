package com.logusinfo.pdf2sheets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.logusinfo.pdf2sheets.model.Funcionalidade;
import com.logusinfo.pdf2sheets.model.Subsistema;
import com.logusinfo.pdf2sheets.service.PdfParser;

/**
 * Hello world!
 *
 */
public class RequisitosApp 
{
    public static void main( String[] args )
    {
    	try (PDDocument document = PDDocument.load(new File("Rondônia - EDITAL  PE 022-2020 - SIGEF.pdf"))) {
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();
                tStripper.setStartPage(72);
                tStripper.setEndPage(126);
                String pdfFileInText = tStripper.getText(document);
                String lines[] = pdfFileInText.split("\\r?\\n");
                
                List<Subsistema> subsistemas = new PdfParser(lines).parse();
                for (Subsistema subsistema : subsistemas) {
					System.out.println(subsistema.getNome());
					List<Funcionalidade> funcionalidades = subsistema.getFuncionalidades();
					for (Funcionalidade funcionalidade : funcionalidades) {
						System.out.println("\t\t"+funcionalidade.getCasoDeUso());
					}
				}
            }

        } catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
