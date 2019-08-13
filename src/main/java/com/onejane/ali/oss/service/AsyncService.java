package com.onejane.ali.oss.service;

import com.onejane.ali.oss.vo.TransferResultVo;
import com.onejane.multisource.entity.PdfTransferWordEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @ClassName AsyncService
 * @Description TODO
 * @Author xumin zhao
 * @Date 2019/7/3 17:59
 * @Version 1.0
 **/

public interface AsyncService {

    @Async(value = "asyncServiceExecutor")
    TransferResultVo pdfTransferWordAndUpload(MultipartFile file, File filepdf, String path, PdfTransferWordEntity entity);
}
