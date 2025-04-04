package com.var.entity;


import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    //@Size(max = 100)
    @Column(unique = true)
    private String text;



    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL )
    @JoinColumn(name = "post_id")
 
    private Post post;
    
    public Comment(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String title) {
        this.text = title;
    }



    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
