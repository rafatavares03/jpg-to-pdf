package org.example;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;

public class PDF {
    private PDDocument pdf_file;
    private String path;
    private String absolutePath;
    private String file_name;

    public PDF(String path, String file_name){
        setPath(path);
        setFile_name(file_name);
        setAbsolutePath(path, file_name);
        pdf_file = new PDDocument();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFile_name(String file_name) {
        if(file_name.endsWith(".pdf")){
            file_name = file_name.replace(".pdf", "");
        }
        this.file_name = file_name;
    }

    public void setAbsolutePath(String path, String file_name){
        this.absolutePath = path.concat("\\" + file_name + ".pdf");
    }

    public String getPath(){
        return this.path;
    }

    public String getFile_name(){
        return this.file_name;
    }

    public String getAbsolutePath(){
        return this.absolutePath;
    }

    public void save() {
        try{
            pdf_file.save(absolutePath);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public void close(){
        try {
            this.pdf_file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void imageSize(PDRectangle pageSize, float imgSizeX, float imgSizeY){
        float pageSizeX = pageSize.getWidth();
        float pageSizeY = pageSize.getHeight();
        float size[] = new float[2];
        if((imgSizeX < pageSizeX) && (imgSizeY < pageSizeY)) {
            return;
        } else {
            if(imgSizeX > imgSizeY) {
                size[0] = pageSizeX;
                size[1] = (imgSizeY * pageSizeX) / imgSizeX;
            } else if(imgSizeY > imgSizeX) {
                size[0] = (imgSizeX * pageSizeY) / imgSizeY;
                size[1] = pageSizeY;
            } else {
                size[0] = size[1] = pageSizeX;
            }
        }
        pageSize.setUpperRightX(size[0]);
        pageSize.setLowerLeftX(0);
        pageSize.setUpperRightY((size[1]));
        pageSize.setLowerLeftY(0);
    }

    public void addImagePage(String imagePath) {
        PDPage page = new PDPage();
        pdf_file.addPage(page);
        int pageIndex = pdf_file.getNumberOfPages()-1;
        page = pdf_file.getPage(pageIndex);
        PDRectangle pageSize = page.getMediaBox();
        try {
            PDImageXObject image = PDImageXObject.createFromFile(imagePath, pdf_file);
            PDPageContentStream contentStream = new PDPageContentStream(pdf_file, page);
            imageSize(pageSize, image.getWidth(), image.getHeight());
            page.setMediaBox(pageSize);
            contentStream.drawImage(
                    image,
                    0,
                    0,
                    pageSize.getWidth(),
                    pageSize.getHeight()
            );
            contentStream.close();
        } catch (IOException e) {
            throw new RuntimeException("It was not possible to find this image.");
        }
        save();
    }
}
