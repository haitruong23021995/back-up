package com.example.demo.LD.main;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContextInstance {

    @JsonProperty("_links")
    public Links links;
    public int totalCount;
    public String _environmentId;
    public String continuationToken;
    public ArrayList<Item> items;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Context {
    public String kind;
    public String key;
    public String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Context context = (Context) o;
        return Objects.equals(kind, context.kind) &&
                Objects.equals(key, context.key) &&
                Objects.equals(name, context.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, key, name);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Item {
    public Date lastSeen;
    public String id;
    public String applicationId;
    public Context context;
    public Links _links;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Links {
    public Next next;
    public Parent parent;
    public Self self;
    public Site site;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Next {
    public String href;
    public String type;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Parent {
    public String href;
    public String type;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Self {
    public String href;
    public String type;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Site {
    public String href;
    public String type;
}
