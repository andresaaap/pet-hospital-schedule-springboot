package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){

        //save a customer using the customer service
        Customer customer = convertCustomerDTOToCustomer(customerDTO);
        Customer savedCustomer = customerService.save(customer);

        //convert the saved customer to a customerDTO and return it
        return convertCustomerToCustomerDTO(savedCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.findAll();
        return convertCustomerListToCustomerDTOList(customers);
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){

        //find the owner of a pet using the customer service
        Customer customer = customerService.findByOwnerByPetId(petId);

        //convert the customer to a customerDTO and return it
        return convertCustomerToCustomerDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        //save an employee using the employee service
        Employee employee = convertEmployeeDTOToEmployee(employeeDTO);
        Employee savedEmployee = employeeService.save(employee);

        //convert the saved employee to an employeeDTO and return it
        return convertEmployeeToEmployeeDTO(savedEmployee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

        //find an employee by id using the employee service
        Employee employee = employeeService.find(employeeId);

        //convert the employee to an employeeDTO and return it
        return convertEmployeeToEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {

            //set the availability of an employee using the employee service
            employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {

            //find employees by availability and skills using the employee service
            List<Employee> employees = employeeService.findEmployeesForService(employeeDTO.getDate(), employeeDTO.getSkills());

            //convert the employees to employeeDTOs and return them
            return convertEmployeeListToEmployeeDTOList(employees);
    }

    // create convertCustomerDTOToCustomer

    public Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setNotes(customerDTO.getNotes());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        return customer;
    }

    // create convertCustomerToCustomerDTO

    public CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setNotes(customer.getNotes());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());

        // List of Pets to List of PetIds
        List<Pet> pets = customer.getPets();
        if (pets != null) {
            List<Long> petIds = convertPetListToPetIdList(customer.getPets());
            // set the customerDTO's petIds
            customerDTO.setPetIds(petIds);
        }

        return customerDTO;
    }

    // create convertCustomerListToCustomerDTOList

    public List<CustomerDTO> convertCustomerListToCustomerDTOList(List<Customer> customers) {
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(customer.getId());
            customerDTO.setName(customer.getName());
            customerDTO.setNotes(customer.getNotes());
            customerDTO.setPhoneNumber(customer.getPhoneNumber());
            // List of Pets to List of PetIds
            List<Pet> pets = customer.getPets();
            if (pets != null) {
                List<Long> petIds = convertPetListToPetIdList(customer.getPets());
                // set the customerDTO's petIds
                customerDTO.setPetIds(petIds);
            }

            customerDTOs.add(customerDTO);
        }
        return customerDTOs;
    }

    // create convertEmployeeDTOToEmployee

    public Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO){
        // create an employee
        Employee employee = new Employee();
        // set the employee's id
        employee.setId(employeeDTO.getId());
        // set the employee's name
        employee.setName(employeeDTO.getName());
        // set the employee's skills
        employee.setSkills(employeeDTO.getSkills());
        // set the employee's daysAvailable
        employee.setDaysAvailable(employeeDTO.getDaysAvailable());
        // return the employee
        return employee;
    }

    // create convertEmployeeToEmployeeDTO

    public EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee){
        // create an employeeDTO
        EmployeeDTO employeeDTO = new EmployeeDTO();
        // set the employeeDTO's id
        employeeDTO.setId(employee.getId());
        // set the employeeDTO's name
        employeeDTO.setName(employee.getName());
        // set the employeeDTO's skills
        employeeDTO.setSkills(employee.getSkills());
        // set the employeeDTO's daysAvailable
        employeeDTO.setDaysAvailable(employee.getDaysAvailable());
        // return the employeeDTO
        return employeeDTO;
    }

    // create method to convert a list of pets to a list of petIds
    public List<Long> convertPetListToPetIdList(List<Pet> pets) {
        List<Long> petIds = new ArrayList<Long>();
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            petIds.add(pet.getId());
        }
        return petIds;
    }

    // create method to convert a list of employees to a list of employeeDTOs
    public List<EmployeeDTO> convertEmployeeListToEmployeeDTOList(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getName());
            employeeDTO.setSkills(employee.getSkills());
            employeeDTO.setDaysAvailable(employee.getDaysAvailable());
            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }

}
