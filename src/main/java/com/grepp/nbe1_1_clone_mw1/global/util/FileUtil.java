package com.grepp.nbe1_1_clone_mw1.global.util;

import com.grepp.nbe1_1_clone_mw1.product.model.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static String uploadPath = "C:\\Users\\minwo\\Desktop\\workspace\\dev_project\\nbe1_1_clone_mw-1\\src\\main\\resources\\img";

    public static List<ProductImage> saveFiles(MultipartFile[] uploadFile) throws IOException {
        List<ProductImage> files = new ArrayList<>();
        if(uploadFile != null && uploadFile.length > 0){

            if(!new File(uploadPath).exists()){
                new File(uploadPath).mkdirs();
            }

            for(MultipartFile file : uploadFile){
                String saveName = file.getOriginalFilename();
                if(saveName == null || saveName.isEmpty()){
                    continue;
                }
                File savedFile = new File(uploadPath, saveName);

                file.transferTo(savedFile);
                files.add(ProductImage.create(file.getOriginalFilename(), savedFile.getAbsolutePath()));
            }
        }
        return files;
    }
}
