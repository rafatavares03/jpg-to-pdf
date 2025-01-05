package org.example.PDF;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDF {
    private final PDDocument pdfFile ;
    private final String path;
    private final String absolutePath;
    private final String fileName;
    private final ArrayList<PDPage> pages;

    public PDF(String path, String file_name){
        this.path = path;
        this.fileName = (file_name.endsWith(".pdf")) ? file_name.replace(".pdf", " ") : file_name;
        this.absolutePath = path.concat("\\" + this.fileName + ".pdf");
        this.pdfFile = new PDDocument();
        this.pages = new ArrayList<PDPage>();
    }

    public String getPath(){
        return this.path;
    }

    public String getFileName(){
        return this.fileName;
    }

    public String getAbsolutePath(){
        return this.absolutePath;
    }

    public void save() {
        try{
            this.pdfFile.save(this.absolutePath);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public void close(){
        try {
            this.pdfFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void imageSize(PDRectangle pageSize, float imgSizeX, float imgSizeY){
        float pageSizeX = pageSize.getWidth();
        float pageSizeY = pageSize.getHeight();
        float[] size = new float[2];
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

    public PDPage addNewPage(){
        PDPage page = new PDPage();
        this.pdfFile.addPage(page);
        int pageIndex = this.pdfFile.getNumberOfPages()-1;
        page = this.pdfFile.getPage(pageIndex);
        return page;
    }

    private void  drawImage(PDFImage image, PDPage page) {
        try{
            PDPageContentStream contentStream = new PDPageContentStream(this.pdfFile, page);
            contentStream.drawImage(image.getImage(), 0, 0, image.getSize().getWidth(), image.getSize().getHeight());
            contentStream.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addImagePage(String imagePath) {
        PDPage page = addNewPage();
        PDFImage image = new PDFImage();
        boolean success = image.setImage(imagePath, this.pdfFile);
        if(success) {
            image.setSize(page.getMediaBox());
            page.setMediaBox(image.getSize());
            drawImage(image, page);
            this.pages.add(page);
            save();
        }
    }

    public void generate() {

    }
}
