package com.example.newsfast;

public class news {
    public String title;
    public String description;
    public String url;
    public String urlimage;
    public news(String title,String description,String url,String urlimage){
        this.title=title;
        this.description=description;
        this.url=url;
        this.urlimage=urlimage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
