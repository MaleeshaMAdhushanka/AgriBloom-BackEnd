package lk.ijse.gdse70.agreebloom.util;

import lk.ijse.gdse70.agreebloom.enums.ImageType;
import lk.ijse.gdse70.agreebloom.exception.ImageExtractionFailedException;
import lk.ijse.gdse70.agreebloom.exception.ImagePersistFailedException;
import lk.ijse.gdse70.agreebloom.exception.InvalidImageTypeException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

//Spring marks the class as a Spring-manged bean, This Allows Spring to automatically
//Detect, instantiate, and inject th class wherever needed,
//enabling dependency injection and lifecycle management,
@Component
public class ImageUtil {
    public static Path IMAGE_DIRECTORY = Paths.get(System.getProperty("user.home"), "Desktop", "LocalS3Bucket").toAbsolutePath().normalize();

    public ImageUtil(){
        if (!Files.exists(IMAGE_DIRECTORY)){
            try {
                Files.createDirectories(IMAGE_DIRECTORY);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getImage(String imageId){
        try {
            Optional<Path> resource = searchImage(imageId);
            if (resource.isPresent()) {
                return Base64.getEncoder().encodeToString(Files.readAllBytes(resource.get()));
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new ImageExtractionFailedException(imageId);
        }

    }

    public String saveImage(ImageType imageType, MultipartFile file){

        //Check if the file is empty
        if (file.isEmpty()){
            return null;
        }

        //Check whether the file types are valid
        if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith("jpg") &&
                !Objects.requireNonNull(file.getOriginalFilename()).endsWith("png") &&
                !Objects.requireNonNull(file.getOriginalFilename()).endsWith("jpeg")
        ) {
            throw new InvalidImageTypeException("Invalid file type. Only JPG and PNG files are allowed.");
        }
        String fileName = imageType.toString() + "-" + UUID.randomUUID();
        try {
            Files.copy(file.getInputStream(), IMAGE_DIRECTORY.resolve(fileName + "." + Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1]));
            return fileName;
        } catch (IOException e) {
            throw new ImagePersistFailedException("Failed to save image");
        }
    }

    public String updateImage(String imageId, ImageType imageType,  MultipartFile file){
        try {
            Optional<Path> resource = searchImage(imageId);
            if (resource.isPresent()) {
                Files.delete(resource.get());
            }
            return saveImage(imageType, file);
        } catch (IOException e) {
            throw new ImagePersistFailedException("Failed to update image");
        }
    }
    private Optional<Path> searchImage(String imageId) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(IMAGE_DIRECTORY, imageId + ".*")) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) {
                    return Optional.of(entry);
                }
            }
        } catch (IOException e) {
            throw new ImageExtractionFailedException(imageId);
        }
        return Optional.empty();
    }
    public void deleteImage(String imageId) {
        try {
            Optional<Path> resource = searchImage(imageId);
            if (resource.isPresent()) {
                Files.delete(resource.get());
            }
        } catch (IOException e) {
            throw new ImagePersistFailedException("Failed to delete image: "+imageId);
        }
    }

}
