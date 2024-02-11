package br.com.thallyta.productcatalog.models.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private String categoryId;
    private String categoryTitle;
    private String categoryDescription;
    private String categoryOwnerId;
}
