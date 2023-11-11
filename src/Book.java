public class Book extends Product {
    String authorFullName;

    @Override
    public String toString() {
        return  "ID: " + getId() +  ". Книга под названием " + name +
                ", с описанием: " + description +
                ", цена: " + price +
                ", создан автором " + authorFullName + " в " +  createdAt;
    }
}
