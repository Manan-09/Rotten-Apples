package dev.manan.rottenApples.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    public void uploadImage(String key, MultipartFile file) throws Exception {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3.putObject(new PutObjectRequest(bucketName, key, file.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            log.error("Error occured while uploading image with key {}", key, e);
            throw new Exception("Error occured while uploading image");
        }
    }

    public URL generatePresignedUrl(String key, long expiryTimeWindow) throws Exception {
        try {
            final Date expirationDate = new Date(System.currentTimeMillis() + expiryTimeWindow);
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, key)
                    .withMethod(HttpMethod.GET)
                    .withExpiration(expirationDate);
            return amazonS3.generatePresignedUrl(urlRequest);
        } catch (Exception e) {
            log.error("Error occurred while generating pre-signed URL for key {}", key, e);
            throw new Exception("Error occurred while generating pre-signed URL");
        }
    }

    public void deleteObject(String key) throws Exception {
        try {
            DeleteObjectRequest deleteRequest = new DeleteObjectRequest(bucketName, key);
            amazonS3.deleteObject(deleteRequest);
        } catch (Exception e) {
            log.error("Error occurred while deleting object with key {}", key, e);
        }
    }
}
