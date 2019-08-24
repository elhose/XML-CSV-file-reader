package read;

public interface FileCheck {

    public static String checkFileExtension(String filePath){
        String fileExtension = filePath.substring(filePath.lastIndexOf(".")+1);
        return fileExtension;
    }

}
