import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Customer {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private Gender gender;

    //contructor
    public Customer(Integer customerId,String firstName,String lastName,Gender gender) {
        this.customerId = customerId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
    }

    //getters and setters
    public Integer getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    //toString()
    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(customerId, customer.customerId) && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && gender == customer.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, gender);
    }

    public String getFullGenderNames(){
        return Arrays.toString(Gender.values());
    }

    public void getGenderCount(List<Customer> customerList){
      /* Map<Gender,Integer> genderCount=new HashMap<>();
        for(Customer customer:customerList) {
            Gender g = customer.getGender();
            if (genderCount.containsKey(g)) {
                genderCount.put(g, genderCount.get(g) + 1);
            } else {
                genderCount.put(g, 1);
            }
        }
        for (Map.Entry<Gender,Integer> mapElement :genderCount.entrySet()) {
            System.out.println(mapElement.getKey()+" "+mapElement.getValue());
        }
        */
         Map<Gender, Long> genderCount = customerList.stream()
                .collect(Collectors.groupingBy(
                        Customer::getGender,
                        Collectors.counting()
                ));

         genderCount.entrySet().stream()
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }

    public void getCustomerDetails(int customerId,List<Customer>customerList) throws ExceptionHandler.CustomerNotFoundException {
        for(Customer customer:customerList){
            if(customer.getCustomerId()==customerId){
                System.out.println("Name "+customer.getFirstName()+" "+customer.getLastName());
                System.out.println("Gender "+customer.getGender().toString());
                return;
            }
        }
        throw new ExceptionHandler.CustomerNotFoundException();
    }
}
