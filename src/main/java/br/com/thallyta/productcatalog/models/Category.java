package br.com.thallyta.productcatalog.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("title", title);
        json.put("description", description);
        json.put("ownerId", ownerId);
        json.put("type", "category");

        return json.toString();
    }

}
