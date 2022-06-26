public class Customer {
    String name, ic;
    int age;
    
    public Customer()
    {
        name = ic = "";
        age = 0;
    }
    public Customer(String name, String ic, int age)
    {
        this.name = name;
        this.ic = ic;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIc() {
        return ic;
    }
    public void setIc(String ic) {
        this.ic = ic;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    
}
