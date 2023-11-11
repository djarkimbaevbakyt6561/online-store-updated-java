import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final User[] users = new User[100];
    private static int userIndex = 0;
    private static User currentUser = null;
    private static Product[] products = new Product[100];
    private static int productIndex = 0;

    public static void displayInitialOptions() {
        System.out.println("""
                1. Регистрация
                2. Вход
                3. Выход""");
    }

    public static void displayLoggedOptions() {
        System.out.println("""
                0. Выйти
                1. Добавить новый продукт
                2. Получить все продукты
                3. Получить все книги
                4. Получить все электроники
                5. Удалить продукт""");
    }

    private static String inputString(String prompt) {
        System.out.print(prompt);
        String returnText;
        while (true) {
            String text = scanner.nextLine();

            if (text.isEmpty()) {
                System.out.println("Значение не должно быть пустым!");
            } else {
                returnText = text;
                break;
            }
        }
        return returnText;
    }

    private static int inputInt() {
        System.out.print("Количество памяти: ");
        scanner.nextLine();
        while (!scanner.hasNextInt()) {
            System.out.println("Введите число!");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }


    private static boolean inputBoolean() {
        System.out.print("Новый ли это продукт? (true or false): ");
        while (!scanner.hasNextBoolean()) {
            System.out.println("Введите 'true' или 'false'!");
            scanner.nextLine();
        }
        return scanner.nextBoolean();
    }

    private static BigDecimal inputBigDecimal() {
        System.out.print("Укажите цену продукта: ");
        while (!scanner.hasNextBigDecimal()) {
            System.out.println("Введите число!");
            scanner.nextLine();
        }
        return scanner.nextBigDecimal();
    }

    private static boolean isEmailAlreadyTaken(String email) {
        for (User user : users) {
            if (user != null && user.email.equals(email)) {
                return true;
            }
        }
        return false;
    }

    private static Book addBook() {
        Book product = new Book();
        product.name = inputString("Напишите название продукта: ");
        product.description = inputString("Напишите описание: ");
        product.price = inputBigDecimal();
        scanner.nextLine();
        product.createdAt = inputString("Укажите дату выпуска продукта: ");
        product.authorFullName = inputString("Укажите полное имя автора: ");
        product.setId(productIndex + 1);

        return product;
    }

    private static Device addDevice() {
        Random random = new Random();
        Device product = new Device();
        product.name = inputString("Напишите название продукта: ");
        product.brand = inputString("Напишите бренд: ");
        product.color = inputString("Укажите цвет продукта: ");
        product.isNew = inputBoolean();
        product.memory = inputInt();
        scanner.nextLine();
        product.description = inputString("Напишите описание: ");
        product.price = inputBigDecimal();
        scanner.nextLine();
        product.createdAt = inputString("Укажите дату выпуска продукта: ");
        product.setId(random.nextLong(1, 10000));
        return product;
    }

    private static Product[] deleteProduct(long id) {
        Product[] newProducts = new Product[100];
        int newIndex = 0;

        for (Product product : products) {
            if (product != null && !(product.getId() == id)) {
                newProducts[newIndex++] = product;
            }
        }

        productIndex = newIndex;
        return newProducts;
    }

    private static Product[] deleteProducts(long[] ids) {
        Product[] newProducts = new Product[100];
        int newIndex = 0;

        for (Product product : products) {
            boolean shouldDelete = false;
            for (long id : ids) {
                if (product != null && product.getId() == id) {
                    shouldDelete = true;
                    break;
                }
            }
            if (!shouldDelete) {
                newProducts[newIndex++] = product;
            }
        }

        productIndex = newIndex;
        return newProducts;
    }


    public static void main(String[] args) {
        boolean exit = false;
        boolean isLoggedIn = false;

        while (!exit) {
            displayInitialOptions();
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                scanner.nextLine();
                switch (num) {
                    case 1:
                        User user = new User();
                        user.firstName = inputString("Введите ваше имя: ");
                        user.lastName = inputString("Введите вашу фамилию: ");
                        while (true) {
                            String email = inputString("Введите ваш email: ");
                            if (isEmailAlreadyTaken(email)) {
                                System.out.println("Такой email уже существует!");
                            } else if (!email.contains("@") || email.length() < 7) {
                                System.out.println("Неправильно написан email или должен состоять из 7 символов!");
                            } else {
                                user.email = email;
                                break;
                            }
                        }
                        while (true) {
                            String password = inputString("Введите пароль для вашего email: ");
                            if (password.length() <= 4) {
                                System.out.println("Длина пароля должна быть больше 4 символов!");
                            } else {
                                user.password = password;
                                break;
                            }
                        }
                        if (userIndex < 100) {
                            users[userIndex] = user;
                            userIndex++;
                            System.out.println("Вы успешно зарегистрировались!");
                        } else {
                            System.out.println("Достигнут лимит пользователей");
                        }
                        break;
                    case 2:
                        while (currentUser == null) {
                            String email = inputString("Напишите email: ");
                            String password = inputString("Напишите пароль: ");
                            for (User u : users) {
                                if (u != null && u.email.equals(email) && u.password.equals(password)) {
                                    currentUser = u;
                                    break;
                                }
                            }
                            if (currentUser != null) {
                                System.out.println("Вы успешно вошли! ~ Добро пожаловать! ");
                                System.out.println("Профиль: " + currentUser.firstName + " " + currentUser.lastName);
                                System.out.println("email: " + currentUser.email);
                                System.out.println();
                                isLoggedIn = true;
                            } else {
                                System.out.println("Неправильный email или пароль.");
                            }
                        }
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Вы успешно вышли!");
                        break;
                    default:
                        System.out.println("Введите правильную цифру!");
                        break;
                }

            } else {
                System.out.println("Вы не ввели значение, введите еще раз!");
                scanner.nextLine();
            }

            if (isLoggedIn) {
                while (isLoggedIn) {
                    displayLoggedOptions();
                    if (scanner.hasNextInt()) {
                        int num = scanner.nextInt();
                        switch (num) {
                            case 0:
                                currentUser = null;
                                isLoggedIn = false;
                                System.out.println("Вы успешно вышли!");
                                break;
                            case 1:
                                while (true) {
                                    System.out.println("Выберите категорию: (1) Устройство  или (2) Книга ");
                                    scanner.nextLine();
                                    if (scanner.hasNextInt()) {
                                        int categoryChoice = scanner.nextInt();
                                        scanner.nextLine();
                                        if (categoryChoice == 1) {
                                            Device product = addDevice();
                                            if (productIndex < 100) {
                                                products[productIndex] = product;
                                                productIndex++;
                                                System.out.println("Устройство успешно добавлена!");
                                            } else {
                                                System.out.println("Достигнут лимит продуктов!");
                                            }
                                            System.out.println();
                                            break;
                                        } else if (categoryChoice == 2) {
                                            Book product = addBook();
                                            if (productIndex < 100) {
                                                products[productIndex] = product;
                                                productIndex++;
                                                System.out.println("Устройство успешно добавлена!");
                                            } else {
                                                System.out.println("Достигнут лимит продуктов!");
                                            }
                                            System.out.println("Книга успешно добавлена!");
                                            System.out.println();
                                            break;
                                        } else {
                                            System.out.println("Выбрана неверная категория.");
                                        }
                                    } else if (scanner.hasNextLine()) {
                                        String category = scanner.nextLine();
                                        if (category.equalsIgnoreCase("устройство")) {
                                            Device product = addDevice();
                                            if (productIndex < 100) {
                                                products[productIndex] = product;
                                                productIndex++;
                                                System.out.println("Устройство успешно добавлена!");
                                            } else {
                                                System.out.println("Достигнут лимит продуктов!");
                                            }
                                            System.out.println("Устройство успешно добавлена!");
                                            System.out.println();
                                            break;
                                        } else if (category.equalsIgnoreCase("книга")) {
                                            Book product = addBook();
                                            if (productIndex < 100) {
                                                products[productIndex] = product;
                                                productIndex++;
                                                System.out.println("Устройство успешно добавлена!");
                                            } else {
                                                System.out.println("Достигнут лимит продуктов!");
                                            }
                                            System.out.println("Книга успешно добавлена!");
                                            System.out.println();
                                            break;
                                        } else {
                                            System.out.println("Введите правильное значение!");
                                        }
                                    } else {
                                        System.out.println("Введите правильное значение!");
                                    }
                                }
                                break;
                            case 2:
                                if (productIndex == 0) {
                                    System.out.println("Продуктов не найдено.");
                                } else {
                                    System.out.println("Все продукты:");
                                    for (Product p : products) {
                                        if (p != null) {
                                            System.out.println(p);
                                        }
                                    }
                                }
                                break;
                            case 3:
                                Book[] books = new Book[products.length];
                                int newIndex = 0;
                                for (Product p : products) {
                                    if (p instanceof Book) {
                                        books[newIndex++] = (Book) p;
                                    }
                                }
                                if (newIndex == 0) {
                                    System.out.println("Книг не найдено.");
                                } else {
                                    System.out.println("Все книги:");
                                    for (Book b : books) {
                                        if (b != null) {
                                            System.out.println(b);

                                        }
                                    }
                                }
                                break;
                            case 4:
                                Device[] devices = new Device[products.length];
                                int newIndex2 = 0;
                                for (Product p : products) {
                                    if (p instanceof Device) {
                                        devices[newIndex2++] = (Device) p;
                                    }
                                }
                                if (newIndex2 == 0) {
                                    System.out.println("Электроники не найдено.");
                                } else {
                                    System.out.println("Все электроники:");
                                    for (Device d : devices) {
                                        if (d != null) {
                                            System.out.println(d);
                                        }
                                    }
                                }
                                break;
                            case 5:
                                if (productIndex == 0) {
                                    System.out.println("Продуктов нету!");
                                } else {
                                    System.out.print("Сколько продуктов хотите удалить: ");
                                    while (!scanner.hasNextInt()) {
                                        System.out.println("Введите число!");
                                        scanner.nextLine();
                                    }
                                    int count = scanner.nextInt();
                                    scanner.nextLine();
                                    if (count == 1) {
                                        while (true) {
                                            System.out.println("Введите id продукта: ");
                                            while (!scanner.hasNextLong()) {
                                                System.out.println("Введите число!");
                                                scanner.nextLine();
                                            }
                                            long id = scanner.nextLong();
                                            boolean found = false;
                                            for (Product product : products) {
                                                if (product != null && product.getId() == id) {
                                                    found = true;
                                                    break;
                                                }
                                            }
                                            if (found) {
                                                products = deleteProduct(id);
                                                break;
                                            } else {
                                                System.out.println("Продукт с таким id не найден!");
                                                System.out.print("Доступные id: ");
                                                for (Product product : products) {
                                                    if (product != null) {
                                                        System.out.println(product.getId() + ", ");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (count > 1) {
                                        long[] ids = new long[count];
                                        for (int i = 0; i < count; i++) {
                                            while (true) {
                                                System.out.println("Введите id продукта: ");
                                                while (!scanner.hasNextLong()) {
                                                    System.out.println("Введите число!");
                                                    scanner.nextLine();
                                                }
                                                long id = scanner.nextLong();
                                                boolean found = false;
                                                for (Product product : products) {
                                                    if (product != null && product.getId() == id) {
                                                        found = true;
                                                        break;
                                                    }
                                                }
                                                if (found) {
                                                    ids[i] = id;
                                                    break;
                                                } else {
                                                    System.out.println("Продукт с таким id не найден!");
                                                }
                                            }
                                        }
                                        products = deleteProducts(ids);
                                        break;
                                    }
                                    System.out.println("Успешно удалено " + count);
                                }
                                break;

                            default:
                                System.out.println("Введите правильное число!");
                                break;
                        }
                    } else {
                        System.out.println("Введите правильное число!");
                        scanner.nextLine();
                    }
                }
            }
        }
    }
}
