import java.util.ArrayList;
import java.util.List;

class User {
    private String name;
    private int age;
    private final String id;
    private List<CarbonConsumption> carbonConsumption;

    public User(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
        carbonConsumption = new ArrayList<CarbonConsumption>();
    }

    public List<CarbonConsumption> getCarbonConsumption() {
        return carbonConsumption;
    }

    public void setCarbonConsumption(List<CarbonConsumption> carbonConsumption) {
        this.carbonConsumption = carbonConsumption;
    }

    public int getAge() {
        return age;
    }
    public String getId() {
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
