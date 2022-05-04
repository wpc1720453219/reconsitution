import java.util.Objects;

/**
 * (剧目实体类)
 */
public class Plays {
    private String name;
    private String type;

    public Plays(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plays plays = (Plays) o;
        return Objects.equals(name, plays.name) && Objects.equals(type, plays.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}
