package com.onejane.ali.oss.service;

import com.onejane.ali.oss.vo.TransferResultVo;
import org.springframework.web.multipart.MultipartFile;


/**
 * @ClassName TransferService
 * @Description TODO
 * @Author xumin zhao
 * @Date 2019/6/24 16:33
 * @Version 1.0
 **/
public interface TransferService {

    TransferResultVo pdfTransferWord(MultipartFile file, String path);

    String downLoadWord(String id);

    String pdfUrl(String id);

    String wordUrl(String id);
}
