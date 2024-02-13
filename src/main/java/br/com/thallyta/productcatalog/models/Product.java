package br.com.thallyta.productcatalog.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product{

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private String categoryId;

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("title", title);
        json.put("description", description);
        json.put("ownerId", ownerId);
        json.put("price", price);
        json.put("categoryId", categoryId);
        json.put("type", "product");

        return json.toString();
    }
}
