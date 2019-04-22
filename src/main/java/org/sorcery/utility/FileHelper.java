package org.sorcery.utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class FileHelper {

    public static String getFileContentAsString(String filename) throws IOException {
        Resource resource = new ClassPathResource(filename);
        InputStream inputStream = resource.getInputStream();
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, Charset.defaultCharset());
        return writer.toString();
    }

    public static InputStream getFileContentAsStream(String filename) throws IOException {
        Resource resource = new ClassPathResource(filename);
        InputStream inputStream = resource.getInputStream();
        return inputStream;
    }

}