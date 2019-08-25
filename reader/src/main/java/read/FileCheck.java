package read;

public interface FileCheck {

    public static String checkFileExtension(String filePath){
        return filePath.substring(filePath.lastIndexOf(".")+1);
    }

}
