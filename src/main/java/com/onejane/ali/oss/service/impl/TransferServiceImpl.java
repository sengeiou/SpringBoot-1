package com.onejane.ali.oss.service.impl;

import com.onejane.ali.oss.base.util.StringUtils;
import com.onejane.ali.oss.base.util.UploadUtils;
import com.onejane.ali.oss.base.util.UuidUtils;
import com.onejane.ali.oss.refer.FilesEnum;
import com.onejane.ali.oss.service.AsyncService;
import com.onejane.ali.oss.service.TransferService;
import com.onejane.ali.oss.vo.TransferQueryVo;
import com.onejane.ali.oss.vo.TransferResultVo;
import com.onejane.multisource.entity.PdfTransferWordEntity;
import com.onejane.multisource.mapper.basemapper.PdfTransferWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * @ClassName TransferServiceImpl
 * @Description TODO
 * @Author xumin zhao
 * @Date 2019/6/24 16:33
 * @Version 1.0
 **/
@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AsyncService asyncService;
    @Autowired
    private PdfTransferWordMapper pdfTransferWordMapper;

    @Override
    public TransferResultVo pdfTransferWord(MultipartFile file, String path) {
        TransferResultVo transferResultVo = new TransferResultVo();
        try {
            MultipartFile pdfFile=file;
            String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".pdf"));
            InputStream is = file.getInputStream();
            String url = UploadUtils.uploadPdf(FilesEnum.PDF.getDesc(), is, fileName);
            transferResultVo.setId(UuidUtils.createUUID());
            PdfTransferWordEntity entity = new PdfTransferWordEntity();
            entity.setId(transferResultVo.getId());
            entity.setPdfUrl(url);
            pdfTransferWordMapper.insertPdf(entity);
            File filePdf =File.createTempFile(UuidUtils.createUUID(),FilesEnum.PDF.getDesc());
            pdfFile.transferTo(filePdf);
            asyncService.pdfTransferWordAndUpload(file, filePdf, path, entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transferResultVo;
    }

    @Override
    public String downLoadWord(String id) {
        TransferQueryVo transferQueryVo = new TransferQueryVo();
        transferQueryVo.setId(id);
        PdfTransferWordEntity entity = pdfTransferWordMapper.findUrl(transferQueryVo);
        if (entity != null && !StringUtils.isEmpty(entity.getWordUrl())) {
            return entity.getWordUrl();
        }
        return null;
    }

    @Override
    public String wordUrl(String id) {
        TransferQueryVo transferQueryVo = new TransferQueryVo();
        transferQueryVo.setId(id);
        PdfTransferWordEntity entity = pdfTransferWordMapper.findUrl(transferQueryVo);
        if (entity != null && !StringUtils.isEmpty(entity.getWordUrl())) {
            return entity.getWordUrl();
        }
        return null;
    }

    @Override
    public String pdfUrl(String id) {
        TransferQueryVo queryVo = new TransferQueryVo();
        queryVo.setId(id);
        PdfTransferWordEntity entity = pdfTransferWordMapper.findUrl(queryVo);
        if (entity != null && !StringUtils.isEmpty(entity.getPdfUrl())) {
            return entity.getPdfUrl();
        }
        return null;


    }
}
