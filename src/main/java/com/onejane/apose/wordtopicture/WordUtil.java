package com.onejane.apose.wordtopicture;


import com.aspose.words.Document;
import com.aspose.words.ImageSaveOptions;
import com.aspose.words.SaveFormat;
import com.onejane.apose.util.CompactAlgorithm;
import com.onejane.apose.util.MergeImage;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * word文档（doc，docx）转换pdf/图片，
 * 图片转换base64用于前端在线预览
 * mvn install:install-file -DgroupId=com.aspose.words -DartifactId=aspose-words -Dversion=19.2 -Dpackaging=jar -Dfile=aspose-words-19.2-jdk16.jar
 */

public class WordUtil {


	/**
	 * word 转每页pdf
	 * @param wordfile
	 * @return
	 * @throws Exception
	 */
	public static String parseFileToBase64_PNG1(String wordfile) throws Exception {


		// 声明一个
		InputStream inputStream = new FileInputStream(wordfile);



		//文件 获取文件名字
		File file = new File(wordfile);
		String name = file.getName();
		//截取不带后缀名的字段
		String fileName = name.substring(0, name.lastIndexOf("."));
		//文件上传路径
		String parent = file.getParent();

		//创建同名文件夹
		new File(parent+"/"+ fileName).mkdir();

		List<BufferedImage> bufferedImages = wordToImg1(inputStream);

		for (int i = 0; i < bufferedImages.size(); i++){
			ImageIO.write(bufferedImages.get(i), "png", new File(parent +"/"+ fileName +"/"+ "第"+ i +"页" + fileName + ".png"));
		}


		//压缩同名文件夹
		File f = new File(parent  +"/" + fileName );
		new CompactAlgorithm(new File( parent+ "/",f.getName()+".zip")).zipFiles(f);

		//关闭流
		inputStream.close();


		return "转换成功";
	}

	/**
	 * @Description: word和txt文件转换图片
	 */
	private static List<BufferedImage> wordToImg1(InputStream inputStream) throws Exception {


		try {
			Document doc = new Document(inputStream);

			ImageSaveOptions options = new ImageSaveOptions(SaveFormat.PNG);
			options.setPrettyFormat(true);
			options.setUseAntiAliasing(true);
			options.setUseHighQualityRendering(true);
			int pageCount = doc.getPageCount();

			List<BufferedImage> imageList = new ArrayList<BufferedImage>();
			for (int i =  0; i < pageCount; i++) {
				OutputStream output = new ByteArrayOutputStream();
				options.setPageIndex(i);

				doc.save(output, options);

				ImageInputStream imageInputStream = ImageIO.createImageInputStream(parse(output));

				imageList.add(ImageIO.read(imageInputStream));
			}
			return imageList;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}



	// 将word 转化为图片一张
	public static String parseFileToBase64_PNG(String wordfile) throws Exception {

		//文件流
		InputStream inputStream = new FileInputStream(wordfile);
		//文件 获取文件名字
		File file = new File(wordfile);
		String name = file.getName();
		//截取不带后缀名的字段
		String fileName = name.substring(0, name.lastIndexOf("."));

		//文件上传路径
		String parent = file.getParent();

		List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();
		BufferedImage image = null;
			bufferedImages = wordToImg(inputStream);
			image = MergeImage.mergeImage(false, bufferedImages);

		boolean png = ImageIO.write(image, "png", new File(parent + "/" + fileName + ".png"));// 写入流中


		if(png == false){
			return "转换失败";
		}

		//关闭流
		inputStream.close();
		return "转换成功";
	}



	/**
	 * @Description: word和txt文件转换图片
	 */
	private static List<BufferedImage> wordToImg(InputStream inputStream) throws Exception {


		try {

			Document doc = new Document(inputStream);
			ImageSaveOptions options = new ImageSaveOptions(SaveFormat.PNG);
			options.setPrettyFormat(true);
			options.setUseAntiAliasing(true);
			options.setUseHighQualityRendering(true);
			int pageCount = doc.getPageCount();

			List<BufferedImage> imageList = new ArrayList<BufferedImage>();
			for (int i = 0; i < pageCount; i++) {
				OutputStream output = new ByteArrayOutputStream();
				options.setPageIndex(i);

				doc.save(output, options);
				ImageInputStream imageInputStream = ImageIO.createImageInputStream(parse(output));
				imageList.add(ImageIO.read(imageInputStream));
			}
			return imageList;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}



	// outputStream转inputStream
	public static ByteArrayInputStream parse(OutputStream out) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos = (ByteArrayOutputStream) out;
		ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
		return swapStream;
	}

    public static void main(String[] args) throws Exception {
        parseFileToBase64_PNG("C:\\Users\\codewj\\Desktop\\技改.docx");
    }
}
