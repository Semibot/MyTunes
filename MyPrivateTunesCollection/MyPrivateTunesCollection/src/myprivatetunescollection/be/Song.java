package myprivatetunescollection.be;

/**
 *
 * @author DKE
 */
public class Song{
    private int id;
    private String title;
    private String artist;

    public Song(int id, String title, String artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }
    
    @Override
    public String toString(){
        return title + "\t\t" + artist;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public String getArtist(){
        return artist;
    }
    
    public void setArtist(String artist){
        this.artist = artist;
    }
}