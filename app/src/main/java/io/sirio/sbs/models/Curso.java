package io.sirio.sbs.models;

import java.util.ArrayList;

/**
 * Created by Diego on 23/05/2015.
 */
public class Curso {

    private int count;
    private int pages;
    private ArrayList<CursoPost> posts;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ArrayList<CursoPost> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<CursoPost> posts) {
        this.posts = posts;
    }
}
