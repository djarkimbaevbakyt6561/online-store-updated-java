import java.math.BigDecimal;
import java.time.LocalDate;

public class Product {
    String name;
    String description;
    BigDecimal price;
    String createdAt;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
