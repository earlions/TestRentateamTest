package ru.onedr.earlzzz.testrentateam.recyclerview;


public class Post {

   
    private String userEmail;
    
    private long id;
   
    private String firsName;
  
    private String lastName;


    private String avatarSrc;

    public Post(long id, String firsName, String lastName, String userEmail, String avatarSrc) {
        this.id=id;
        this.firsName = firsName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.avatarSrc = avatarSrc;
    }

    public void setAvatarSrc(String avatar) {
        this.avatarSrc = avatar;
    }


    public String getAvatarSrc() {
        return avatarSrc;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}