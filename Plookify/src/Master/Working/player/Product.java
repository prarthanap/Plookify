public class Product {

    private String trackName;
    private String name;
    private String artist;
    private double duration;
    private String genre;
    private String album;
    

    public Product(){
        this.trackName = " ";
        this.name = "";
        this.artist= "";
        this.duration = 0;
        this.genre = "";
        this.album = "";
    }

    public Product(String trackName, String name, String artist, double duration, String genre, String album){
       this.trackName = trackName;
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
        this.album = album;
    }

    public String getName() {
        return trackName;
    }

    public void setName(String trackName) {
        this.trackName = trackName;
    }
    
    public String getname()
    {
        return name;
        
    }
    
    public void setname(String name)
    {
        this.name =name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

     public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }


}