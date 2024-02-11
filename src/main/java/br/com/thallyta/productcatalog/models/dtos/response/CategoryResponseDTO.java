package br.com.thallyta.productcatalog.models.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDTO {
    private String id;
    private String title;
    private String description;
    private String ownerId;
}
