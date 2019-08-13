package com.onejane.multisource.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName PdfTransferWordEntity
 * @Description TODO
 * @Author xumin zhao
 * @Date 2019/7/8 14:24
 * @Version 1.0
 **/
@Table(name = "pdf_transfer_word")
public class PdfTransferWordEntity implements Serializable {

    private static final long serialVersionUID = -460160636524267757L;

    @Id
    private String id;

    @Column(name = "pdf_url")
    private String pdfUrl;

    @Column(name = "word_url")
    private String wordUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getWordUrl() {
        return wordUrl;
    }

    public void setWordUrl(String wordUrl) {
        this.wordUrl = wordUrl;
    }
}
