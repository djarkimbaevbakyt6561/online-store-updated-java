public class Device extends Product{
    String brand;
    String color;
    boolean isNew;
    int memory;

    @Override
    public String toString() {
        return isNew ? "ID: " + getId() +  ". Новое" + " устройство под названием " + name +
                " с цветом " + color +
                ", с памятью " + memory + '\n' +
                "Описание: " + description +
                ", цена: " + price +
                ", была создана " + createdAt : "ID: " + getId() +  ". Старое" +
                " устройство под названием " + name +
                " с цветом " + color +
                ", с памятью " + memory + '\n' +
                "Описание: " + description +
                ", цена: " + price +
                ", была создана " + createdAt;
    }
}
