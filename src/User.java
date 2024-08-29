class User {
    private String name;
    private int age;
    private final int id;

    public User(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }
    public int getAge() {
        return age;
    }
    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{ name = '" + name + "', age = " + age + ", id = '" + id + "' }";
    }

}
