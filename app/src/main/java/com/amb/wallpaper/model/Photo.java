package com.amb.wallpaper.model;

/**
 * Created by abarsode on 3/4/17.
 */

public class Photo {

//    { "id": "33198353736", "owner": "148036032@N07", "secret": "aec9b0bab2", "server": "648", "farm": 1,
// "title": "This is Sybill. She snores and plays fetch and is occasionally... - The Caturday", "ispublic": 1, "isfriend": 0, "isfamily": 0 }

    String id;
    String owner;
    String secret;
    String server;
    int farm;
    String title;
    int isPublic;
    int isfriend;
    int isfamily;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public int getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(int isfriend) {
        this.isfriend = isfriend;
    }

    public int getIsfamily() {
        return isfamily;
    }

    public void setIsfamily(int isfamily) {
        this.isfamily = isfamily;
    }
}
