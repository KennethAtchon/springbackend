package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String name;

    @Lob
    private String shortDescription;

    @Lob
    private String longDescription;

    @Column(columnDefinition = "json")
    private String preciseDescription;

    @Column(nullable = false)
    private BigDecimal price;

    private BigDecimal discount;

    @Column(columnDefinition = "json")
    private String images;

    @Column(columnDefinition = "json")
    private String optionsImages;

    private String category;
    private String productTypes;
    private String special;

    @Column(name = "quantity_in_stock", nullable = false)
    private Integer quantityInStock;

    @Column(columnDefinition = "json")
    private String shippingDetails;

    @Column(columnDefinition = "json")
    private String optionsProduct;

    private String brand;

    @Column(name = "on_sale", columnDefinition = "boolean default false")
    private Boolean onSale;

    @Column(columnDefinition = "json")
    private String relatedProducts;

    private String measurements;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Getters and Setters
}
