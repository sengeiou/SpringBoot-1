package com.onejane.multisource.mapper.basemapper;

import com.onejane.ali.oss.vo.TransferQueryVo;
import com.onejane.multisource.entity.PdfTransferWordEntity;
import com.onejane.multisource.mapper.MyMapper;

/**
 * @ClassName PdfTransferWordMapper
 * @Description TODO
 * @Author xumin zhao
 * @Date 2019/7/8 14:28
 * @Version 1.0
 **/
public interface PdfTransferWordMapper extends MyMapper<PdfTransferWordEntity> {

    void insertPdf(PdfTransferWordEntity pdfTransferWordEntity);

    void updateWord(PdfTransferWordEntity pdfTransferWordEntity);

    PdfTransferWordEntity findUrl(TransferQueryVo transferQueryVo);
}
