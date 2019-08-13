package com.onejane.ali.oss.service.impl;

import com.onejane.ali.oss.base.util.PdfTransformWordUtils;
import com.onejane.ali.oss.base.util.UploadUtils;
import com.onejane.ali.oss.base.util.UuidUtils;
import com.onejane.ali.oss.refer.FilesEnum;
import com.onejane.ali.oss.service.AsyncService;
import com.onejane.ali.oss.vo.TransferResultVo;
import com.onejane.multisource.entity.PdfTransferWordEntity;
import com.onejane.multisource.mapper.basemapper.PdfTransferWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @ClassName AsyncServiceImpl
 * @Description TODO
 * @Author xumin zhao
 * @Date 2019/7/3 18:01
 * @Version 1.0
 **/
@Service
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private PdfTransferWordMapper pdfTransferWordMapper;

    @Override
    public TransferResultVo pdfTransferWordAndUpload(MultipartFile file, File filepdf, String path, PdfTransferWordEntity entity) {
        TransferResultVo transferResultVo = new TransferResultVo();
        try {
            String wordFileName = UuidUtils.createUUID();
            File fileWord =new File(wordFileName+ FilesEnum.DOC.getDesc());
            fileWord = PdfTransformWordUtils.pdfTransferWordSpire(file, filepdf, fileWord, wordFileName+FilesEnum.DOC.getDesc());
            InputStream is = new FileInputStream(fileWord);
            String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".pdf"));
            String url = UploadUtils.uploadDoc(FilesEnum.DOC.getDesc(), is, fileName);
            transferResultVo.setUrl(url);
            entity.setWordUrl(url);
            pdfTransferWordMapper.updateWord(entity);
            if(fileWord.exists()) {
                PdfTransformWordUtils.deleteFile(fileWord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transferResultVo;
    }
}
