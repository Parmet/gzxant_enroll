package com.gzxant.entity.article.dto;

/**
 * @author: Fatal
 * @date: 2018/7/14 0014 15:37
 */
public class EnrollArticleDTO {

    private String title;

    private String content;

    private String image;

    private String releaseDate;

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "EnrollArticleDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
