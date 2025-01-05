package org.example.PDF;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PDFImage {
    private PDImageXObject image;
    final private PDRectangle size;

    public PDFImage() {
        this.size = new PDRectangle();
        this.size.setLowerLeftX(0);
        this.size.setLowerLeftY(0);
    }

    public void setSize(PDRectangle pageSize) {
        if(this.image.getWidth() < pageSize.getWidth() && this.image.getHeight() < pageSize.getHeight()) {
            this.size.setUpperRightX(pageSize.getWidth());
            this.size.setUpperRightY(pageSize.getHeight());
            return;
        }
        if(this.image.getWidth() > this.image.getHeight()) {
            this.size.setUpperRightX(pageSize.getWidth());
            this.size.setUpperRightY( (this.image.getHeight() * pageSize.getWidth()) / this.image.getWidth());
            return;
        }
        if(this.image.getWidth() < this.image.getHeight()) {
            this.size.setUpperRightX((this.image.getWidth() * pageSize.getHeight()) / this.image.getHeight());
            this.size.setUpperRightY(pageSize.getHeight());
            return;
        }
        this.size.setUpperRightX(pageSize.getWidth());
        this.size.setUpperRightY(pageSize.getWidth());

    }

    public boolean setImage(String image_path, PDDocument file) {
        try {
            this.image = PDImageXObject.createFromFile(image_path, file);
            return true;
        } catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    public PDImageXObject getImage() {
        return image;
    }

    public PDRectangle getSize() {
        return size;
    }
}
