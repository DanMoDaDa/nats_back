package org.danmo.utils;

import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.domain.proto.ErrorCodeConstants;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

/**
 * FastDfs工具类
 *
 * @author 胡春林
 * @date 2022-7-14
 */
@Slf4j
@Component
public class FastDfsUtil {

    /**
     * 文件对象
     */
    private static FastFileStorageClient storageClient;

    /**
     * 图片对象
     */
    private static ThumbImageConfig thumbImageConfig;

    @Autowired
    public void setStorageClient(FastFileStorageClient storageClient) {
        this.storageClient = storageClient;
    }

    @Autowired
    public void setThumbImageConfig(ThumbImageConfig thumbImageConfig) {
        this.thumbImageConfig = thumbImageConfig;
    }

    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @return java.lang.String
     * @date 2022-7-14
     */
    public static String uploadFile(MultipartFile multipartFile) throws IOException {
        return uploadFile(multipartFile, null);
    }

    /**
     * 上传带元数据
     *
     * @param multipartFile 文件
     * @param metaDataSet   元数据
     * @return java.lang.String
     * @date 2022-7-14
     */
    public static String uploadFile(MultipartFile multipartFile, Set<MetaData> metaDataSet) throws IOException {

        String originalFilename = multipartFile.getOriginalFilename();
        String fileExtName = StringUtils.substringAfterLast(originalFilename, ".");

        StorePath storePath = storageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(),
                fileExtName, metaDataSet);

        return storePath.getFullPath();
    }

    /**
     * 下载文件
     *
     * @param fileUrl 文件地址
     * @return byte[] 字节数组
     * @date 2022-7-14
     */
    public static byte[] downloadFile(String fileUrl) {

        StorePath storePath = StorePath.parseFromUrl(fileUrl);
        DownloadByteArray downloadByteArray = new DownloadByteArray();

        return storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), downloadByteArray);
    }

    /**
     * 下载缩略图
     *
     * @param fileUrl 文件地址
     * @return byte[]
     * @date 2022-7-14
     */
    public static byte[] downloadThumbImage(String fileUrl) {
        StorePath storePath = StorePath.parseFromUrl(fileUrl);
        // 获取缩略图地址
        String thumbImagePath = thumbImageConfig.getThumbImagePath(storePath.getPath());
        DownloadByteArray downloadByteArray = new DownloadByteArray();

        return storageClient.downloadFile(storePath.getGroup(), thumbImagePath, downloadByteArray);
    }

    /**
     * 删除文件
     *
     * @param fileUrl 文件地址
     * @date 2022-7-14
     */
    public static void deleteFile(String fileUrl) throws Exception {
        if (StringUtils.isEmpty(fileUrl)) {
            log.info("fileUrl == >>文件路径为空...");
            return;
        }
        StorePath storePath = StorePath.parseFromUrl(fileUrl);
        try {
            // 删除文件
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
            log.info("delete success:{}", fileUrl);
        } catch (FdfsServerException e) {
            // 文件不存在则不抛出异常
            if (ErrorCodeConstants.ERR_NO_ENOENT != e.getErrorCode()) {
                throw new Exception(e.getMessage());
            } else {
                log.info("fileUrl:{}, message:{}", fileUrl, e.getMessage());
            }
        }
    }

    /**
     * 获取文件元数据
     *
     * @param fileUrl 文件地址
     * @return java.util.Set<com.github.tobato.fastdfs.domain.fdfs.MetaData>
     * @date 2022-7-14
     */
    public static Set<MetaData> getMetadata(String fileUrl) {
        StorePath storePath = StorePath.parseFromUrl(fileUrl);
        return storageClient.getMetadata(storePath.getGroup(), storePath.getPath());
    }

    /**
     * 查询文件信息
     *
     * @param fileUrl 文件地址
     * @return com.github.tobato.fastdfs.domain.fdfs.FileInfo
     * @date 2022-7-14
     */
    public static FileInfo queryFileInfo(String fileUrl) {
        StorePath storePath = StorePath.parseFromUrl(fileUrl);

        return storageClient.queryFileInfo(storePath.getGroup(), storePath.getPath());
    }

}
