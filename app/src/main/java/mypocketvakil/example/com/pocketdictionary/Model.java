package mypocketvakil.example.com.pocketdictionary;

/**
 * Created by sanyam jain on 06-02-2017.
 */

public class Model {
    private static Model instance = null;
    int id;
    String title;
    String meaning;
    String synonyms;
    String translation;
    String antonyms;
    int favorites;

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }
    public Model()
    {

    }
    public Model(int id,String title,String meaning,String synonyms,String translation,String antonyms,int favorites)
    {
        this.id=id;
        this.title=title;
        this.meaning=meaning;
        this.synonyms=synonyms;
        this.translation=translation;
        this.antonyms=antonyms;
        this.favorites=favorites;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(String antonyms) {
        this.antonyms = antonyms;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }


}
