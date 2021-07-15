package bachelor.project.PandG;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document
public class PGusers {
    @Id
    private String id;
    @Field
    private String username;
    @Field
    private String password;
    public PGusers(
            String username,
            String password
            ) {

        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "PGusers{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
