package br.com.rafamuvi.productapi.productapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String desc;

    private String imgUrl;

    public Product(String name, String desc, String imgUrl) {
        this.name = name;
        this.desc = desc;
        this.imgUrl = imgUrl;
    }


}
