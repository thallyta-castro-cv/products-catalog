package br.com.thallyta.productcatalog.models.dtos.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {

    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private String categoryId;
}
