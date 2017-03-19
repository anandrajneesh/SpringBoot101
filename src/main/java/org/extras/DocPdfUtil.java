package org.extras;

import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.io3.Load3;
import org.docx4j.openpackaging.io3.stores.PartStore;
import org.docx4j.openpackaging.io3.stores.ZipPartStore;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anand_Rajneesh on 3/8/2017.
 */
public class DocPdfUtil {

    public static void createPdfFromDoc(String documentPath, Map<String, Object> replaceVariables, String outputPath) throws Exception {
        InputStream is = new FileInputStream(documentPath);
        PartStore partLoader = new ZipPartStore(is);
        Load3 loader = new Load3(partLoader);
        WordprocessingMLPackage mlPackage = (WordprocessingMLPackage) loader.get();
        VariablePrepare.prepare(mlPackage);
        mlPackage.getMainDocumentPart().variableReplace(localize(replaceVariables));
        org.docx4j.convert.out.pdf.PdfConversion c = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(mlPackage);
        OutputStream os = new FileOutputStream(outputPath);
        c.output(os, new PdfSettings());
        is.close();
        os.close();
    }

    private static HashMap<String, String> localize(Map<String, Object> parameters) {
        HashMap<String, String> newMap = new HashMap<String, String>();
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            newMap.put(entry.getKey(), entry.getValue().toString());
        }
        return newMap;
    }


}
