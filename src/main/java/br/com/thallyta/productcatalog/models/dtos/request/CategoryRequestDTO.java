package br.com.thallyta.productcatalog.models.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {
    private String title;
    private String description;
    private String ownerId;
}
