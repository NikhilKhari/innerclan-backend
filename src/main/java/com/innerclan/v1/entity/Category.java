package com.innerclan.v1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"products"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @NotNull(message = "Category name cannot be blank")
    @Column(nullable = false)
    String name;



    @ElementCollection
    @CollectionTable(name="category_information", joinColumns = @JoinColumn(name="category_id") )
    List<String> information;

    @JsonIgnore
    @OneToMany(mappedBy ="category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    Set<Product> products;

    public void addProducts(Product product){
        if(products==null){
            products= new HashSet<Product>();
        }
        products.add(product);
        product.setCategory(this);
    }

}


