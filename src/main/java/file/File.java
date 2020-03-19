package file;

public class File {
    private String title;
    private double size;
    private String path;

    public File(String title, double size, String path){
        this.title = title;
        this.size = size;
        this.path = path;
    }

    public String getTitle(){
        return this.title;
    }

    public double getSize(){
        return this.size;
    }

    public String getPath(){
        return this.path;
    }

}
