package parsingFilesPackage;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import static org.assertj.core.api.Assertions.assertThat;

public class ParsingFiles {
    ClassLoader cl = ParsingFiles.class.getClassLoader();

    @Test
    void zipParsingTest() throws Exception {

        try (
                InputStream resource = cl.getResourceAsStream("files.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {

                if (entry.getName().endsWith(".pdf")) {
                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("small demonstration");
                } else if (entry.getName().contains(".xls")) {
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(1).getCell(2).getStringCellValue()).contains("Barry French");
                } else if (entry.getName().endsWith(".csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(1)[0]).contains("user");
                }

            }
        }
    }
}
