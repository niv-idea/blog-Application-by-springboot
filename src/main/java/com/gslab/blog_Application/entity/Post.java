package com.gslab.blog_Application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private  Integer postId;
    @Column(name = "post_title",length = 70, nullable = false)
  private String title;
    @Column(length = 1000)
  private String content;

  private String imageName;

  private Date addedDate;

  @ManyToOne
  @JoinColumn(name = "category_Id")
  private Category category;
  @ManyToOne
  @JoinColumn(name= "user_Id")
  private User user;

}
